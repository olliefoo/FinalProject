package controller;

import fxapp.MainFXApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Owner on 9/22/2016.
 */
public class WelcomeController {

    private MainFXApplication mainApplication;

    @FXML
    private Button loginButton;

    @FXML
    private Button registerButton;

    // set up link to the MainFXApplication
    public void setMainApp(MainFXApplication mainFXApplication) {
        mainApplication = mainFXApplication;
    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        Stage stage = mainApplication.getMainStage();
        Parent root = null;

        if (event.getSource() == loginButton) {
            stage = (Stage) loginButton.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("../view/LoginScreen.fxml"));
        }
        // implement register handler in else statement here

        stage.setScene(new Scene(root));
        stage.show();
    }

}
