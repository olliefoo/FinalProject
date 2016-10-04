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
    private ComboBox<String> dayField;

    @FXML
    private ComboBox<String> yearField;

    @FXML
    private ComboBox<String> monthField;

    @FXML
    private RadioButton maleRadioButton;

    @FXML
    private RadioButton femaleRadioButton;

    private final ToggleGroup group = new ToggleGroup();

    String currentUsername = MainFXApplication.userList.getLoggedIn();
    User user = MainFXApplication.userList.getUser(currentUsername);

    /**
     * Initializes the ProfileScreen. Sets the values for the comboboxes and
     * show the user's previous edits to their profile.
     *
     */
    @FXML
    private void initialize() {
        maleRadioButton.setToggleGroup(group);
        femaleRadioButton.setToggleGroup(group);
        if (user.getGender()) {
            femaleRadioButton.setSelected(true);
        } else {
            maleRadioButton.setSelected(true);
        }

        firstNameField.setText(user.getFirstname());
        lastNameField.setText(user.getLastname());
        emailField.setText(user.getEmail());
        phoneField.setText(user.getPhone());
        streetField.setText(user.getStreet());
        cityField.setText(user.getCity());
        stateField.setText(user.getState());
        zipField.setText(user.getZip());
        monthField.setValue(user.getMonth());
        dayField.setValue(user.getDay());
        yearField.setValue(user.getYear());

        monthField.getItems().addAll(
                "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep",
                "Oct", "Nov", "Dec"
        );

        dayField.getItems().addAll(
                "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12",
                "13", "14", "15", "16", "17", "18", "19", "20", "21", "22",
                "23", "24", "25", "26", "27", "28", "29", "30", "31"
        );

        yearField.getItems().addAll(
                "2016", "2015", "2014", "2013", "2012", "2011", "2010",
                "2009", "2008", "2007", "2006", "2005", "2004", "2003",
                "2002", "2001", "2000", "1999", "1998", "1997", "1996",
                "1995", "1994", "1993", "1992", "1991", "1990", "1989",
                "1988", "1987", "1986", "1985", "1984", "1983", "1982",
                "1981", "1980", "1979", "1978", "1976", "1975", "1974",
                "1973", "1972", "1971", "1970", "1969", "1968", "1967",
                "1966", "1965", "1964", "1963", "1962", "1961", "1960",
                "1959", "1958", "1957", "1956", "1955", "1954", "1953",
                "1952", "1951", "1950"
        );
    }

    /**
     * Sets the user's attributes using the inputs from the various fields.
     *
     */
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
        String month = monthField.getValue();
        String day = dayField.getValue();
        String year = yearField.getValue();
        boolean gender;
        if (femaleRadioButton.isSelected()) {
            gender = true;
        } else {
            gender = false;
        }

        user.setName(fName,lName);
        user.setPhone(pNumber);
        user.setAddress(street, city, state, zip);
        user.setEmail(mail);
        user.setMonth(month);
        user.setDay(day);
        user.setYear(year);
        user.setGender(gender);
    }

    /**
     * Handles the Update button press. When pressed, the inputs will be saved
     * to the user and the user will be lead back to the AppStartScreen.
     *
     * @throws IOException
     */
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
