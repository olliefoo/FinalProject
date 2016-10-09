package controller;

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

import model.Database;
import model.User;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button login;

    @FXML
    private Button cancel;

    private Stage dialogStage;

    /**
     * Checks if the username and password entered are valid. The entered
     * username must already exist in the database and the password must
     * correspond to the stored username.
     *
     * @return whether or not the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        String username = usernameField.getText();

        if (username == null || username.length() == 0
                || !Database.containsUsername(username)) {
            errorMessage += "Username does not exist. Please register first.\n";
        } else {
            User currentUser = Database.getUser(username);
            String password = passwordField.getText();

            if (password == null || password.length() == 0
                    || !(password.equals(currentUser.getPassword()))) {
                errorMessage += "Wrong password. Try again.\n";
            }
        }

        //no error message means success / good input
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

    /**
     * Handles the Login button press. When pressed after entering a valid
     * username and password, the user will be lead to the AppStartScreen.
     *
     * @throws IOException
     */
    @FXML
    private void handleLoginPressed() throws IOException {
        if (isInputValid()) {
            User user = Database.getUser(usernameField.getText());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/AppStartScreen.fxml"));
            Stage stage = (Stage) login.getScene().getWindow();
            Parent root = loader.load();
            loader.<AppStartController>getController().setUser(user);
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

    /**
     * Handles the Cancel button pressed. When pressed, the user will be lead
     * back to the WelcomeScreen.
     *
     * @throws IOException
     */
    @FXML
    private void handleCancelPressed() throws IOException {
            Stage stage = (Stage) cancel.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("../view/WelcomeScreen.fxml"));
            stage.setScene(new Scene(root));
            stage.show();
    }

}
