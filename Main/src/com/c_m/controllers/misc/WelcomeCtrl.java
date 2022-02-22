package com.c_m.controllers.misc;

import com.c_m.controllers.misc.SceneCtrl;

import javafx.fxml.*;
import javafx.scene.control.Button;

public class WelcomeCtrl {
    SceneCtrl scene_switcher = new SceneCtrl();

    @FXML
    private Button manual_;
    
    @FXML
    private Button cashier_;

    @FXML
    private Button admin_;

    @FXML
    private void cashier_login(){
        // scene_switcher.
    };

    @FXML
    private void admin_login(){};
}
