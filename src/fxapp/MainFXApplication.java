package fxapp;

import controller.LogoutController;
import controller.WelcomeController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainFXApplication extends Application {

    private Stage mainStage;
    private Parent rootLayout;

    @Override
    public void start(Stage primaryStage) throws Exception{
        mainStage = primaryStage;
        showWelcomeScreen(mainStage);
    }

    public Stage getMainStage() {
        return mainStage;
    }
    private void showWelcomeScreen(Stage mainStage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/WelcomeScreen.fxml"));
        rootLayout = loader.load();

        // Give the controller access to the main app.
        WelcomeController controller = loader.getController();
        controller.setMainApp(this);

        mainStage.setTitle("Clean Water Crowdsourcing");
        // Show the scene containing the root layout.
        Scene scene = new Scene(rootLayout);
        mainStage.setScene(scene);
        mainStage.show();
    }

    public void LogoutDialog(Stage thirdStage) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainFXApplication.class.getResource("../view/AppStartScreen.fxml"));
        rootLayout = loader.load();

        LogoutController controller = loader.getController();
        controller.setMainApp(this);
        // Show the dialog and wait until the user closes it

        mainStage.setTitle("Testing logout page");
        Scene scene = new Scene(rootLayout);
        mainStage.setScene(scene);
        mainStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
