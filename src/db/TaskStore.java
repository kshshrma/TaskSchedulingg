package db;

import model.Task;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TaskStore {

    private static final String URL =
            "jdbc:postgresql://localhost:5432/promanage_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "pallu123";

    private Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // ADD TASK
    public void addTask(String title, int deadline, int revenue) {

        String sql = "INSERT INTO projects(title, deadline, revenue) VALUES (?, ?, ?)";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, title);
            ps.setInt(2, deadline);
            ps.setInt(3, revenue);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // VIEW TASKS
    public List<Task> fetchAllTasks() {

        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM projects ORDER BY project_id";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                tasks.add(new Task(
                        rs.getInt("project_id"),
                        rs.getString("title"),
                        rs.getInt("deadline"),
                        rs.getInt("revenue")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return tasks;
    }

    // DELETE TASK
    public void deleteTask(int id) {

        String sql = "DELETE FROM projects WHERE project_id = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("✅ Task deleted successfully.");
            } else {
                System.out.println("❌ Task not found.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
