package com.cm.cm.controllers.misc;

import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.layout.GridPane;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;

import java.net.URL;
import java.util.ResourceBundle;

import com.cm.cm.modals.AlertModule;
import javafx.stage.Stage;
import javafx.stage.Window;
import com.cm.cm.database.Sqlite;


public class DecisionCtrl implements Initializable {
    SceneCtrl scene_switcher = new SceneCtrl();
    Sqlite sqlite = new Sqlite();
    AlertModule alertBox = new AlertModule();

    Stage stage = new Stage();
    Window owner = stage.getOwner();

    String users[] = {
            "Admin",
            "User",
            "Guest",
    };

    String sqlUsers[] = {
            "admin",
            "user",
    };

    String user_ ="";

    boolean auth;
    ObservableList<String> user = FXCollections.observableArrayList(users);
    @FXML
    private MFXFilterComboBox<String> userType;
    @FXML
    private MFXTextField userField;
    @FXML
    private MFXPasswordField passField;
    @FXML
    private GridPane login_choice;
    @FXML
    private MFXButton SubmitBtn;

    @Override
    public void initialize(URL url, ResourceBundle resource) {
        userType.setItems(user);
    }

    @FXML
    private void submit() {
        checkUser(userField.getText(), passField.getText(), userType.getValue());
    }

    private void checkUser(String userName, String passWord, String choice) {
        if (userField.getText().isEmpty() || passField.getText().isEmpty()) {
            alertBox.showMFXAlert(owner, "Error in Credentials", "Please enter both name and user name", AlertModule.dialogType.ERR, login_choice);
        }

        switch (choice){
            case "Admin" -> {
                user_ = sqlUsers[0];
            }
            case "User" -> {
                user_ = sqlUsers[1];
            }

            default -> throw new IllegalStateException("Unexpected value: " + choice);
        }

            try {
                auth = Authenticate();

                if (auth) {
                    sceneSelector(choice);
                } else {
                    alertBox.showMFXAlert(owner, "Error in Credentials", "Wrong credentials", AlertModule.dialogType.ERR, login_choice);

                }
            } catch (Exception e){
                e.printStackTrace();
            }
    }

    private void sceneSelector(String choice) {
        switch (choice) {
            case "Admin" -> {
                scene_switcher.admin_rec_scene();
                login_choice.getScene().getWindow().hide();
            }
            case "User" -> {
                scene_switcher.user_rec_scene();
                login_choice.getScene().getWindow().hide();
            }
            case "Guest" -> {
                scene_switcher.guest_rec_scene();
                login_choice.getScene().getWindow().hide();
            }
            default -> System.out.println("User not found");
        }
    }


    private boolean Authenticate() throws Exception {
        boolean val = sqlite.validateUser(userField.getText(), passField.getText(), user_);

        if (val) {
            System.out.println("User name and Password are correct");
            return true;
        } else {
            System.out.println("Incorrect user and password");
            return false;
        }
    }

}
