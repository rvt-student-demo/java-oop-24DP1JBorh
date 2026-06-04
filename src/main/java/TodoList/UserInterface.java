package TodoList;

import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author jegor
 */
public class UserInterface {

    private TodoList todoList;
    private Scanner scanner;

    public UserInterface(TodoList todoList, Scanner scanner) {
        this.todoList = todoList;
        this.scanner = scanner;
    }

    public void start() {

        System.out.println("""
                    command list:
                    stop - stop programm
                    add - add task
                    list - list all tasks
                    remove - remove task
                """);

        while (true) {
            System.out.print("Command: ");
            String command = scanner.nextLine();

            if (command.equals("stop")) {
                break;
            }

            if (command.equals("add")) {
                System.out.print("To add: ");
                String task = scanner.nextLine();

                todoList.add(task);

                continue;
            }

            if (command.equals("list")) {
                todoList.print();

                continue;
            }

            if (command.equals("remove")) {
                System.out.print("Which one is removed? ");
                int id = Integer.valueOf(scanner.nextLine());

                todoList.remove(id);
            }
        }
    }
}