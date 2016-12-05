package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
//import model.Database;
import model.Profile;
import model.User;

import java.io.IOException;
import java.sql.SQLException;

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

    private Stage dialogStage;

    private final ToggleGroup group = new ToggleGroup();

    private User user;

    /**
     * Sets the current user in the controller
     * @param u the current user
     */
    public void setUser(User u) {
        user = u;
        setup();
    }

    /**
     * Fill in the textboxes/comboboxes with the previous stored Profile values
     *
     */
    private void setup() {
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
        streetField.setText(user.getAddress());
        cityField.setText(user.getCity());
        stateField.setText(user.getState());
        zipField.setText(user.getZip());
        monthField.setValue(user.getMonth());
        dayField.setValue(user.getDay());
        yearField.setValue(user.getYear());

        monthField.getItems().addAll(
                "January", "February", "March", "April", "May", "June", "July",
                "August", "September", "October", "November", "December"
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
        user.setEmail(emailField.getText());
        user.setAddress(streetField.getText());
        user.setCity(cityField.getText());
        user.setState(stateField.getText());
        user.setZip(zipField.getText());
        user.setPhone(phoneField.getText());
        user.setMonth(monthField.getValue());
        user.setDay(dayField.getValue());
        user.setYear(yearField.getValue());
        user.setGender(femaleRadioButton.isSelected());

        try {
            user.update();
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
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
        //Database.saveAll();
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("../view/AppStartScreen.fxml"));
        Stage stage = (Stage) updateButton.getScene().getWindow();
        Parent root = loader.load();
        loader.<AppStartController>getController().setUser(user);
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void handleBackPressed() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("../view/AppStartScreen.fxml"));
        Stage stage = (Stage) updateButton.getScene().getWindow();
        Parent root = loader.load();
        loader.<AppStartController>getController().setUser(user);
        stage.setScene(new Scene(root));
        stage.show();
    }

}
