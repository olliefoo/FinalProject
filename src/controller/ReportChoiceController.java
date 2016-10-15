package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import model.SourceReport;

import java.io.IOException;

/**
 * Created by cbbjs on 10/8/2016.
 */
public class ReportChoiceController {
    @FXML
    private Button chooseButton;

    @FXML
    private ListView<SourceReport> sourceList;

    @FXML
    private Button returnButton;
    @FXML
    private void initialize() {

    }
    @FXML
    private void handleReturnPressed() throws IOException {
        Stage stage = (Stage) returnButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../view/AppStartScreen.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void handleViewPressed() throws IOException {
        Stage stage = (Stage) chooseButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../view/ViewSourceReportScreen.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

}
