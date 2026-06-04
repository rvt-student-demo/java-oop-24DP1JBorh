package TodoList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TodoDB {

    private static final String DB_URL = "jdbc:sqlite:todo.db";

    public TodoDB() {
        initSchema();
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    private void initSchema() {
        String sql = "CREATE TABLE IF NOT EXISTS todo ("
                + "id INTEGER PRIMARY KEY,"
                + "task TEXT NOT NULL) STRICT";
        try (
                Connection conn = connect();
                Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Schema init failed: " + e.getMessage());
        }
    }

    public void add(String task) throws SQLException {
        String sql = "INSERT INTO todo (task) VALUES (?)";
        try (
                Connection conn = connect();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, task);
            ps.executeUpdate();
        }
        // SQLException не глотаем — пусть распространяется наверх (метод уже throws
        // SQLException)
    }

    public ArrayList<String> findAll() {
        String sql = "SELECT * FROM todo";
        ArrayList<String> tasks = new ArrayList<>();

        try (
                Connection conn = connect();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet res = ps.executeQuery() // ✅ без аргументов
        ) {
            while (res.next()) {
                tasks.add(res.getInt("id") + ": " + res.getString("task")); // по имени колонки надёжнее
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return tasks;
    }

    public void removeById(int id) {
        String sql = "DELETE FROM todo WHERE id = ?";

        try (
                Connection conn = connect();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}