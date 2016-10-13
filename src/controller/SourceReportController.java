package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Database;
import model.Profile;
import model.SourceReport;
import model.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class SourceReportController {
    @FXML
    private TextField reportDate;

    @FXML
    private TextField reportTime;

    @FXML
    private TextField reportNumber;

    @FXML
    private TextField reporterNameField;

    @FXML
    private TextField waterLocationField;

    @FXML
    private ComboBox<String> waterTypeCombo;

    @FXML
    private TextField otherTypeField;

    @FXML
    private ComboBox<String> waterConditionCombo;

    @FXML
    private Button submitSourceButton;

    @FXML
    private Button cancelButton;

    private Stage dialogStage;

    private User user;
    private Profile profile;
    private SourceReport r = new SourceReport();

    public void setUser(User u) {
        user = u;
        profile = user.getProfile();
        setup();
    }

    private void setup() {
        reporterNameField.setText(profile.getFirstname() + " "
                + profile.getLastname());

        reportNumber.setText("" + r.getNumber());

        SimpleDateFormat ft1 = new SimpleDateFormat("E MM/dd/yyyy");
        SimpleDateFormat ft2 = new SimpleDateFormat("h:mm a");
        reportDate.setText(ft1.format(r.getFullDate()));
        reportTime.setText(ft2.format(r.getFullDate()));

        waterTypeCombo.getItems().addAll("Bottled", "Well", "Stream",
                "Lake", "Spring", "Other");
        waterTypeCombo.setValue("Bottled");

        waterConditionCombo.getItems().addAll("Waste", "Treatable-Clear",
                "Treatable-Muddy", "Potable");
        waterConditionCombo.setValue("Waste");
    }

    private void setReportValues() {
        String date = reportDate.getText();
        String time = reportTime.getText();
        //String number = reportNumber.getText();
        String name = reporterNameField.getText();
        String location = waterLocationField.getText();

        String type = waterTypeCombo.getValue();
        if (type.equals("Other")) {
            type = otherTypeField.getText();
        }

        String condition = waterConditionCombo.getValue();
        r.setReporter(user);
        r.setTime(time);
        r.setDate(date);
        r.setName(name);
        r.setLocation(location);
        r.setType(type);
        r.setCondition(condition);
    }

    private boolean isInputValid() {
        String errorMessage = "";

        String location = waterLocationField.getText();
        String other = otherTypeField.getText();
        String type = waterTypeCombo.getValue();

        if (type.equals("Other") && other.length() == 0) {
            errorMessage += "Please specify other.\n";
        }
        if (location.length() == 0) {
            errorMessage += "Please enter a location.\n";
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

    /**
     * Handles the Submit button press. When pressed, the inputs will be saved
     * to the user and the user will be lead back to the AppStartScreen.
     *
     * @throws IOException
     */
    @FXML
    private void handleReportSubmitPressed() throws IOException {
        if (isInputValid()) {
            setReportValues();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/AppStartScreen.fxml"));
            Stage stage = (Stage) submitSourceButton.getScene().getWindow();
            Parent root = loader.load();
            loader.<AppStartController>getController().setUser(user);
            stage.setScene(new Scene(root));
            stage.show();
        }
    }
    /**
     * The method that is used when the cancel button is pressed.
     * It will lead the user back to the app start screen screen.
     * @throws IOException
     */
    @FXML
    private void handleCancelPressed() throws IOException {
        // if cancel, then report number does not increment
        SourceReport.setTotal(SourceReport.getTotal() - 1);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/AppStartScreen.fxml"));
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        Parent root = loader.load();
        loader.<AppStartController>getController().setUser(user);
        stage.setScene(new Scene(root));
        stage.show();
    }
}