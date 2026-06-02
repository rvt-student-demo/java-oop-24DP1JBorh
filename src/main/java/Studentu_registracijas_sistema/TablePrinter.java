package Studentu_registracijas_sistema;

import java.util.List;

public class TablePrinter {

    private static final String[] HEADERS = {
            "Vards", "Uzvards", "E-pasts", "Personas kods", "Registracija"
    };

    public void printTable(List<Student> students) {
        if (students.isEmpty()) {
            System.out.println("Nav registretu studentu.");
            return;
        }

        int[] widths = new int[HEADERS.length];

        for (int i = 0; i < HEADERS.length; i++) {
            widths[i] = HEADERS[i].length();
        }

        for (Student s : students) {
            String[] row = studentToRow(s);
            for (int i = 0; i < row.length; i++) {
                if (row[i].length() > widths[i]) {
                    widths[i] = row[i].length();
                }
            }
        }

        String separator = buildSeparator(widths);

        System.out.println(separator);
        System.out.println(buildRow(HEADERS, widths));
        System.out.println(separator);

        for (Student s : students) {
            System.out.println(buildRow(studentToRow(s), widths));
        }

        System.out.println(separator);
        System.out.println("Kopa: " + students.size() + " studenti.");
    }

    private String buildSeparator(int[] widths) {
        StringBuilder sb = new StringBuilder("+");
        for (int w : widths) {
            sb.append("-".repeat(w + 2)).append("+");
        }
        return sb.toString();
    }

    private String buildRow(String[] values, int[] widths) {
        StringBuilder sb = new StringBuilder("|");
        for (int i = 0; i < values.length; i++) {
            sb.append(" ");
            sb.append(values[i]);
            sb.append(" ".repeat(widths[i] - values[i].length()));
            sb.append(" |");
        }
        return sb.toString();
    }

    private String[] studentToRow(Student s) {
        return new String[] {
                s.getFirstName(),
                s.getLastName(),
                s.getEmail(),
                s.getPersonId(),
                s.getRegistrationDateTime().toString().replace("T", " ")
        };
    }
}
