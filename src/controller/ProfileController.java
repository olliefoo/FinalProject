package controller;

import fxapp.MainFXApplication;
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
    private ComboBox dayField;

    @FXML
    private ComboBox yearField;

    @FXML
    private ComboBox monthField;

    //@FXML
    //private RadioButton gender;


    String currentUsername = MainFXApplication.userList.getLoggedIn();
    User user = MainFXApplication.userList.getUser(currentUsername);

    @FXML
    private void initialize() {
        firstNameField.setText(user.getFirstname());
        lastNameField.setText(user.getLastname());
        emailField.setText(user.getEmail());
        phoneField.setText(user.getPhone());
        streetField.setText(user.getStreet());
        cityField.setText(user.getCity());
        stateField.setText(user.getState());
        zipField.setText(user.getZip());

        monthField.getItems().addAll(
                "Jan", "Feb", "Mar", "Apr", "May", "June", "July",
                "Aug", "Sept", "Oct", "Nov", "Dec"
        );

        dayField.getItems().addAll(
                "1", "2", "3", "4", "5", "6", "7",
                "8", "9", "10", "11", "12", "13", "14",
                "15", "16", "17", "18", "19", "20", "21",
                "22", "23", "24", "25", "26", "27", "28",
                "29", "30", "31", "32"
        );

        yearField.getItems().addAll(
                "1950", "1951", "1952", "1953", "1954", "1955", "1956",
                "1957", "1958", "1959", "1960", "1961", "1962", "1963",
                "1964", "1965", "1966", "1967", "1968", "1969", "1970",
                "1971", "1972", "1973", "1974", "1975", "1976",
                "1977", "1978", "1979", "1980", "1981", "1982", "1983",
                "1984", "1985", "1986", "1987", "1988", "1989", "1990",
                "1991", "1992", "1993", "1994", "1995", "1996",
                "1997", "1998", "1999", "2000", "2001", "2002", "2003",
                "2004", "2005", "2006", "2007", "2008", "2009", "2010",
                "2011", "2012", "2013", "2014", "2015", "2016"
        );
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
    private void handleUpdatePressed() throws IOException {
        setValues();
        MainFXApplication.userList.updateUser(user);
        Stage stage = (Stage) updateButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../view/AppStartScreen.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }



}
