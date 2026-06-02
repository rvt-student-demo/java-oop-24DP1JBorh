package Studentu_registracijas_sistema;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Registration registration = new Registration();

        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║   Studentu registracijas sistema     ║");
        System.out.println("╚══════════════════════════════════════╝");

        while (true) {
            printMenu();
            System.out.print("Izvelieties darbibu: ");
            String choice = scanner.nextLine().trim().toLowerCase();

            switch (choice) {
                case "1", "register" -> registration.register();
                case "2", "show" -> registration.show();
                case "3", "remove" -> registration.remove();
                case "4", "edit" -> registration.edit();
                case "5", "exit" -> {
                    System.out.println("Uz redzesanos!");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Nepareiza izvele. Meginiet velreiz.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n┌──────────────────────────────┐");
        System.out.println("│  1. register  — pievienot    │");
        System.out.println("│  2. show      — skatit visus │");
        System.out.println("│  3. remove    — dzest        │");
        System.out.println("│  4. edit      — rediget      │");
        System.out.println("│  5. exit      — iziet        │");
        System.out.println("└──────────────────────────────┘");
    }
}