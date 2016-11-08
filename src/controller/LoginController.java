package controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;

import model.Database;
import model.ReportDatabase;
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

    @FXML
    private ComboBox accountBox;

    /**
     * Initialize method called when controller is loaded. Used to set values
     * for the account type combobox. Sets the default choice as USER.
     *
     */
    @FXML
    private void initialize() {
        accountBox.getItems().addAll("USER", "WORKER", "MANAGER", "ADMIN");
        accountBox.setValue("USER");
        accountBox.setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                accountBox.requestFocus();
            }
        });
    }

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
        String password = passwordField.getText();

        User user = Database.getUser(username);
        if (accountBox.getValue().equals("WORKER")) {
            user = Database.getWorker(username);
        } else if (accountBox.getValue().equals("MANAGER")) {
            user = Database.getManager(username);
        } else if (accountBox.getValue().equals("ADMIN")) {
            user = Database.getAdmin(username);
        }

        if (username == null || username.length() == 0
                || !Database.containsUsername(username)) {
            errorMessage += "Username does not exist. Please register first.\n";
        } else if (user == null) {
            errorMessage += "Wrong account type. Try Again.\n";
        } else {
            if (password == null || password.length() == 0
                    || !(password.equals(user.getPassword()))) {
                errorMessage += "Wrong password. Try again.\n";
            }
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
            ReportDatabase.getInstance().loadSource();
            ReportDatabase.getInstance().loadQuality();
            User user = Database.getUser(usernameField.getText());
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass()
                    .getResource("../view/AppStartScreen.fxml"));

            if (accountBox.getValue().equals("WORKER")) {
                user = Database.getWorker(usernameField.getText());
                //loader.setLocation(getClass().getResource("../view/AppStartScreen2.fxml"));
            } else if (accountBox.getValue().equals("MANAGER")) {
                user = Database.getManager(usernameField.getText());
                //loader.setLocation(getClass().getResource("../view/AppStartScreen3.fxml"));
            } else if (accountBox.getValue().equals("ADMIN")) {
                user = Database.getAdmin(usernameField.getText());
                //loader.setLocation(getClass().getResource("../view/AppStartScreen4.fxml"));
            }
            //FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/AppStartScreen.fxml"));

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
