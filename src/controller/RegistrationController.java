package controller;

import fxapp.MainFXApplication;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import model.User;
import model.Worker;
import model.Manager;
import model.Admin;
import model.userType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    private ComboBox choiceBox;

    @FXML
    private Button cancelButton;

    private Stage dialogStage;

    @FXML
    private void initialize() {
        choiceBox.setItems(FXCollections.observableArrayList(userType.values()));
        choiceBox.setValue(userType.U);
    }

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
            if (choiceBox.getValue().equals(userType.U)) {
                User newUser = new User(usernameField.getText(), password1Field.getText(), emailField.getText());
                MainFXApplication.userList.add(newUser);
            }
            if (choiceBox.getValue().equals(userType.W)) {
                User newWorker = new Worker(usernameField.getText(), password1Field.getText(), emailField.getText());
                MainFXApplication.userList.add(newWorker);
            }
            if (choiceBox.getValue().equals(userType.M)) {
                User newManager = new Manager(usernameField.getText(), password1Field.getText(), emailField.getText());
                MainFXApplication.userList.add(newManager);
            }
            if (choiceBox.getValue().equals(userType.A)) {
                User newAdmin = new Admin(usernameField.getText(), password1Field.getText(), emailField.getText());
                MainFXApplication.userList.add(newAdmin);
            }
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
