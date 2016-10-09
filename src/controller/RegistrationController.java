package controller;

import fxapp.MainFXApplication;
import javafx.scene.control.*;
import model.*;
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

    /**
     * Method to initialize the registration page. Mainly used
     * to make sure the combo box have choices, and the User to be
     * the default type.
     */
    @FXML
    private void initialize() {
        choiceBox.getItems().addAll("USER", "WORKER", "MANAGER", "ADMIN");
        choiceBox.setValue("USER");
    }

    /**
     * Method that makes sure all the inputs for registration does not break
     * any rules. Also makes sure if the username or email used is unique.
     * @return The boolean whether the registration process broke rules or not.
     */
    private boolean isInputValid() {
        String errorMessage = "";
        if (usernameField.getText() == null || usernameField.getText().length() == 0) {
            errorMessage += "Username cannot be blank\n";
        } else if (password1Field.getText() == null || password1Field.getText().length() == 0) {
            errorMessage += "Password cannot be blank\n";
        } else if (emailField.getText() == null || emailField.getText().length() == 0) {
            errorMessage += "Email cannot be blank\n";
        } else if (!(password2Field.getText().equals(password1Field.getText()))) {
            errorMessage += "Verification must match the original password\n";
        } else if (Database.containsEmail(emailField.getText())) {
            errorMessage += "Email is already being used\n";
        } else if (Database.containsUsername(usernameField.getText())) {
            errorMessage += "Username is already being used\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message if bad data
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please Correct Invalid Fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }



    }

    /**
     * The method that handles when the register button is pressed.
     * It will add the user to the main database arraylist and lead the user
     * to the appstart page.
     * @throws IOException When something goes wrong.
     */
    @FXML
    private void handleRegistrationPressed() throws IOException {
        if (isInputValid()) {
            if (choiceBox.getValue().equals("USER")) {
                Database.add(new User(usernameField.getText(), password1Field.getText(), emailField.getText()));
                //MainFXApplication.userList.add(newUser);
                //Database.add(newUser);
            }
            if (choiceBox.getValue().equals("WORKER")) {
                Database.add(new Worker(usernameField.getText(), password1Field.getText(), emailField.getText()));
                //MainFXApplication.userList.add(newWorker);
                //Database.add(newWorker);
            }
            if (choiceBox.getValue().equals("MANAGER")) {
                Database.add(new Manager(usernameField.getText(), password1Field.getText(), emailField.getText()));
                //MainFXApplication.userList.add(newManager);
                //Database.add(newManager);
            }
            if (choiceBox.getValue().equals("ADMIN")) {
                Database.add(new Admin(usernameField.getText(), password1Field.getText(), emailField.getText()));
                //MainFXApplication.userList.add(newAdmin);
            }

            Database.addEmail(emailField.getText());
            Database.addUsername(usernameField.getText());

            Stage stage = (Stage) registerButton.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("../view/WelcomeScreen.fxml"));
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

    /**
     * The method that is used when the cancel button is pressed.
     * It will lead the user back to the welcome screen.
     * @throws IOException
     */
    @FXML
    private void handleCancelPressed() throws IOException {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../view/WelcomeScreen.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }
}
