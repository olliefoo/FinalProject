package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.User;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Owner on 12/6/2016.
 */
public class UserListController {

    @FXML
    private Button backButton, deleteButton, unlockButton;

    @FXML
    private TableView<User> table;

    @FXML
    private TableColumn<User, String> userCol, typeCol, lockedCol;

    ObservableList<User> list = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        try {
            for(User u : User.selectAllUsers()) {
                if(!u.isAdmin()) {
                    list.add(u);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        userCol.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
        typeCol.setCellValueFactory(new PropertyValueFactory<User, String>("type"));
        lockedCol.setCellValueFactory(new PropertyValueFactory<User, String>("locked"));

        table.getItems().setAll(list);
    }

    @FXML
    private void handleBackPressed() throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass()
                .getResource("../view/AdminStartScreen.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void handleDeletePressed() {
        User user = table.getSelectionModel().getSelectedItem();
        if(user != null) {
            try {
                User.delete(user.getUsername());
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            updateTable();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No User Selected");
            alert.setContentText("Please select a user to delete");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleUnlockPressed() {
        User user = table.getSelectionModel().getSelectedItem();
        if(user != null) {
            if (user.isLocked()) {
                user.setLocked(false);
                try {
                    user.update();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
            updateTable();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No User Selected");
            alert.setContentText("Please select a user to unlock");
            alert.showAndWait();
        }
    }

    private void updateTable() {
        try {
            list.clear();
            for(User u : User.selectAllUsers()) {
                if(!u.isAdmin()) {
                    list.add(u);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        table.getItems().setAll(list);
    }

}
