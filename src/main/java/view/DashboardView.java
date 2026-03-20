package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.User;

public class DashboardView {

    private User currentUser;

    public DashboardView(User user) {
        this.currentUser = user;
    }

    public void show(Stage stage) {

        Label titleLabel = new Label("Dashboard");
        Label welcomeLabel = new Label("Welcome, " + currentUser.getUsername() + "!");

        Button manageStudentsBtn = new Button("Manage Students");
        Button logoutBtn = new Button("Logout");

        manageStudentsBtn.setOnAction(e -> {
            StudentView studentView = new StudentView(currentUser);
            studentView.show(stage);
        });

        logoutBtn.setOnAction(e -> {
            LoginView loginView = new LoginView();
            loginView.show(stage);
        });

        VBox layout = new VBox(15);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(30));
        layout.getChildren().addAll(titleLabel, welcomeLabel, manageStudentsBtn, logoutBtn);

        Scene scene = new Scene(layout, 400, 300);
        stage.setScene(scene);
        stage.setTitle("Dashboard");
    }
}
