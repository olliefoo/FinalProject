package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;
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

    private User user;

    /**
     * Checks if the username and password entered are valid. The entered
     * username must already exist in the database and the password must
     * correspond to the stored username.
     *
     * @return whether or not the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        try {
            user = User.selectUser(usernameField.getText());
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }

        if(user == null) {
            errorMessage += "Username does not exist. Please register first.\n";
        } else if(!user.getPassword().equals(passwordField.getText())) {
            errorMessage += "Wrong password. Try again.\n";
        }

        //no error message means success / good input
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message if bad data
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }

    /**
     * Handles the Login button press. When pressed after entering a valid
     * username and password and account type, the user will be lead to a start
     * screen depending on what type of user they are.
     *
     * @throws IOException
     */
    @FXML
    private void handleLoginPressed() throws IOException {
        if (isInputValid()) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass()
                    .getResource("../view/AppStartScreen.fxml"));

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
            Parent root = FXMLLoader.load(getClass()
                    .getResource("../view/WelcomeScreen.fxml"));
            stage.setScene(new Scene(root));
            stage.show();
    }

}
