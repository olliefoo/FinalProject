package controller;

import javafx.fxml.FXML;
import fxapp.MainFXApplication;
import javafx.scene.control.*;
import model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Created by cbbjs on 10/8/2016.
 */
public class ReportReadController {
    @FXML
    private TextField sourceText;

    @FXML
    private Button returnButton;

    @FXML
    private void initialize(){
        ReportDatabase.getSourceReport();
    }

    @FXML
    private void handleReturnPressed() throws IOException{
        Stage stage = (Stage) returnButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource
                ("../view/ReportChoiceScreen.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }


}
