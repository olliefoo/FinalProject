package controller;

import fxapp.MainFXApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.Database;

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

    /**
     * Handles Login button press. When pressed, leads the user to the
     * LoginScreen.
     *
     * @throws IOException
     */
    @FXML
    private void handleLoginPressed() throws IOException {
            Database.loadAll();
            Stage stage = (Stage) loginButton.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass()
                    .getResource("../view/LoginScreen.fxml"));
            stage.setScene(new Scene(root));
            stage.show();
    }

    /**
     * Handles the Registration button press. When pressed, leads the user to
     * the RegistrationScreen.
     *
     * @throws IOException
     */
    @FXML
    private void handleRegistrationPressed() throws IOException {
        Database.loadAll();
        Stage stage = (Stage) registerButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass()
                .getResource("../view/RegistrationScreen.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }
 /*   private void handleButtonAction(ActionEvent event) throws IOException {
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
*/

}
