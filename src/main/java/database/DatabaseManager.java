package database;

import model.Student;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {

    private static final String URL = "jdbc:mysql://localhost:3306/studentdb";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "NewPassword123!";

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, DB_USER, DB_PASS);
    }

    public User validateUser(String username, String password) {
        String query = "SELECT id, username, role FROM users WHERE username = ? AND password = ?";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new User(rs.getInt("id"), rs.getString("username"), rs.getString("role"));
            }

        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return null;
    }

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM students";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Student s = new Student(
                    rs.getInt("id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("email"),
                    rs.getString("course"),
                    rs.getInt("year")
                );
                students.add(s);
            }

        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return students;
    }

    public boolean addStudent(Student student) {
        String query = "INSERT INTO students (first_name, last_name, email, course, year) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, student.getFirstName());
            stmt.setString(2, student.getLastName());
            stmt.setString(3, student.getEmail());
            stmt.setString(4, student.getCourse());
            stmt.setInt(5, student.getYear());
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            return false;
        }
    }


    public boolean updateStudent(Student student) {
        String query = "UPDATE students SET first_name=?, last_name=?, email=?, course=?, year=? WHERE id=?";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, student.getFirstName());
            stmt.setString(2, student.getLastName());
            stmt.setString(3, student.getEmail());
            stmt.setString(4, student.getCourse());
            stmt.setInt(5, student.getYear());
            stmt.setInt(6, student.getId());
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteStudent(int studentId) {
        String query = "DELETE FROM students WHERE id = ?";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, studentId);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            return false;
        }
    }
}
