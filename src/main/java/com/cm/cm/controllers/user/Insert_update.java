package com.cm.cm.controllers.user;

import com.cm.cm.database.Sqlite;
import com.cm.cm.modals.AlertModule;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Window;

import java.net.URL;
import java.util.ResourceBundle;

public class Insert_update implements Initializable {

    Sqlite sqlite = new Sqlite();

    @FXML
    private Button cancel_insert;

    @FXML
    private Button insert_update;

    @FXML
    private Button insert_update_kids;

    //Start Of TextField
    @FXML
    private TextField fnameBox;

    @FXML
    private TextField lnameBox;

    @FXML
    private TextField idBox;

    @FXML
    private TextField num_Children;

    @FXML
    private DatePicker dateJoined;

    @FXML
    private DatePicker dob;

    @FXML
    private TextField address;

    @FXML
    private TextField homePhone;

    @FXML
    private TextField workPhone;

    @FXML
    private TextField mobilePhone;

    @FXML
    private TextField employer;

    @FXML
    private TextField position;

    @FXML
    private TextField email;

    @FXML
    private TextField homeGroupLeader;

    @FXML
    private TextField departmentLeader;

    @FXML
    private ComboBox<String> titleBox;

    @FXML
    private ComboBox<String> genderBox;

    @FXML
    private ComboBox<String> maritial_status;

    @FXML
    private ComboBox<String> surbub;

    @FXML
    private ComboBox<String> depBox;

    @FXML
    private ComboBox<String> salvationBox;

    @FXML
    private ComboBox<String> baptismBox_1;

    @FXML
    private ComboBox<String> baptismBox_2;

    // Weekdays
    String titles[] = { "Mr", "Mrs", "Miss" };

    String maritial_status_[] = { "Single", "Married" };

    String gender[] = { "Male", "Female" };

    String surbubs[] = {
            "Aerodrome",
            "Mkhosana",
            "Mfelandawonye",
            "Zambezi Camp",
            "Chinotimba",
            "Industrial",
            "Monde",
    };

    String deps[] = {
            "Ushers",
            "Worship/Music Team",
            "Media",
            "Intercessors",
            "Hosting",
    };

    String bools[] = { "Yes", "No" };

    int year = 2024;
    int month = 12;
    int dayOfMonth = 02;
    // Create a combo box
    //   ComboBox combo_box = new ComboBox(
    //     FXCollections.observableArrayList(week_days)
    //   );

    ObservableList<String> title = FXCollections.observableArrayList(titles);
    ObservableList<String> status = FXCollections.observableArrayList(
            maritial_status_
    );
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

    @FXML
    private void insert_rec_kids(ActionEvent event) {
        Window owner = insert_update_kids.getScene().getWindow();
        grabTxtKids(owner);
    }


    public void grabTxtAdults(Window owner) {
        try {
            if (fnameBox.getText().isEmpty() || lnameBox.getText().isEmpty()) {
                AlertModule.showAlert(
                        Alert.AlertType.ERROR,
                        owner,
                        "Input Error",
                        "Please enter all fields"
                );
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
                AlertModule.showAlert(
                        Alert.AlertType.INFORMATION,
                        owner,
                        "Record Added",
                        "Record added successfully"
                );
                // TODO cannot auto reload results after entry
                // TODO Reload is done manually after window closes
                // TableCtrl.reloadTable();;
                insert_update.getScene().getWindow().hide();
            }
            // choiceSelector();

        } catch (Exception e) {
            AlertModule.showAlert(
                    Alert.AlertType.ERROR,
                    owner,
                    "System Error",
                    "Something is broken :("
            );
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void grabTxtKids(Window owner) {
        try {
            if (fnameBox.getText().isEmpty() || lnameBox.getText().isEmpty()) {
                AlertModule.showAlert(
                        Alert.AlertType.ERROR,
                        owner,
                        "Input Error",
                        "Please enter all fields"
                );
            } else {
                sqlite.insertValuesKids(
                        titleBox.getValue(),
                        fnameBox.getText(),
                        lnameBox.getText(),
                        genderBox.getValue(),
                        idBox.getText(),
                        num_Children.getText(),
                        maritial_status.getValue(),
                        dateJoined.getValue().of(year, month, dayOfMonth).toString(),
                        dob.getValue().of(year, month, dayOfMonth).toString(),
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
                AlertModule.showAlert(
                        Alert.AlertType.INFORMATION,
                        owner,
                        "Record Added",
                        "Record added successfully"
                );
                // TODO cannot auto reload results after entry
                // TODO Reload is done manually after window closes
                // TableCtrl.reloadTable();;
                insert_update.getScene().getWindow().hide();
            }
            // choiceSelector();

        } catch (Exception e) {
            AlertModule.showAlert(
                    Alert.AlertType.ERROR,
                    owner,
                    "System Error",
                    "Something is broken :("
            );
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void cancel(ActionEvent event) {
        cancel_insert.getScene().getWindow().hide();
    }
}
