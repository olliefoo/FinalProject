package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.User;
import model.QualityReport;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by miles on 10/31/2016.
 */
public class QualityReportListController {

    @FXML
    private Button chooseButton, returnButton, deleteButton;

    @FXML
    private
    TableView<QualityReport> table;

    @FXML
    private
    TableColumn<QualityReport, Integer> numberCol;

    @FXML
    private
    TableColumn<QualityReport, String> locationCol;

    @FXML
    private
    TableColumn<QualityReport, String> dateCol;

    @FXML
    private
    TableColumn<QualityReport, String> userCol;

    @FXML
    private ListView<String> qualityList;

    private User user;

    ObservableList<QualityReport> list = FXCollections.observableArrayList();

    /**
     * Generates the list of reports to be shown on the Quality Report List
     * screen.
     */
    @FXML
    private void initialize() {
        try {
            for(QualityReport r : QualityReport.selectAllReports()) {
                list.add(r);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        numberCol.setCellValueFactory(new PropertyValueFactory<QualityReport, Integer>("number"));
        locationCol.setCellValueFactory(new PropertyValueFactory<QualityReport, String>("location"));
        dateCol.setCellValueFactory(new PropertyValueFactory<QualityReport, String>("date"));
        userCol.setCellValueFactory(new PropertyValueFactory<QualityReport,String>("username"));
        table.getItems().setAll(list);

    }

    /**
     * Sets User to the user that navigated to the page.
     * @param u user that navigated to page
     */
    public void setUser(User u) {
        user = u;
        if(!user.isManager()) {
            deleteButton.setVisible(false);
        }
    }

    /**
     * Handles the return button pressed. When pressed, user will be returned
     * to the App start screen.
     * @throws IOException
     */
    @FXML
    private void handleReturnPressed() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("../view/AppStartScreen.fxml"));
        Stage stage = (Stage) returnButton.getScene  ().getWindow();
        Parent root = loader.load();
        loader.<AppStartController>getController().setUser(user);
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Handles the view button pressed. When pressed, the user will be taken
     * to the View Report Screen to view the data of the report that was
     * selected on the Quality Report List Screen.
     * @throws IOException
     */
    @FXML
    private void handleViewPressed() throws IOException {
        QualityReport report = table.getSelectionModel().getSelectedItem();
        if (report != null) {
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("../view/ViewQualityReportScreen.fxml"));
            Stage stage = (Stage) chooseButton.getScene().getWindow();
            Parent root = loader.load();
            loader.<ViewQualityReportController>getController().setup(user, report);
            stage.setScene(new Scene(root));
            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Report Selected");
            alert.setContentText("Please select a report to view.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleDeletePressed() throws IOException {
        QualityReport report = table.getSelectionModel().getSelectedItem();
        if (report != null) {
            try {
                QualityReport.deleteReport(report.getNumber());
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            updateTable();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Report Selected");
            alert.setContentText("Please select a report to view.");
            alert.showAndWait();
        }
    }

    private void updateTable() {
        try {
            list.clear();
            for(QualityReport r : QualityReport.selectAllReports()) {
                list.add(r);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        table.getItems().setAll(list);
    }
}
