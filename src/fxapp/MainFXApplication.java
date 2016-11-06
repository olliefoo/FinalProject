package fxapp;

import model.Database;
import controller.WelcomeController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.ReportDatabase;

import java.io.*;

public class MainFXApplication extends Application {

    // Creates a single instance of the database classes
    public static Database database = new Database();
    public static ReportDatabase reportDatabase = new ReportDatabase();
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
        //controller.setMainApp(this);

        mainStage.setTitle("Clean Water Crowdsourcing");
        // Show the scene containing the root layout.
        Scene scene = new Scene(rootLayout);
        mainStage.setScene(scene);
        mainStage.show();
    }


    public static void main(String[] args) {
        String fileName = "data.bin";
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fileName));
            os.writeObject(database);
            os.writeObject(reportDatabase);
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(fileName));
            database = (Database) is.readObject();
            reportDatabase = (ReportDatabase) is.readObject();
            is.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    launch(args);
    }
}
