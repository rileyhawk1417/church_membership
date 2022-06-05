package com.cm.cm.controllers.admin;

import com.cm.cm.database.Sqlite;
import com.cm.cm.modals.AlertModule;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.stage.Window;

import java.net.URL;
import java.util.ResourceBundle;

public class Add_Record implements Initializable {
    @FXML
    private  MFXButton insert_update;
    @FXML
    private  MFXButton insert_update_kids;
    //Start Of TextField
    @FXML
    private  MFXTextField fnameBox;
    @FXML
    private  MFXTextField lnameBox;
    @FXML
    private  MFXTextField idBox;
    @FXML
    private  MFXTextField num_Children;
    @FXML
    private  MFXDatePicker dateJoined;
    @FXML
    private  MFXDatePicker dob;
    @FXML
    private  MFXTextField address;
    @FXML
    private  MFXTextField homePhone;
    @FXML
    private  MFXTextField workPhone;
    @FXML
    private  MFXTextField employer;
    @FXML
    private  MFXTextField position;
    @FXML
    private  MFXTextField email;
    @FXML
    private  MFXTextField homeGroupLeader;
    @FXML
    private  MFXTextField departmentLeader;
    @FXML
    private  MFXComboBox<String> titleBox;
    @FXML
    private  MFXComboBox<String> genderBox;
    @FXML
    private  MFXComboBox<String> maritial_status;
    @FXML
    private  MFXComboBox<String> depBox;
    @FXML
    private  MFXComboBox<String> salvationBox;
    @FXML
    private  MFXComboBox<String> baptismBox_1;
    @FXML
    private  MFXComboBox<String> baptismBox_2;
    Sqlite sqlite = new Sqlite();
    AlertModule alertBox = new AlertModule();
    public  String[] titles = {"Mr", "Mrs", "Miss"};
    public  String[] maritial_status_ = {"Single", "Married"};
    public  String[] gender = {"Male", "Female"};
    public  String[] surbubs = {
            "Aerodrome",
            "Mkhosana",
            "Mfelandawonye",
            "Zambezi Camp",
            "Chinotimba",
            "Industrial",
            "Monde",
    };
    public  String[] deps = {
            "Ushers",
            "Worship/Music Team",
            "Media",
            "Intercessors",
            "Hosting",
    };
    public  String[] bools = {"Yes", "No"};
    public  ObservableList<String> title = FXCollections.observableArrayList(titles);
    public  ObservableList<String> status = FXCollections.observableArrayList(maritial_status_);
    public  ObservableList<String> sex = FXCollections.observableArrayList(gender);
    public  ObservableList<String> region = FXCollections.observableArrayList(surbubs);
    public  ObservableList<String> dep = FXCollections.observableArrayList(deps);
    public  ObservableList<String> truths = FXCollections.observableArrayList(bools);
    @FXML
    private GridPane gridPane;
    @FXML
    private MFXButton cancel_insert;
    @FXML
    private  MFXTextField mobilePhone;
    @FXML
    private  MFXComboBox<String> surbub;

    public  void updateBtn(Boolean res) {
        if (!res) insert_update_kids.setDisable(true);
        else insert_update.setDisable(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resource) {
        setupComboBoxes();
    }

    public  void setupComboBoxes() {
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

    @FXML
    private void insert_rec_kids(ActionEvent event) {
        Window owner = insert_update_kids.getScene().getWindow();
        grabTxtKids(owner);
    }


    public void grabTxtAdults(Window owner) {
        try {
            if (fnameBox.getText().isEmpty() || lnameBox.getText().isEmpty()) {
                alertBox.showMFXAlert(owner, "Input Error", "Please enter all fields", AlertModule.dialogType.ERR, gridPane);
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
                // System.out.println(dob.getValue() + " " + dateJoined.getValue());
                alertBox.showMFXAlert(owner, "Record Added", "Record added successfully", AlertModule.dialogType.INFO, gridPane);
                //TODO: Add adult or kids table view
                insert_update.getScene().getWindow().hide();
            }
            // choiceSelector();

        } catch (Exception e) {
            alertBox.showMFXAlert(owner, "Action Failed", "Failed To add record", AlertModule.dialogType.ERR, gridPane);


            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void grabTxtKids(Window owner) {
        try {
            if (fnameBox.getText().isEmpty() || lnameBox.getText().isEmpty()) {
                alertBox.showMFXAlert(owner, "Input Error", "Please enter all fields", AlertModule.dialogType.ERR, gridPane);
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
                alertBox.showMFXAlert(owner, "Record Added", "Record added successfully", AlertModule.dialogType.INFO, gridPane);
                insert_update.getScene().getWindow().hide();
            }

        } catch (Exception e) {
            alertBox.showMFXAlert(owner, "System Error", "Failed to add record", AlertModule.dialogType.ERR, gridPane);
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void cancel(ActionEvent event) {
        cancel_insert.getScene().getWindow().hide();
    }
}
