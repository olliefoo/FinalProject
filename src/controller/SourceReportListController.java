package controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.SourceReport;
import model.User;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by cbbjs on 10/8/2016.
 */
public class SourceReportListController{

    @FXML
    private Button chooseButton, returnButton, deleteButton;

    @FXML
    TableView<SourceReport> table;

    @FXML
    TableColumn<SourceReport, Integer> numberCol;

    @FXML
    TableColumn<SourceReport, String> locationCol;

    @FXML
    TableColumn<SourceReport, String> dateCol;

    @FXML
    TableColumn<SourceReport, String> userCol;

    private User user;

    ObservableList<SourceReport> list = FXCollections.observableArrayList();


    /**
     * Generates the list of reports to be shown on the Source Report List
     * screen.
     */
    @FXML
    private void initialize() {
        try {
            for(SourceReport r : SourceReport.selectAllReports()) {
                list.add(r);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        numberCol.setCellValueFactory(new PropertyValueFactory<SourceReport, Integer>("number"));
        locationCol.setCellValueFactory(new PropertyValueFactory<SourceReport, String>("location"));
        dateCol.setCellValueFactory(new PropertyValueFactory<SourceReport, String>("date"));
        userCol.setCellValueFactory(new PropertyValueFactory<SourceReport,String>("username"));
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
        Stage stage = (Stage) returnButton.getScene().getWindow();
        Parent root = loader.load();
        loader.<AppStartController>getController().setUser(user);
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Handles the view button pressed. When pressed, the user will be taken
     * to the View Report Screen to view the data of the report that was
     * selected on the Source Report List Screen.
     * @throws IOException
     */
    @FXML
    private void handleViewPressed() throws IOException {
        SourceReport report = table.getSelectionModel().getSelectedItem();
        if (report != null) {
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("../view/ViewSourceReportScreen.fxml"));
            Stage stage = (Stage) chooseButton.getScene().getWindow();
            Parent root = loader.load();
            loader.<ViewSourceReportController>getController().setup(user, report);
            stage.setScene(new Scene(root));
            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("No Report Selected");
            alert.setContentText("Please select a report to view.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleDeletePressed() throws IOException {
        SourceReport report = table.getSelectionModel().getSelectedItem();
        if (report != null) {
            try {
                SourceReport.deleteReport(report.getNumber());
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            updateTable();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("No Report Selected");
            alert.setContentText("Please select a report to view.");
            alert.showAndWait();
        }
    }

    private void updateTable() {
        try {
            list.clear();
            for(SourceReport r : SourceReport.selectAllReports()) {
                list.add(r);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        table.getItems().setAll(list);
    }

}
