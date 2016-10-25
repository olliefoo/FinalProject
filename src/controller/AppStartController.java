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
    private Text sourceText;

    @FXML
    private Text qualityText;

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
        sourceText.setVisible(false);
        qualityText.setVisible(false);
        submitQualityButton.setVisible(false);
        viewQualityButton.setVisible(false);

        if (user instanceof Worker || user instanceof Manager) {
            sourceText.setVisible(true);
            qualityText.setVisible(true);
            submitQualityButton.setVisible(true);
        }
        if (user instanceof Manager) {
            viewQualityButton.setVisible(true);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mapView.addMapInializedListener(this);
    }

    @Override
    public void mapInitialized() {
        MapOptions options = new MapOptions();

        //set up the center location for the map
        LatLong center = new LatLong(34, -88);

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
        if (ReportDatabase.numSource() != 0) {
            SourceReport temp;
            double lat;
            double lng;
            LatLong point;
            for (int i = 1; i <= ReportDatabase.numSource(); i++) {
                MarkerOptions markerOptions = new MarkerOptions();
                temp = ReportDatabase.getSourceReport(i);
                lat = temp.getLatitude();
                lng = temp.getLongitude();
                point = new LatLong(lat, lng);

                markerOptions.position( point )
                        .visible(Boolean.TRUE)
                        .title(temp.getLocation());

                Marker marker = new Marker( markerOptions );

                map.addUIEventHandler(marker,
                        UIEventType.click,
                        (JSObject obj) -> {
                            InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
                            /*infoWindowOptions.content(Integer.toString(temp
                                    .getNumber()));*/

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
     * Checks whether the user has edited their profile to include at least
     * their first and last name.
     *
     * @return whether or not the user entered both their first and last name
     */
    public boolean isProfileCreated() {
        return (user.getProfile().getFirstname() != null &&
                user.getProfile().getLastname() != null);
    }

    /**
     * Handles the "Submit Report" button press. When pressed, leads the user to
     * the SubmitSourceReportScreen.
     *
     * @throws IOException
     */
    @FXML
    private void handleSubmitPressed() throws IOException {
        if (isProfileCreated()) {
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("../view/SubmitSourceReportScreen.fxml"));
            Stage stage = (Stage) submitButton.getScene().getWindow();
            Parent root = loader.load();
            loader.<SubmitSourceReportController>getController().setUser(user);
            stage.setScene(new Scene(root));
            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Access Denied");
            alert.setHeaderText("Please Edit Profile");
            alert.setContentText("Please edit your profile first to proceed." +
                    "Need first name and last name.");
            alert.showAndWait();
        }
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
        Stage stage = (Stage) profileButton.getScene().getWindow();
        Parent root = loader.load();
        loader.<SourceReportListController>getController().setUser(user);
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void handleSubmitQualityPressed() throws IOException {

    }

    @FXML
    private void handleViewQualityPressed() throws IOException {

    }
}
