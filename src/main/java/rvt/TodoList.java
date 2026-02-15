package rvt;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.nio.file.Paths;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jegor
 */
public class TodoList {
    private ArrayList<String> tasks;
    private final String filePath = "data/todo.csv";
    private static final Pattern pattern = Pattern.compile("^[a-zA-Z0-9 ]{3,}$");

    public TodoList() {
        this.tasks = new ArrayList<>();
        loadFromFile();
    }

    public void updateFile() {
        try (FileWriter fw = new FileWriter(filePath)) {

            fw.write("id,task");

            for (int i = 0; i < tasks.size(); i++) {
                fw.write("\n" + (i + 1) + "," + tasks.get(i));
            }

            fw.close();
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void loadFromFile() {
        boolean firstLine = true;

        try (Scanner reader = new Scanner(Paths.get(filePath))) {

            while (reader.hasNextLine()) {
                String line = reader.nextLine();

                if (line.isEmpty()) {
                    break;
                }

                if (firstLine) {
                    firstLine = false;
                    continue;
                }

                tasks.add(line.split(",")[1]);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    public void add(String task) {
        tasks.add(task);

        try (FileWriter fw = new FileWriter(filePath, true)) {

            fw.write("\n" + (getLastId() + 1) + "," + task);

            fw.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void print() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + ": " + tasks.get(i));
        }
    }

    public void remove(int id) {
        tasks.remove(id - 1);

        updateFile();
    }

    public int getLastId() {
        return tasks.size() - 1;
    }

    public boolean checkEventString(String value) {
        return pattern.matcher(value).matches();
    }
}