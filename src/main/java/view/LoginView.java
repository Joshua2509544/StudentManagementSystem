package view;

import database.DatabaseManager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.User;

public class LoginView {

    private DatabaseManager dbManager = new DatabaseManager();

    public void show(Stage stage) {

        Label titleLabel = new Label("Student Management System");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        Button loginButton = new Button("Login");
        Label messageLabel = new Label();

        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            if (username.isEmpty() || password.isEmpty()) {
                messageLabel.setText("Enter a username and password.");
                return;
            }

            User user = dbManager.validateUser(username, password);

            if (user != null) {
                DashboardView dashboard = new DashboardView(user);
                dashboard.show(stage);
            } else {
                messageLabel.setText("Invalid username or password.");
                passwordField.clear();
            }
        });

        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(30));
        layout.getChildren().addAll(titleLabel, usernameField, passwordField, loginButton, messageLabel);

        Scene scene = new Scene(layout, 400, 300);
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.show();
    }
}
