package Studentu_registracijas_sistema;

import java.util.List;
import java.util.Scanner;

import Studentu_registracijas_sistema.Exceptions.EmailAlreadyExistsException;
import Studentu_registracijas_sistema.Exceptions.InvalidInputException;
import Studentu_registracijas_sistema.Exceptions.PersonalCodeAlreadyExistsException;
import Studentu_registracijas_sistema.Exceptions.StudentNotFoundException;

public class Registration {

    private final Scanner scanner = new Scanner(System.in);
    private final FileHandler fileHandler = new FileHandler();
    private final TablePrinter tablePrinter = new TablePrinter();

    public void register() {
        System.out.println("\n=== Jauna studenta registracija ===");

        String firstName = promptValidated("Ievadiet vardu: ", "Vards");
        String lastName = promptValidated("Ievadiet uzvardu: ", "Uzvards");
        String email = promptEmail();
        String personId = promptPersonId();

        try {
            Student student = new Student(firstName, lastName, email, personId);
            fileHandler.saveStudent(student);
            System.out.println("✓ Students veiksmigi registrets!");
        } catch (EmailAlreadyExistsException | PersonalCodeAlreadyExistsException e) {
            System.out.println("Kluda: " + e.getMessage());
        }
    }

    public void show() {
        System.out.println("\n=== Visu studentu saraksts ===");
        List<Student> students = fileHandler.loadAllStudents();
        tablePrinter.printTable(students);
    }

    public void remove() {
        System.out.println("\n=== Studenta dzesana ===");
        System.out.print("Ievadiet personas kodu: ");
        String personId = scanner.nextLine().trim();

        try {
            Student student = fileHandler.findByPersonId(personId);
            System.out.println("Dzest so studentu?");
            tablePrinter.printTable(List.of(student));
            System.out.print("Apstiprinat (j/n): ");
            String confirm = scanner.nextLine().trim();

            if (confirm.equalsIgnoreCase("j")) {
                fileHandler.removeStudent(personId);
                System.out.println("✓ Students veiksmigi dzests!");
            } else {
                System.out.println("Dzesana atcelta.");
            }
        } catch (StudentNotFoundException e) {
            System.out.println("Kluda: " + e.getMessage());
        }
    }

    public void edit() {
        System.out.println("\n=== Studenta datu redigesana ===");
        System.out.print("Ievadiet personas kodu: ");
        String personId = scanner.nextLine().trim();

        try {
            Student student = fileHandler.findByPersonId(personId);
            System.out.println("Atrasts students:");
            tablePrinter.printTable(List.of(student));

            System.out.println("(Nospiediet Enter, lai atstatu veco vertibu)");

            System.out.print("Jauns vards [" + student.getFirstName() + "]: ");
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                try {
                    Validator.validateFirstName(input);
                    student.setFirstName(input);
                } catch (InvalidInputException e) {
                    System.out.println("Kluda: " + e.getMessage() + " — vertiba netika mainita.");
                }
            }

            System.out.print("Jauns uzvards [" + student.getLastName() + "]: ");
            input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                try {
                    Validator.validateLastName(input);
                    student.setLastName(input);
                } catch (InvalidInputException e) {
                    System.out.println("Kluda: " + e.getMessage() + " — vertiba netika mainita.");
                }
            }

            System.out.print("Jauns e-pasts [" + student.getEmail() + "]: ");
            input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                try {
                    Validator.validateEmail(input);
                    student.setEmail(input);
                } catch (InvalidInputException e) {
                    System.out.println("Kļūda: " + e.getMessage() + " — vertiba netika mainita.");
                }
            }

            fileHandler.updateStudent(student);
            System.out.println("✓ Dati veiksmigi atjauninati!");
            tablePrinter.printTable(List.of(student));

        } catch (StudentNotFoundException e) {
            System.out.println("Kluda: " + e.getMessage());
        }
    }

    private String promptValidated(String prompt, String fieldName) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                if (fieldName.equals("Vards"))
                    Validator.validateFirstName(input);
                else
                    Validator.validateLastName(input);
                return input;
            } catch (InvalidInputException e) {
                System.out.println("Kluda: " + e.getMessage());
            }
        }
    }

    private String promptEmail() {
        while (true) {
            System.out.print("Ievadiet e-pastu: ");
            String input = scanner.nextLine().trim();
            try {
                Validator.validateEmail(input);
                return input;
            } catch (InvalidInputException e) {
                System.out.println("Kluda: " + e.getMessage());
            }
        }
    }

    private String promptPersonId() {
        while (true) {
            System.out.print("Ievadiet personas kodu (DDMMYY-XXXXX): ");
            String input = scanner.nextLine().trim();
            try {
                Validator.validatePersonId(input);
                return input;
            } catch (InvalidInputException e) {
                System.out.println("Kluda: " + e.getMessage());
            }
        }
    }
}
