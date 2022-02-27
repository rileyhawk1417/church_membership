package com.cm.cm.controllers.misc;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Window;

import java.net.URL;
import java.util.ResourceBundle;


public class AboutCtrl implements Initializable{
    @FXML
    private Button close_;

    @FXML
    private Text jfx_;

    @FXML
    private Text jdk_;

    @FXML
    private void close_btn(){
        Window owner = close_.getScene().getWindow();
        owner.hide();
    }

    @Override
    public void initialize(URL location, ResourceBundle resource){
        String jdk = System.getProperty("java.version");
        String jfx = System.getProperty("javafx.version");

        jdk_.setText(jdk);
        jfx_.setText(jfx);
    }

}
