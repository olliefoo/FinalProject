package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.SourceReport;
import model.User;

import java.io.IOException;


public class AppStartController {

    private User user;

    @FXML
    private Button logoutButton;

    @FXML
    private Button profileButton;

    @FXML
    private Button submitButton;

    @FXML
    private Button viewButton;

    public void setUser(User u) {
        user = u;
    }

    /**
     * Method to handle the case when the logout button is pressed
     * It will lead back to the welcome screen.
     * @throws IOException If something messes up
     */
    @FXML
    private void handleLogoutPressed() throws IOException {
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../view/WelcomeScreen.fxml"));
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/ProfileScreen.fxml"));
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


    @FXML
    private void handleSubmitPressed() throws IOException {
        if (isProfileCreated()) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/SourceReportScreen.fxml"));
            Stage stage = (Stage) submitButton.getScene().getWindow();
            Parent root = loader.load();
            loader.<SourceReportController>getController().setUser(user);
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

    @FXML
    private void handleViewPressed() throws IOException {

    }
}
