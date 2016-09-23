package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button loginButton;

    private Stage dialogStage;

    private boolean isInputValid() {
        String errorMessage = "";

        if (username.getText() == null || username.getText().length() == 0
                || !username.getText().equals("user")) {
            errorMessage += "Username does not exist.\n";
        }
        if (password.getText() == null || password.getText().length() == 0
                || !password.getText().equals("pass")) {
            errorMessage += "Wrong password. Try again.\n";
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
}
