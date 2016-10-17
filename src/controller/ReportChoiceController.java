package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import model.Profile;
import model.ReportDatabase;
import model.SourceReport;
import model.User;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by cbbjs on 10/8/2016.
 */
public class ReportChoiceController {
    @FXML
    private Button chooseButton;

    @FXML
    private ListView<String> sourceList;

    @FXML
    private Button returnButton;

    private ObservableList<String> showList = FXCollections.observableArrayList();
    private User user;
    private Profile profile;
    @FXML
    private void initialize() {
        if (ReportDatabase.size() != 0) {
            for (int i = 0; i < ReportDatabase.size(); i++) {
                showList.add("Report #" + String.valueOf(i + 1));
            }
            sourceList.setItems(showList);
        }
    }
    public void setUser(User u) {
        user = u;
        profile = user.getProfile();
    }
    @FXML
    private void handleReturnPressed() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/AppStartScreen.fxml"));
        Stage stage = (Stage) returnButton.getScene().getWindow();
        Parent root = loader.load();
        loader.<AppStartController>getController().setUser(user);
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void handleViewPressed() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/ViewSourceReportScreen.fxml"));
        Stage stage = (Stage) chooseButton.getScene().getWindow();
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.show();
    }

}
