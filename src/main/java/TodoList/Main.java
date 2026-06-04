package TodoList;

import java.sql.Statement;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try (
            Connection connection = 
            DriverManager.getConnection(
                "jdbc:sqlite:todo.db"
        );
        Statement statement = connection.createStatement();
    )

    {
        String sql = "CREATE TABLE IF NOT EXISTS todo"
        + "(id INTEGER PRIMARY KEY, task TEXT NOT NULL) STRICT";
        statement.executeUpdate(sql);
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }

    TodoDB db = new TodoDB();
    Scanner scanner = new Scanner(System.in);
    UserInterface ui = new UserInterface(db, scanner);
    ui.start();
    }
}