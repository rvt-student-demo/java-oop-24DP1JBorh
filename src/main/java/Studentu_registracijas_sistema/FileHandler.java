package Studentu_registracijas_sistema;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import Studentu_registracijas_sistema.Exceptions.EmailAlreadyExistsException;
import Studentu_registracijas_sistema.Exceptions.PersonalCodeAlreadyExistsException;
import Studentu_registracijas_sistema.Exceptions.StudentNotFoundException;

public class FileHandler {
    private final String filePath = "students.csv";
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public void saveStudent(Student student) {

        List<Student> existing = loadAllStudents();
        for (Student s : existing) {
            if (s.getEmail().equalsIgnoreCase(student.getEmail())) {
                throw new EmailAlreadyExistsException(student.getEmail());
            }
            if (s.getPersonId().equals(student.getPersonId())) {
                throw new PersonalCodeAlreadyExistsException(student.getPersonId());
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(studentToCsv(student));
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Kluda saglabajot failu: " + e.getMessage());
        }
    }

    public List<Student> loadAllStudents() {
        List<Student> students = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists())
            return students;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isBlank()) {
                    students.add(csvToStudent(line));
                }
            }
        } catch (IOException e) {
            System.out.println("Kluda lasot failu: " + e.getMessage());
        }

        return students;
    }

    public void removeStudent(String personId) {
        List<Student> students = loadAllStudents();
        boolean found = students.removeIf(s -> s.getPersonId().equals(personId));

        if (!found) {
            throw new StudentNotFoundException(personId);
        }

        rewriteFile(students);
    }

    public void updateStudent(Student updated) {
        List<Student> students = loadAllStudents();
        boolean found = false;

        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getPersonId().equals(updated.getPersonId())) {
                students.set(i, updated);
                found = true;
                break;
            }
        }

        if (!found) {
            throw new StudentNotFoundException(updated.getPersonId());
        }

        rewriteFile(students);
    }

    private String studentToCsv(Student s) {
        String dateTime = DATE_FORMAT.format(s.getRegistrationDateTime());
        return s.getFirstName() + "," +
                s.getLastName() + "," +
                s.getEmail() + "," +
                s.getPersonId() + "," +
                dateTime;
    }

    private Student csvToStudent(String line) {
        String[] parts = line.split(",");
        Student s = new Student(parts[0], parts[1], parts[2], parts[3]);
        s.setRegistrationDateTime(LocalDateTime.parse(parts[4], DATE_FORMAT));
        return s;
    }

    private void rewriteFile(List<Student> students) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
            for (Student s : students) {
                writer.write(studentToCsv(s));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Kluda parrakstot failu: " + e.getMessage());
        }
    }

    public Student findByPersonId(String personId) {
        List<Student> students = loadAllStudents();
        for (Student s : students) {
            if (s.getPersonId().equals(personId)) {
                return s;
            }
        }
        throw new StudentNotFoundException(personId);
    }
}