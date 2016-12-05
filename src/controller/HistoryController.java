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

import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;


import model.QualityReport;
import model.User;

import java.io.IOException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

/**
 * Created by Owner on 11/6/2016.
 */
public class HistoryController {

    private User user;

    @FXML
    private LineChart<String, Double> graph;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @FXML
    private Button viewButton;

    @FXML
    private Button returnButton;

    @FXML
    private ComboBox<String> locationBox;

    @FXML
    private ComboBox<String> ppmBox;

    @FXML
    private ComboBox<String> yearBox;

    /**
     * Sets the current user into the controller
     * @param u the current user
     */
    public void setUser(User u) {
        user = u;
    }

    /**
     * Initializes the graph axes and combo box values.
     */
    @FXML
    private void initialize() {
        ObservableList<String> monthNames = FXCollections.observableArrayList();
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul",
                "Aug", "Sep", "Oct", "Nov", "Dec"};
        monthNames.addAll(Arrays.asList(months));
        xAxis.setCategories(monthNames);

        String[] ppmType = {"Contaminant" , "Virus"};
        ppmBox.getItems().addAll(ppmType);

        TreeSet<String> locations = new TreeSet<>();
        TreeSet<String> years = new TreeSet<>();
        try {
            for(QualityReport r : QualityReport.selectAllReports()) {
                locations.add(r.getLocation());
                years.add(r.getYear());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        locationBox.getItems().addAll(locations);
        yearBox.getItems().addAll(years);
    }

    /**
     * When view is pressed, generates the information on the graph
     */
    @FXML
    private void handleViewPressed() {
        if(isInputValid()) {
            // Can only view one line at a time. Every time press view button,
            // delete old line.
            graph.getData().clear();

            /*ArrayList<QualityReport> locationList = new ArrayList<>(10);
            ArrayList<QualityReport> yearList = new ArrayList<>(10);*/

            //adds the reports with the specified location to the list
            /*for (QualityReport r : ReportDatabase.getInstance().getQualityReports()) {
                if (r.getLocation().equals(locationBox.getValue())) {
                    locationList.add(r);
                }
            }*/

            //adds the reports from the locationList with the specified year
            /*for (QualityReport r : locationList) {
                if ((r.getYear().equals(yearBox.getValue()))) {
                    yearList.add(r);
                }
            }*/

            XYChart.Series<String, Double> series = new XYChart.Series<>();
            if (ppmBox.getValue().equals("Virus")) {
                /*for (QualityReport r : yearList) {
                    XYChart.Data<String, Double> new_chart = new XYChart.Data<>(r.getMonth(), r.getVirus());
                    series.getData().add(new_chart);
                }*/
                try {
                    for(QualityReport r : QualityReport.selectVirusReports(locationBox.getValue(), yearBox.getValue())) {
                        XYChart.Data<String, Double> new_chart = new XYChart.Data<>(r.getMonth(), r.getVirus());
                        series.getData().add(new_chart);
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
                series.setName(locationBox.getValue() + ", " + yearBox.getValue() + ", Virus PPM");
            } else if (ppmBox.getValue().equals("Contaminant")) {
                /*for (QualityReport r : yearList) {
                    XYChart.Data<String, Double> new_chart = new XYChart.Data<>(r.getMonth(), r.getContaminant());
                    series.getData().add(new_chart);
                }*/
                try {
                    for(QualityReport r : QualityReport.selectContaminantReports(locationBox.getValue(), yearBox.getValue())) {
                        XYChart.Data<String, Double> new_chart = new XYChart.Data<>(r.getMonth(), r.getVirus());
                        series.getData().add(new_chart);
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
                series.setName(locationBox.getValue() + ", " + yearBox.getValue() + ", Contaminant PPM");
            }

            graph.getData().add(series);
        }
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

    /**
     * Checks whether the combo box values are selected
     */
    private boolean isInputValid() {

        // not working correctly. choosing just location and ppm works but should not
        return (locationBox.getValue() != null && ppmBox.getValue() != null
                && yearBox.getItems() != null);
    }

}
