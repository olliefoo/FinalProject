package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LogoutController {

    @FXML
    private Button logout;
    @FXML
    private Stage dialogStage;

    private boolean okClicked = false;

    public boolean isClicked() { return okClicked;}

    public void setDialogStage(Stage dialogStage) {this.dialogStage = dialogStage;}

    @FXML
    private void handleOKPressed() {
        okClicked = true;
    }
}
