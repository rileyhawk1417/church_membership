package com.cm.cm.controllers.misc;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import io.github.palexdev.materialfx.controls.MFXButton;

public class DecisionCtrl {
    SceneCtrl scene_switcher = new SceneCtrl();

    @FXML
    private GridPane login_choice;

    @FXML
    private MFXButton admin_;

    @FXML
    private MFXButton cashier_;

    @FXML
    private void admin_btn(){
        scene_switcher.admin_login_scene();
        login_choice.getScene().getWindow().hide();
    }

    @FXML
    private void cashier_btn(){
        scene_switcher.cashier_login_scene();
        login_choice.getScene().getWindow().hide();
    }

}
