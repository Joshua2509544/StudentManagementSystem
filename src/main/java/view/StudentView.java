package view;

import controller.StudentController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.Student;
import model.User;

import java.util.List;

public class StudentView {

    private User currentUser;
    private StudentController studentController = new StudentController();

    public StudentView(User user) {
        this.currentUser = user;
    }

    public void show(Stage stage) {

        Label titleLabel = new Label("Manage Students");

        TextArea studentDisplay = new TextArea();
        studentDisplay.setEditable(false);
        studentDisplay.setPrefHeight(300);

        Label addLabel = new Label("Add Student:");

        TextField firstNameField = new TextField();
        firstNameField.setPromptText("First name");

        TextField lastNameField = new TextField();
        lastNameField.setPromptText("Last name");

        TextField emailField = new TextField();
        emailField.setPromptText("Email");

        TextField courseField = new TextField();
        courseField.setPromptText("Course");

        TextField yearField = new TextField();
        yearField.setPromptText("Year");

        Button addBtn = new Button("Add Student");
        Label addMessageLabel = new Label();

        addBtn.setOnAction(e -> {
            if (firstNameField.getText().isEmpty() || lastNameField.getText().isEmpty() ||
                emailField.getText().isEmpty() || courseField.getText().isEmpty() ||
                yearField.getText().isEmpty()) {
                addMessageLabel.setText("Fill in all fields.");
                return;
            }

            int year;
            try {
                year = Integer.parseInt(yearField.getText());
            } catch (NumberFormatException ex) {
                addMessageLabel.setText("Year must be a number.");
                return;
            }

            boolean success = studentController.addStudent(
                firstNameField.getText(),
                lastNameField.getText(),
                emailField.getText(),
                courseField.getText(),
                year
            );

            if (success) {
                addMessageLabel.setText("Student added.");

                firstNameField.clear();
                lastNameField.clear();
                emailField.clear();
                courseField.clear();
                yearField.clear();

                loadStudents(studentDisplay);
            } else {
                addMessageLabel.setText("Error.");
            }
        });

        Label deleteLabel = new Label("Delete Student by ID:");

        TextField deleteIdField = new TextField();
        deleteIdField.setPromptText("Enter student ID");

        Button deleteBtn = new Button("Delete Student");
        Label deleteMessageLabel = new Label();

        deleteBtn.setOnAction(e -> {
            if (deleteIdField.getText().isEmpty()) {
                deleteMessageLabel.setText("Enter a student ID.");
                return;
            }

            int id;
            try {
                id = Integer.parseInt(deleteIdField.getText());
            } catch (NumberFormatException ex) {
                deleteMessageLabel.setText("ID must be a number.");
                return;
            }

            boolean success = studentController.deleteStudent(id);

            if (success) {
                deleteMessageLabel.setText("Student deleted.");
                deleteIdField.clear();
                loadStudents(studentDisplay);
            } else {
                deleteMessageLabel.setText("No student found.");
            }
        });

        Button backBtn = new Button("Back");
        backBtn.setOnAction(e -> {
            DashboardView dashboard = new DashboardView(currentUser);
            dashboard.show(stage);
        });

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(
            titleLabel,
            studentDisplay,
            addLabel,
            firstNameField, lastNameField, emailField, courseField, yearField,
            addBtn, addMessageLabel,
            deleteLabel,
            deleteIdField,
            deleteBtn, deleteMessageLabel,
            backBtn
        );

        Scene scene = new Scene(layout, 500, 650);
        stage.setScene(scene);
        stage.setTitle("Manage Students");

        loadStudents(studentDisplay);
    }

    private void loadStudents(TextArea studentDisplay) {
        List<Student> students = studentController.getAllStudents();

        if (students.isEmpty()) {
            studentDisplay.setText("No students found.");
            return;
        }

        String display = "";
        for (Student s : students) {
            display = display + "ID: " + s.getId()
                    + "  |  Name: " + s.getFullName()
                    + "  |  Email: " + s.getEmail()
                    + "  |  Course: " + s.getCourse()
                    + "  |  Year: " + s.getYear()
                    + "\n";
        }

        studentDisplay.setText(display);
    }
}
