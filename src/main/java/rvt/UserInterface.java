package rvt;
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
            }

            if (command.equals("list")) {
                todoList.print();
            }

            if (command.equals("update")) {
                todoList.updateFile();
            }

            if (command.equals("loadfile")) {
                todoList.loadFromFile();
            }

            if (command.equals("getlastid")) {
                System.out.println(todoList.getLastId());
            }

            if (command.equals("remove")) {
                System.out.print("Which one is removed? ");
                int id = Integer.valueOf(scanner.nextLine());
                todoList.remove(id);
            }

        }
    }
}