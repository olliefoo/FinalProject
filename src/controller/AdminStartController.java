package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Owner on 12/6/2016.
 */
public class AdminStartController {

    @FXML
    private Button logoutButton;

    @FXML
    private Hyperlink viewLink;

    @FXML
    private void handleLogoutPressed() throws IOException {
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass()
                .getResource("../view/WelcomeScreen.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void handleViewPressed() throws IOException {
        Stage stage = (Stage) viewLink.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass()
                .getResource("../view/UserListView.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }
}
