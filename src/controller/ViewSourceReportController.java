package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * Created by miles on 10/14/2016.
 */
public class ViewSourceReportController {
    @FXML
    private TextField reportDateField;

    @FXML
    private TextField reportTimeField;

    @FXML
    private TextField reportNumberField;

    @FXML
    private TextField reporterNameField;

    @FXML
    private TextField waterLocationField;

    @FXML
    private TextField waterTypeField;

    @FXML
    private TextField waterCondField;

    @FXML
    private Button backButton;

    private User user;
    private SourceReport sourceReport;

    /**
     *Fills in text boxes
     */
    private void setUp () {
        //reportDateField.setText(sourceReport.getDate());
        //reportTimeField.setText(sourceReport.getTime());
        //reportNumberField.setText(sourceReport.getNumber());
        //reporterNameField.setText(sourceReport.getReporterName());
        //waterLocationField.setText(sourceReport.getLocation());
        //waterTypeField.setText(sourceReport.getType());
        //waterCondField.setText(sourceReport.getCondition());
    }

    /**
     * Handles the Back button press. Returns user to Source Report List
     * Screen
     *
     * @throws IOException
     */
    @FXML
    private void handleBackPressed() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource
                ("../view/SourceReportListScreen.fxml"));
        Stage stage = (Stage) backButton.getScene().getWindow();
        Parent root = loader.load();
        //loader.<SourceReportListController>getController().setUser(user);
        stage.setScene(new Scene(root));
        stage.show();
    }

}
