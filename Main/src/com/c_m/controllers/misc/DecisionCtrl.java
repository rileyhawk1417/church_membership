package com.c_m.controllers.misc;

import javafx.scene.layout.AnchorPane;

import com.c_m.controllers.misc.SceneCtrl;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class DecisionCtrl {
    SceneCtrl scene_switcher = new SceneCtrl();
    
    @FXML
    private AnchorPane login_choice;

    @FXML
    private Button admin_;
    
    @FXML
    private Button cashier_;

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
