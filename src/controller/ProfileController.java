package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
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
    private Button updateButton;

    @FXML
    private RadioButton gender;



    @FXML
    private void handleDonePressed() throws IOException {

        //set variables of user to input values
        firstName.getText();
        lastName.getText();
        email.getText();
        phoneNumber.getText();
        updateButton.getText();


        Stage stage = (Stage) updateButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../view/AppStartScreen.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }



}
