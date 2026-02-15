package rvt;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        // Here you can try out the combined functionality of your classes
        TodoList2Parts list = new TodoList2Parts();
        Scanner scanner = new Scanner(System.in);

        UserInterface ui = new UserInterface(list, scanner);

        ui.start();
    }
}
