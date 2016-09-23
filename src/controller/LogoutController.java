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

public class LogoutController {

    private MainFXApplication mainApplication;
    @FXML
    private Button logout;

    public void setMainApp(MainFXApplication mainFXApplication) {
        mainApplication = mainFXApplication;
    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        Stage stage = mainApplication.getMainStage();
        Parent root = null;

        if (event.getSource() == logout) {
            stage = (Stage) logout.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("../view/WelcomeScreen.fxml"));
        }
        // implement register handler in else statement here

        stage.setScene(new Scene(root));
        stage.show();
    }
}
