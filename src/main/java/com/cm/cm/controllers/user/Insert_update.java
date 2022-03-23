package com.cm.cm.controllers.user;

import com.cm.cm.database.Sqlite;
import com.cm.cm.modals.AlertModule;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Window;
import io.github.palexdev.materialfx.controls.*;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class Insert_update implements Initializable {

    Sqlite sqlite = new Sqlite();
    AlertModule alertBox = new AlertModule();
    Kids_Table_View kidsTable = new Kids_Table_View();
    Table_View adultTable = new Table_View();

    @FXML
    private GridPane gridPane;

    @FXML
    private MFXButton cancel_insert;

    @FXML
    private static MFXButton insert_update;

    @FXML
    private static MFXButton insert_update_kids;

    //Start Of TextField
    @FXML
    private MFXTextField fnameBox;

    @FXML
    private MFXTextField lnameBox;

    @FXML
    private MFXTextField idBox;

    @FXML
    private MFXTextField num_Children;

    @FXML
    private MFXDatePicker dateJoined;

    @FXML
    private MFXDatePicker dob;

    @FXML
    private MFXTextField address;

    @FXML
    private MFXTextField homePhone;

    @FXML
    private MFXTextField workPhone;

    @FXML
    private MFXTextField mobilePhone;

    @FXML
    private MFXTextField employer;

    @FXML
    private MFXTextField position;

    @FXML
    private MFXTextField email;

    @FXML
    private MFXTextField homeGroupLeader;

    @FXML
    private MFXTextField departmentLeader;

    @FXML
    private MFXComboBox<String> titleBox;

    @FXML
    private MFXComboBox<String> genderBox;

    @FXML
    private MFXComboBox<String> maritial_status;

    @FXML
    private MFXComboBox<String> surbub;

    @FXML
    private MFXComboBox<String> depBox;

    @FXML
    private MFXComboBox<String> salvationBox;

    @FXML
    private MFXComboBox<String> baptismBox_1;

    @FXML
    private MFXComboBox<String> baptismBox_2;

    String[] titles = { "Mr", "Mrs", "Miss" };

    String[] maritial_status_ = { "Single", "Married" };

    String[] gender = { "Male", "Female" };

    String[] surbubs = {
            "Aerodrome",
            "Mkhosana",
            "Mfelandawonye",
            "Zambezi Camp",
            "Chinotimba",
            "Industrial",
            "Monde",
    };

    String[] deps = {
            "Ushers",
            "Worship/Music Team",
            "Media",
            "Intercessors",
            "Hosting",
    };

    String[] bools = { "Yes", "No" };

    ObservableList<String> title = FXCollections.observableArrayList(titles);
    ObservableList<String> status = FXCollections.observableArrayList(maritial_status_);
    ObservableList<String> sex = FXCollections.observableArrayList(gender);
    ObservableList<String> region = FXCollections.observableArrayList(surbubs);
    ObservableList<String> dep = FXCollections.observableArrayList(deps);
    ObservableList<String> truths = FXCollections.observableArrayList(bools);

    @Override
    public void initialize(URL url, ResourceBundle resource) {
        titleBox.setItems(title);
        maritial_status.setItems(status);
        genderBox.setItems(sex);
        surbub.setItems(region);
        depBox.setItems(dep);
        salvationBox.setItems(truths);
        baptismBox_1.setItems(truths);
        baptismBox_2.setItems(truths);
    }

    @FXML
    private void insert_rec(ActionEvent event) {
        Window owner = insert_update.getScene().getWindow();
        grabTxtAdults(owner);
    }

    public static void updateBtn(Boolean res){
        if (!res) insert_update_kids.setDisable(true);
        else insert_update.setDisable(false);
    }

    @FXML
    private void insert_rec_kids(ActionEvent event) {
        Window owner = insert_update_kids.getScene().getWindow();
        grabTxtKids(owner);
    }


    public void grabTxtAdults(Window owner) {
        try {
            if (fnameBox.getText().isEmpty() || lnameBox.getText().isEmpty()) {
                alertBox.showMFXAlert(owner, "Input Error", "Please Enter all fields", AlertModule.dialogType.ERR, gridPane);
            } else {
                sqlite.insertValues(
                        titleBox.getValue(),
                        fnameBox.getText(),
                        lnameBox.getText(),
                        genderBox.getValue(),
                        idBox.getText(),
                        num_Children.getText(),
                        maritial_status.getValue(),
                        dateJoined.getValue().toString(),
                        dob.getValue().toString(),
                        address.getText(),
                        surbub.getValue(),
                        homePhone.getText(),
                        workPhone.getText(),
                        mobilePhone.getText(),
                        employer.getText(),
                        position.getText(),
                        email.getText(),
                        homeGroupLeader.getText(),
                        departmentLeader.getText(),
                        depBox.getValue(),
                        salvationBox.getValue(),
                        baptismBox_1.getValue(),
                        baptismBox_2.getValue()
                );
                alertBox.showMFXAlert(owner, "Record Added", "Recorded Successfully Added", AlertModule.dialogType.INFO, gridPane);
                adultTable.loadTable();
                insert_update.getScene().getWindow().hide();
            }
        } catch (Exception e) {
            alertBox.showMFXAlert(owner, "System Error", "Could not add record", AlertModule.dialogType.ERR, gridPane);
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void grabTxtKids(Window owner) {
        try {
            if (fnameBox.getText().isEmpty() || lnameBox.getText().isEmpty()) {
                alertBox.showMFXAlert(owner, "Input Error", "Please Enter all fields", AlertModule.dialogType.ERR, gridPane);
            } else {
                sqlite.insertValuesKids(
                        titleBox.getValue(),
                        fnameBox.getText(),
                        lnameBox.getText(),
                        genderBox.getValue(),
                        idBox.getText(),
                        num_Children.getText(),
                        maritial_status.getValue(),
                        dateJoined.getValue().toString(),
                        dob.getValue().toString(),
                        address.getText(),
                        surbub.getValue(),
                        homePhone.getText(),
                        workPhone.getText(),
                        mobilePhone.getText(),
                        employer.getText(),
                        position.getText(),
                        email.getText(),
                        homeGroupLeader.getText(),
                        departmentLeader.getText(),
                        depBox.getValue(),
                        salvationBox.getValue(),
                        baptismBox_1.getValue(),
                        baptismBox_2.getValue()
                );
                alertBox.showMFXAlert(owner, "Record Added", "Recorded Successfully Added", AlertModule.dialogType.INFO, gridPane);
                kidsTable.loadTable();
                insert_update.getScene().getWindow().hide();
            }
        } catch (Exception e) {
            alertBox.showMFXAlert(owner, "System Error", "Could not add record", AlertModule.dialogType.ERR, gridPane);
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void cancel(ActionEvent event) {
        cancel_insert.getScene().getWindow().hide();
    }
}
