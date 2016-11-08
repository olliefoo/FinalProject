package controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.ReportDatabase;
import model.SourceReport;
import model.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


/**
 * Created by cbbjs on 10/8/2016.
 */
public class SourceReportListController{
    @FXML
    private Button chooseButton;

    //@FXML
    //private ListView<String> sourceList;

    @FXML
    TableView table;

    @FXML
    TableColumn numberCol;

    @FXML
    TableColumn locationCol;

    @FXML
    TableColumn dateCol;

    @FXML
    TableColumn userCol;

    @FXML
    private Button returnButton;

    /*private ObservableList<String> showList
            = FXCollections.observableArrayList();*/

    private User user;

    private int reportIndex;


    /**
     * Generates the list of reports to be shown on the Source Report List
     * screen.
     */
    @FXML
    private void initialize() {
        if (ReportDatabase.getInstance().numSource() != 0) {
            ObservableList<SourceReport> list
                    = FXCollections.observableArrayList();
            SourceReport report;
            for (int i = 1; i <= ReportDatabase.getInstance().numSource(); i++) {
                report = ReportDatabase.getInstance().getSourceReport(i);
                list.add(report);
            }

            /*numberCol.setCellValueFactory(new PropertyValueFactory<SourceReport, String>("reportNumber"));
            locationCol.setCellValueFactory(new PropertyValueFactory<SourceReport, String>("location"));
            dateCol.setCellValueFactory(new PropertyValueFactory<SourceReport, String>("date"));
            userCol.setCellValueFactory(new PropertyValueFactory<SourceReport, String>("username"));*/

            numberCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SourceReport, String>, ObservableValue<String>>() {
                public ObservableValue<String> call(TableColumn.CellDataFeatures<SourceReport, String> r) {
                    // p.getValue() returns the Person instance for a particular TableView row
                    return new SimpleStringProperty(Integer.toString(r.getValue().getNumber()));
                }
            });
            //table.getColumns().add(numberCol);

            locationCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SourceReport, String>, ObservableValue<String>>() {
                public ObservableValue<String> call(TableColumn.CellDataFeatures<SourceReport, String> r) {
                    // p.getValue() returns the Person instance for a particular TableView row
                    return new SimpleStringProperty(r.getValue().getLocation());
                }
            });
            //table.getColumns().add(locationCol);

            dateCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SourceReport, String>, ObservableValue<String>>() {
                public ObservableValue<String> call(TableColumn.CellDataFeatures<SourceReport, String> r) {
                    // p.getValue() returns the Person instance for a particular TableView row
                    return new SimpleStringProperty(r.getValue().getDate());
                }
            });
            //table.getColumns().add(dateCol);

            userCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SourceReport, String>, ObservableValue<String>>() {
                public ObservableValue<String> call(TableColumn.CellDataFeatures<SourceReport, String> r) {
                    // p.getValue() returns the Person instance for a particular TableView row
                    return new SimpleStringProperty(r.getValue().getUsername());
                }
            });
            //table.getColumns().add(userCol);

            table.setItems(list);
            /*SourceReport temp;
            for (int i = 1; i <= ReportDatabase.numSource(); i++) {
                temp = ReportDatabase.getSourceReport(i);
                showList.add(String.format("Report #%d               %s, %s" +
                        "               %s",
                        i, temp.getDate(), temp.getTime(), temp.getLocation()));
            }*/
            //sourceList.setItems(showList);
        }
    }

    /**
     * Sets User to the user that navigated to the page.
     * @param u user that navigated to page
     */
    public void setUser(User u) {
        user = u;
    }

    /**
     * Checks to see if a report is selected before the user is taken to the
     * view source report page.
     * @return true if a report is selected, false otherwise
     */
    private boolean isIndexValid() {
        return(reportIndex > 0);
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
        // adds 1 to reportIndex because no selection is -1
        reportIndex = table.getSelectionModel().getSelectedIndex() + 1;
        if (isIndexValid()) {
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("../view/ViewSourceReportScreen.fxml"));
            Stage stage = (Stage) chooseButton.getScene().getWindow();
            Parent root = loader.load();
            loader.<ViewSourceReportController>getController()
                    .setup(user, ReportDatabase.getInstance().getSourceReport(reportIndex));
            stage.setScene(new Scene(root));
            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Access Denied");
            alert.setHeaderText("No Report Selected");
            alert.setContentText("Please select a report to view.");
            alert.showAndWait();
        }
    }

}
