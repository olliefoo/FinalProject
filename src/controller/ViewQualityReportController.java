package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.QualityReport;
import model.User;
import java.io.IOException;

/**
 * Created by miles on 10/31/2016.
 */
public class ViewQualityReportController {
    @FXML
    private TextField reportDateField;

    @FXML
    private TextField reportTimeField;

    @FXML
    private TextField reportNumberField;

    @FXML
    private TextField workerNameField;

    @FXML
    private TextField waterLocationField;

    @FXML
    private TextField latField;

    @FXML
    private TextField longField;

    @FXML
    private TextField conditionField;

    @FXML
    private TextField virusField;

    @FXML
    private TextField contaminantField;

    @FXML
    private Button backButton;

    private User user;

    private QualityReport qualityReport;

    public void setup(User u, QualityReport r) {
        user = u;
        qualityReport = r;
        fillInformation();
    }

    /**
     *Fills the textboxes with the information from the quality report.
     */
    private void fillInformation () {
        reportDateField.setText(qualityReport.getDate());
        reportTimeField.setText(qualityReport.getTime());
        reportNumberField.setText(Integer.toString(qualityReport.getNumber()));
        workerNameField.setText(qualityReport.getName());
        waterLocationField.setText(qualityReport.getLocation());
        conditionField.setText(qualityReport.getCondition());
        latField.setText(Double.toString(qualityReport.getLatitude()));
        longField.setText(Double.toString(qualityReport.getLongitude()));
        virusField.setText(qualityReport.getVirus());
        contaminantField.setText(qualityReport.getContaminant());
    }

    /**
     * Handles the Back button press. Returns user to Quality Report List
     * Screen
     *
     * @throws IOException
     */
    @FXML
    private void handleBackPressed() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource
                ("../view/QualityReportListScreen.fxml"));
        Stage stage = (Stage) backButton.getScene().getWindow();
        Parent root = loader.load();
        loader.<QualityReportListController>getController().setUser(user);
        stage.setScene(new Scene(root));
        stage.show();
    }
}
