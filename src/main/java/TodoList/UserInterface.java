package TodoList;

import java.sql.SQLException;
import java.util.Scanner;

public class UserInterface {

    private final TodoDB db;
    private final Scanner scanner;

    public UserInterface(TodoDB db, Scanner scanner) {
        this.db = db;
        this.scanner = scanner;
    }

    public void start() {
        System.out.println("""
                Commands:
                  add    - add task
                  list   - list all tasks
                  remove - remove task by id
                  stop   - exit
                """);

        while (true) {
            System.out.print("Command: ");
            String command = scanner.nextLine().trim();

            switch (command) {
                case "stop" -> {
                    return;
                }
                case "add" -> {
                    System.out.print("Task: ");
                    String task = scanner.nextLine();
                    try {
                        db.add(task);
                        System.out.println("Added.");
                    } catch (SQLException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                case "list" -> {
                    db.findAll().forEach(System.out::println);
                }
                case "remove" -> {
                    System.out.print("ID to remove: ");
                    try {
                        int id = Integer.parseInt(scanner.nextLine());
                        db.removeById(id);
                        System.out.println("Removed.");
                    } catch (NumberFormatException e) {
                        System.out.println("Enter a valid number.");
                    }
                }
                default -> System.out.println("Unknown command.");
            }
        }
    }
}
