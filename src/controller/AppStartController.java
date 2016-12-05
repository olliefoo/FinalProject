package controller;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.*;
import netscape.javascript.JSObject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class AppStartController implements Initializable,
        MapComponentInitializedListener {

    private User user;

    @FXML
    private Button logoutButton;

    @FXML
    private Button profileButton;

    @FXML
    private Button submitButton;

    @FXML
    private Button viewButton;

    @FXML
    private Button submitQualityButton;

    @FXML
    private Button viewQualityButton;

    @FXML
    private Button historyButton;

    @FXML
    private Text sourceText;

    @FXML
    private Text qualityText;

    @FXML
    private Rectangle workerRect;

    @FXML
    private GoogleMapView mapView;

    private GoogleMap map;

    /**
     * Sets the current user into the controller. Also sets the visibilities of
     * certain buttons depending on the type of user.
     * @param u the current user
     */
    public void setUser(User u) {
        user = u;
        //sourceText.setVisible(false);
        qualityText.setVisible(false);
        submitQualityButton.setVisible(false);
        viewQualityButton.setVisible(false);
        workerRect.setVisible(false);
        historyButton.setVisible(false);

        if (user.isWorker() || user.isManager()) {
            workerRect.setVisible(true);
            sourceText.setVisible(true);
            qualityText.setVisible(true);
            submitQualityButton.setVisible(true);
        }
        if (user.isManager()) {
            viewQualityButton.setVisible(true);
            historyButton.setVisible(true);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mapView.addMapInializedListener(this);
    }

    /**
     * Initializes the map and markers for the bodies of water submitted
     * through the submit water source report page.
     */
    @Override
    public void mapInitialized() {
        MapOptions options = new MapOptions();
        //set up the center location for the map
        LatLong center = new LatLong(33, -84);
        if (ReportDatabase.getInstance().getSourceUpdated() && ReportDatabase.getInstance().numSource() != 0) {
            SourceReport newest = ReportDatabase.getInstance().getSourceReport(ReportDatabase.getInstance().numSource());
            center = new LatLong(newest.getLatitude(), newest.getLongitude());
            ReportDatabase.getInstance().setSourceUpdated(false);
        }
        if (ReportDatabase.getInstance().getQualityUpdated() && ReportDatabase.getInstance().numQuality() != 0) {
            QualityReport newest = ReportDatabase.getInstance().getQualityReport(ReportDatabase.getInstance().numQuality());
            center = new LatLong(newest.getLatitude(), newest.getLongitude());
            ReportDatabase.getInstance().setQualityUpdated(false);
        }

        options.center(center)
                .zoom(9)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .mapType(MapTypeIdEnum.TERRAIN);

        map = mapView.createMap(options);

        //place markers on map
        if (ReportDatabase.getInstance().numSource() != 0) {
            SourceReport currentReport;
            double lat;
            double lng;
            LatLong point;
            for (int i = 1; i <= ReportDatabase.getInstance().numSource(); i++) {
                MarkerOptions markerOptions = new MarkerOptions();
                currentReport = ReportDatabase.getInstance().getSourceReport(i);
                String description = currentReport.toString();
                lat = currentReport.getLatitude();
                lng = currentReport.getLongitude();
                point = new LatLong(lat, lng);

                markerOptions.position( point )
                        .visible(Boolean.TRUE)
                        .title(currentReport.getLocation());

                Marker marker = new Marker( markerOptions );

                map.addUIEventHandler(marker,
                        UIEventType.click,
                        (JSObject obj) -> {
                            InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
                            infoWindowOptions.content(description);
                            InfoWindow window = new InfoWindow(infoWindowOptions);
                            window.open(map, marker);});

                map.addMarker(marker);

            }
        }
        //place purity marker
        if (ReportDatabase.getInstance().numQuality() != 0) {
            QualityReport currentReport;
            double lat;
            double lng;
            LatLong point;
            for (int i = 1; i <= ReportDatabase.getInstance().numQuality(); i++) {
                MarkerOptions markerOptions = new MarkerOptions();
                currentReport = ReportDatabase.getInstance().getQualityReport(i);
                String description = currentReport.toString();
                lat = currentReport.getLatitude();
                lng = currentReport.getLongitude();
                point = new LatLong(lat, lng);

                markerOptions.position( point )
                        .visible(Boolean.TRUE)
                        .title(currentReport.getLocation());

                Marker marker = new Marker( markerOptions );

                map.addUIEventHandler(marker,
                        UIEventType.click,
                        (JSObject obj) -> {
                            InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
                            infoWindowOptions.content(description);
                            InfoWindow window = new InfoWindow(infoWindowOptions);
                            window.open(map, marker);});

                map.addMarker(marker);

            }
        }
    }

    /**
     * Method to handle the case when the logout button is pressed
     * It will lead back to the welcome screen.
     * @throws IOException If something messes up
     */
    @FXML
    private void handleLogoutPressed() throws IOException {
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass()
                .getResource("../view/WelcomeScreen.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Method to handle the case when the profile button is pressed
     * It will lead to the profile edit page.
     * @throws IOException If something messes up
     */
    @FXML
    private void handleProfilePressed() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("../view/ProfileScreen.fxml"));
        Stage stage = (Stage) profileButton.getScene().getWindow();
        Parent root = loader.load();
        loader.<ProfileController>getController().setUser(user);
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Handles the "Submit Report" button press. When pressed, leads the user to
     * the SubmitSourceReportScreen.
     *
     * @throws IOException
     */
    @FXML
    private void handleSubmitPressed() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("../view/SubmitSourceReportScreen.fxml"));
        Stage stage = (Stage) submitButton.getScene().getWindow();
        Parent root = loader.load();
        loader.<SubmitSourceReportController>getController().setUser(user);
        stage.setScene(new Scene(root));
        stage.show();

    }

    /**
     * Handles the "View Reports" button press. When pressed, leads the user to
     * the ViewSourceReportScreen.
     *
     * @throws IOException
     */
    @FXML
    private void handleViewPressed() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("../view/SourceReportListScreen.fxml"));
        Stage stage = (Stage) viewButton.getScene().getWindow();
        Parent root = loader.load();
        loader.<SourceReportListController>getController().setUser(user);
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * When pressed, leads the user to the SubmitQualityReport Screen.
     * @throws IOException
     */
    @FXML
    private void handleSubmitQualityPressed() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("../view/SubmitQualityReportScreen.fxml"));
        Stage stage = (Stage) submitQualityButton.getScene().getWindow();
        Parent root = loader.load();
        loader.<SubmitQualityReportController>getController().setUser(user);
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * When pressed, leads the user to view the list of quality reports
     * @throws IOException
     */
    @FXML
    private void handleViewQualityPressed() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("../view/QualityReportListScreen.fxml"));
        Stage stage = (Stage) viewQualityButton.getScene().getWindow();
        Parent root = loader.load();
        loader.<QualityReportListController>getController().setUser(user);
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * When pressed, leads the user to view the historical report
     * @throws IOException
     */
    @FXML
    private void handleHistoryPressed() throws  IOException {
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("../view/HistoryScreen.fxml"));
        Stage stage = (Stage) historyButton.getScene().getWindow();
        Parent root = loader.load();
        loader.<HistoryController>getController().setUser(user);
        stage.setScene(new Scene(root));
        stage.show();
    }
}
