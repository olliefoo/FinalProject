package controller;

import fxapp.MainFXApplication;
import model.User;
import model.Database;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Created by Admins on 9/27/2016.
 */
public class RegistrationController {
    @FXML
    private TextField usernameField;

    @FXML
    private TextField password1Field;

    @FXML
    private TextField password2Field;

    @FXML
    private TextField emailField;

    @FXML
    private Button registerButton;

    @FXML
    private Button cancelButton;

    private Stage dialogStage;

    private boolean isInputValid() {
        String errorMessage = "";
        if (usernameField.getText() == null || usernameField.getText().length() == 0) {
            errorMessage += "Username cannot be blank.";
        }
        if (password1Field.getText() == null || password1Field.getText().length() == 0) {
            errorMessage += "Password cannot be blank.";
        }
        if (emailField.getText() == null || emailField.getText().length() == 0) {
            errorMessage += "Email cannot be blank.";
        }
        if (!(password2Field.getText().equals(password1Field.getText()))) {
            errorMessage += "Verification must match the original password.";
        }
        if (MainFXApplication.userList.containsEmail(emailField.getText())) {
            errorMessage += "email is already being used.";
        }
        if (MainFXApplication.userList.containsUsername(usernameField.getText())) {
            errorMessage += "username is already being used.";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message if bad data
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }



    }

    @FXML
    private void handleRegistrationPressed() throws IOException {
        if (isInputValid()) {
            User newUser = new User(usernameField.getText(), password1Field.getText(), emailField.getText());
            MainFXApplication.userList.add(newUser);
            Stage stage = (Stage) registerButton.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("../view/WelcomeScreen.fxml"));
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

    @FXML
    private void handleCancelPressed() throws IOException {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource
                ("../view/WelcomeScreen.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }
}
