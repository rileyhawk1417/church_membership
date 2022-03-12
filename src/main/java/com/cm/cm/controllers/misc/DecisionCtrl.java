package com.cm.cm.controllers.misc;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import java.net.URL;
import java.util.ResourceBundle;
import com.cm.cm.modals.AlertModule;
import javafx.stage.Window;

public class DecisionCtrl implements Initializable {
    SceneCtrl scene_switcher = new SceneCtrl();



    String users[] = {
            "Admin",
            "User",
            "Guest",
    };

    @FXML
    MFXComboBox<String> userType;

    ObservableList<String> user = FXCollections.observableArrayList(users);

    @Override
    public void initialize(URL url, ResourceBundle resource){
        userType.setItems(user);
    }

    @FXML
    private GridPane login_choice;

    @FXML
    private MFXButton SubmitBtn;

    @FXML
    private void submit(){
        selector(userType.getValue());
    }

    private void selector(String choice){
        switch (choice) {
            case "Admin" -> {
                scene_switcher.admin_login_scene();
                login_choice.getScene().getWindow().hide();
            }
            case "User" -> AlertModule.showAlert(Alert.AlertType.ERROR, SubmitBtn.getScene().getWindow(), "System Error", "Function hasn't been implemented yet");
            case "Guest" -> AlertModule.showAlert(Alert.AlertType.ERROR, SubmitBtn.getScene().getWindow(), "System Error", "Function hasn't been implemented yet");
            default -> System.out.println("User not found");
        }
    }

}
