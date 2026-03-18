package rvt;

import java.util.ArrayList;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jegor
 */
public class TodoList2Parts {
    private ArrayList<String> tasks;
    
    public TodoList2Parts() {
        this.tasks = new ArrayList<>();
    }
    
    public void add(String task) {
        tasks.add(task);
    }
    
    public void print() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + ": " + tasks.get(i));
        }
    }
    
    public void remove(int number) {
        tasks.remove(number - 1);
    }
}
