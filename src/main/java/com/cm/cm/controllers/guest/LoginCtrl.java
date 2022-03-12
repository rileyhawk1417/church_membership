package com.cm.cm.controllers.guest;

import com.cm.cm.app.App;
import com.cm.cm.controllers.misc.SceneCtrl;
import com.cm.cm.database.Sqlite;
import com.cm.cm.modals.AlertModule;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginCtrl {
    @FXML
    private MFXTextField userField;

    @FXML
    private MFXPasswordField passField;

    @FXML
    private MenuItem closeBtn;

    @FXML
    private MenuItem aboutBtn;

    @FXML
    private URL location;
    @FXML
    private ResourceBundle resources;

    @FXML
    private MFXButton subBtn;

    Sqlite database = new Sqlite();
    Stage stage = new Stage();
    SceneCtrl scene_switcher = new SceneCtrl();
    App app = new App();
    // * This is just a reminder for when dealing with events
    // final EventHandler<KeyEvent> submit = new EventHandler<KeyEvent>() {
    // public void handle(KeyEvent e) {
    // final String user = userField.getText();
    // final String pass = passField.getText();

    // if (e.getCode().equals(KeyCode.ENTER)) {
    // try {
    // boolean val = database.validate(user, pass);
    // if (val) {
    // System.out.println("User Found");
    // } else {
    // System.out.println("Didnt find user");
    // }
    // } catch (SQLException e1) {
    // // TODO Auto-generated catch block
    // e1.printStackTrace();
    // }
    // } else {
    // System.out.println("Failed");
    // }
    // }
    // };

    @FXML
    private void subKey(ActionEvent event) {
        Window owner = subBtn.getScene().getWindow();
        if (userField.getText().isEmpty()) {
            AlertModule.showAlert(Alert.AlertType.ERROR, owner, "Credential Error", "User Name is required");
            return;
        }
        if (passField.getText().isEmpty()) {
            AlertModule.showAlert(Alert.AlertType.ERROR, owner, "Credential Error", "Password is required");
            return;

        }
        boolean auth;
        try {
            auth = Authenticate();
            if (auth) {
                System.out.println("User Found");
                AlertModule.showAlert(Alert.AlertType.CONFIRMATION, owner, "Login Successful", "Welcome");
                subBtn.getScene().getWindow().hide();
                scene_switcher.admin_rec_scene();
            } else {
                System.out.println("False");
                AlertModule.showAlert(Alert.AlertType.ERROR, owner, "Credential Error", "Incorrect Details");
                System.out.println("Didnt find user");

            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @FXML
    private void exitApp(){
        App.closeApp();
    }

    @FXML
    private void aboutApp(){
        scene_switcher.about_scene();
    }

    public void initialize() {
        // Doesnt do much system loads either way
        try {
            System.out.println("Fxml Loaded from App.java");
        } catch (Exception e) {
            System.out.println(e.getMessage() + "Failed to import FXML");
        }

    }

    private boolean Authenticate() throws Exception {

        boolean val = database.validateAdmin(userField.getText(), passField.getText());
        if (val) {
            System.out.println("User Authenticated");
            return true;
        } else {
            System.out.println("Failed To Authenticate");
            return false;
        }

    }
}
