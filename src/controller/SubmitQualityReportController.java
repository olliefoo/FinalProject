package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;


/**
 * Created by miles on 10/31/2016.
 */
public class SubmitQualityReportController {
    @FXML
    private TextField reportDate;

    @FXML
    private TextField reportTime;

    @FXML
    private TextField reportNumber;

    @FXML
    private TextField workerNameField;

    @FXML
    private TextField waterLocationField;

    @FXML
    private TextField latField;

    @FXML
    private TextField longField;

    @FXML
    private TextField waterCondField;

    @FXML
    private TextField virusField;

    @FXML
    private TextField contaminantField;

    @FXML
    private TextField longitudeField;

    @FXML
    private TextField latitudeField;

    @FXML
    private Slider latitudeSlider;

    @FXML
    private Slider longitudeSlider;

    @FXML
    private ComboBox<String> conditionCombo;

    @FXML
    private Button submitPurityButton;

    @FXML
    private Button cancelButton;

    private Stage dialogStage;

    private User user;
    private Profile profile;
    private QualityReport r = new QualityReport();

    /**
     * sets the current user of this source report
     * @param u the user working on this source report
     */
    public void setUser(User u) {
        user = u;
        profile = user.getProfile();
        setup();
    }

    /**
     * Inserts the initial inputs for the source report view
     */
    private void setup() {
        workerNameField.setText(profile.getFirstname() + " "
                + profile.getLastname());

        reportNumber.setText("" + r.getNumber());

        SimpleDateFormat ft1 = new SimpleDateFormat("E MM/dd/yyyy");
        SimpleDateFormat ft2 = new SimpleDateFormat("h:mm a");
        reportDate.setText(ft1.format(r.getFullDate()));
        reportTime.setText(ft2.format(r.getFullDate()));


        conditionCombo.getItems().addAll("Waste", "Treatable-Clear",
                "Treatable-Muddy", "Potable");
        conditionCombo.setValue("Waste");

        latitudeSlider.setValue(0);
        longitudeSlider.setValue(0);
        latitudeField.setText(Double.toString(0));
        longitudeField.setText(Double.toString(0));
        latitudeField.textProperty().bindBidirectional(
                latitudeSlider.valueProperty(),
                NumberFormat.getNumberInstance());
        longitudeField.textProperty().bindBidirectional(
                longitudeSlider.valueProperty(),
                NumberFormat.getNumberInstance());
    }

    /**
     * Sets the values from the view to the source report
     */
    private void setReportValues() {
        String date = reportDate.getText();
        String time = reportTime.getText();
        //String number = reportNumber.getText();
        String name = workerNameField.getText();
        String location = waterLocationField.getText();
        double latitude = latitudeSlider.getValue();
        double longitude = longitudeSlider.getValue();



        String condition = conditionCombo.getValue();
        r.setReporter(user);
        r.setTime(time);
        r.setDate(date);
        r.setName(name);
        r.setLocation(location);
        r.setCondition(condition);
        r.setLatitude(latitude);
        r.setLongitude(longitude);
    }

    /**
     * Checks to see if the inputs on the source report screen are valid.
     * @return true if valid, false otherwise
     */
    private boolean isInputValid() {
        String errorMessage = "";

        String location = waterLocationField.getText();
        String virus = virusField.getText();
        String contamin = contaminantField.getText();
        if (virus.length() == 0) {
            errorMessage += "Please enter a Virus PPM value.\n";
        }
        if (contamin.length() == 0) {
            errorMessage += "Please enter a contamination value.\n";
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
            alert.setHeaderText("Please address invalid fields");
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
            ReportDatabase.add(r);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/AppStartScreen.fxml"));
            Stage stage = (Stage) submitPurityButton.getScene().getWindow();
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
        QualityReport.setTotal(QualityReport.getTotal() - 1);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/AppStartScreen.fxml"));
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        Parent root = loader.load();
        loader.<AppStartController>getController().setUser(user);
        stage.setScene(new Scene(root));
        stage.show();
    }

}
