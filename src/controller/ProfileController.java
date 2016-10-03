package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Database;
import model.User;

import java.io.IOException;

public class ProfileController {

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField streetField;

    @FXML
    private TextField stateField;

    @FXML
    private TextField cityField;

    @FXML
    private TextField zipField;

    @FXML
    private Button updateButton;

    @FXML
    private RadioButton gender;


    String in = Database.getLoggedIn();
    User user = Database.getUser(in);

    @FXML
    private void initialize() {
        firstNameField.setText(user.getFirstname());
        lastNameField.setText(user.getLastname());
        emailField.setText(user.getEmail());
        phoneField.setText(user.getPhone());
    }


    private void setValues() {
        //set variables of user to input values
        String fName = firstNameField.getText();
        String lName = lastNameField.getText();
        String mail = emailField.getText();
        String pNumber = phoneField.getText();
        String street = streetField.getText();
        String city = cityField.getText();
        String state = stateField.getText();
        String zip = zipField.getText();

        user.setName(fName,lName);
        user.setPhone(pNumber);
        user.setAddress(street, city, state, zip);
        user.setEmail(mail);
    }

    @FXML
    private void handleDonePressed() throws IOException {
        Database.updateUser(user);

        Stage stage = (Stage) updateButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../view/AppStartScreen.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }



}
