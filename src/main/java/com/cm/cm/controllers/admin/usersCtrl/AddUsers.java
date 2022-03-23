package com.cm.cm.controllers.admin.usersCtrl;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.MFXComboBox;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

import com.cm.cm.database.Sqlite;


public class AddUsers implements Initializable {

    Sqlite sqlite = new Sqlite();

    String[] tableNames = {
            "admin_login",
            "user_login"
    };

    @FXML
    private MFXButton submitBtn;

    @FXML
    private MFXButton cancelBtn;

    @FXML
    private MFXComboBox<String> tables;

    @FXML
    private MFXTextField userName;

    @FXML
    private MFXTextField passWord;

    ObservableList<String> tables_ = FXCollections.observableArrayList(tableNames);

    @Override
    public void initialize(URL location, ResourceBundle resource){
        tables.setItems(tables_);
    }

    @FXML
    private void addUser(){
        selectTable(tables.getValue());

    }

    private void selectTable(String selected_) {
        switch (selected_) {
            case "admin", "user_login" -> {
                try {
                    sqlite.insertUser(userName.getText(), passWord.getText(), tables.getValue());
                    System.out.println("User added" + userName.getText() + passWord.getText());
                    submitBtn.getScene().getWindow().hide();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
            }
            default -> System.out.println("Cant add user");
        }
    }

    @FXML
    private void cancelUser(){
        cancelBtn.getScene().getWindow().hide();
    }
}
