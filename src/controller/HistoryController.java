package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import model.User;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Owner on 11/6/2016.
 */
public class HistoryController {

    private User user;

    @FXML
    private LineChart graph;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @FXML
    private Button viewButton;

    @FXML
    private Button returnButton;

    @FXML
    private ComboBox locationBox;

    @FXML
    private ComboBox ppmBox;

    @FXML
    private ComboBox yearBox;

    /**
     * Sets the current user into the controller
     * @param u
     */
    public void setUser(User u) {
        user = u;
    }

    /**
     * Initializes the graph axes and combobox values.
     */
    @FXML
    private void initialize() {
        ObservableList<String> monthNames = FXCollections.observableArrayList();
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul",
                "Aug", "Sep", "Oct", "Nov", "Dec"};
        monthNames.addAll(Arrays.asList(months));
        xAxis.setCategories(monthNames);
    }

    /**
     * When view is pressed, generates the information on the graph
     * @throws IOException
     */
    @FXML
    private void handleViewPressed() throws IOException {

    }

    /**
     * When return is pressed, leads user back to the AppStartScreen
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





}
