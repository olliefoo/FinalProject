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
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField email;

    @FXML
    private TextField phoneNumber;

    @FXML
    private TextField aStreet;

    @FXML
    private TextField aState;

    @FXML
    private TextField aCity;

    @FXML
    private TextField aZip;

    @FXML
    private Button updateButton;

    @FXML
    private RadioButton gender;

    //Database db = new Database();

    String in = Database.getLoggedIn();
    //have to get the username string here somehow
    User user = Database.getUser(in);

    @FXML
    private void handleDonePressed() throws IOException {

        //set variables of user to input values
        String fName = firstName.getText();
        String lName = lastName.getText();
        String mail = email.getText();
        String pNumber = phoneNumber.getText();
        String street = aStreet.getText();
        String city = aCity.getText();
        String state = aState.getText();
        String zip = aZip.getText();

        user.setName(fName,lName);
        user.setPhone(pNumber);
        user.setAddress(street, city, state, zip);

        Database.updateUser(user);

        Stage stage = (Stage) updateButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../view/AppStartScreen.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }



}
