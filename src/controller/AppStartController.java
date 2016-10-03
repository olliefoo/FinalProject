package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;


public class AppStartController {

    @FXML
    private Button myLogout;

    @FXML
    private Button profile;

    @FXML
    private void handleLogoutPressed() throws IOException {
        Stage stage = (Stage) myLogout.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../view/WelcomeScreen.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void handleProfilePressed() throws IOException {
        Stage stage = (Stage) profile.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../view/ProfileScreen.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }
}
