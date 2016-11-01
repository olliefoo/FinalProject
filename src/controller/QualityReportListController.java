package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import model.ReportDatabase;
import model.User;
import model.QualityReport;

import java.io.IOException;

/**
 * Created by miles on 10/31/2016.
 */
public class QualityReportListController {


    @FXML
    private Button chooseButton;

    @FXML
    private ListView<String> qualityList;

    @FXML
    private Button returnButton;

    private ObservableList<String> showList
            = FXCollections.observableArrayList();

    private User user;

    private int reportIndex;

    /**
     * Generates the list of reports to be shown on the Source Report List
     * screen.
     */
    @FXML
    private void initialize() {
        if (ReportDatabase.numQuality() != 0) {
            QualityReport temp;
            for (int i = 1; i <= ReportDatabase.numQuality(); i++) {
                temp = ReportDatabase.getPurityReport(i);
                showList.add(String.format("Report #%d               %s, %s" +
                                "               %s",
                        i, temp.getDate(), temp.getTime(), temp.getLocation()));
            }
            qualityList.setItems(showList);
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
        Stage stage = (Stage) returnButton.getScene  ().getWindow();
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
        reportIndex = qualityList.getSelectionModel().getSelectedIndex() + 1;
        if (isIndexValid()) {
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("../view/ViewQualityReportScreen.fxml"));
            Stage stage = (Stage) chooseButton.getScene().getWindow();
            Parent root = loader.load();
            loader.<ViewQualityReportController>getController()
                    .setup(user, ReportDatabase.getPurityReport(reportIndex));
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
