package controller;

import javafx.scene.control.*;
import model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private TextField firstnameField, lastnameField;

    @FXML
    private Button registerButton;

    @FXML
    private ComboBox<String> choiceBox;

    @FXML
    private Button cancelButton;

    @FXML
    private Hyperlink loginLink;

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);


    /**
     * Method to initialize the registration page. Mainly used
     * to make sure the combo box have choices, and the User to be
     * the default type.
     */
    @FXML
    private void initialize() {
        choiceBox.getItems().addAll("USER", "WORKER", "MANAGER");
        choiceBox.setValue("USER");
        choiceBox.setOnMousePressed(event -> choiceBox.requestFocus());
    }

    /**
     * Method that makes sure all the inputs for registration does not break
     * any rules. Also makes sure if the username or email used is unique.
     * @return The boolean whether the registration process broke rules or not.
     */
    private boolean isInputValid() {
        String errorMessage = "";
        if(firstnameField.getText() == null
                || firstnameField.getText().length() == 0) {
            errorMessage += "First name cannot be blank\n";
        } else if(lastnameField.getText() == null
                || lastnameField.getText().length() == 0) {
            errorMessage += "Last name cannot be blank\n";
        } else if (usernameField.getText() == null
                || usernameField.getText().length() == 0) {
            errorMessage += "Username cannot be blank\n";
        } else if (password1Field.getText() == null
                || password1Field.getText().length() == 0) {
            errorMessage += "Password cannot be blank\n";
        } else if (emailField.getText() == null
                || emailField.getText().length() == 0) {
            errorMessage += "Email cannot be blank\n";
        } else if (!(password2Field.getText()
                .equals(password1Field.getText()))) {
            errorMessage += "Verification must match the original password\n";
        }

        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailField.getText());
        if(!matcher.find()) {
            errorMessage += "Please enter a valid email\n";
        }

        try {
            for (User u : User.selectAllUsers()) {
                if (u.getEmail().equals(emailField.getText())) {
                    errorMessage += "Email is already used\n";
                } else if (u.getUsername().equals(usernameField.getText())) {
                    errorMessage += "Username is already used\n";
                }
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message if bad data
            Alert alert = new Alert(Alert.AlertType.ERROR);
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
     * to the app start page.
     * @throws IOException When something goes wrong.
     */
    @FXML
    private void handleRegistrationPressed() throws IOException {
        if (isInputValid()) {
            boolean isWorker = false;
            boolean isManager = false;
            if(choiceBox.getValue().equals("WORKER")) {
                isWorker = true;
            } else if(choiceBox.getValue().equals("MANAGER")) {
                isManager = true;
            }

            User user = new User(usernameField.getText(),
                    password1Field.getText(), emailField.getText(),
                    firstnameField.getText(), lastnameField.getText(), isWorker,
                    isManager);
            try {
                user.insert();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass()
                    .getResource("../view/AppStartScreen.fxml"));
            Stage stage = (Stage) registerButton.getScene().getWindow();
            Parent root = loader.load();

            loader.<AppStartController>getController().setUser(user);
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
        Parent root = FXMLLoader.load(getClass()
                .getResource("../view/WelcomeScreen.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Leads the user to the login screen when the Sign In link is pressed
     * @throws IOException
     */
    @FXML private void handleLoginPressed() throws IOException {
        Stage stage = (Stage) loginLink.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass()
                .getResource("../view/LoginScreen.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }
}
