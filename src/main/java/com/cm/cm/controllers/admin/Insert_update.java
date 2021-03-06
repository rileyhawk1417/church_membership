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
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import javafx.stage.Window;

import java.net.URL;
import java.util.ResourceBundle;

public class Insert_update implements Initializable {

     String ID_;
     String Fname_;
     String Lname_;
     String Title_;
     String Address_;
     String DOB_;
     String DateJoined_;
     String Dept_;
     String DeptLeader_;
     String IDNum_;
     String KidsNo_;
     String MStatus_;
     String Cell_Num_;
     String Landline_;
     String Email_;
     String Salvation_;
     String Gender_;
     String WaterBapt_;
     String SpiritBapt_;
     String Surbub_;
     String Employer_;
     String WorkPhone_;
     String Position_;
     String Cell_Leader_;
     boolean updateValue;
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
    // Create a combo box
    //   ComboBox combo_box = new ComboBox(
    //     FXCollections.observableArrayList(week_days)
    //   );
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
    // Weekdays
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
    int year = 2024;
    int month = 12;
    int dayOfMonth = 02;
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

    public  void receiveTxt(
            String id_,
            String fname_,
            String lname_,
            String title_,
            String address_,
            String dob_,
            String dateJoined_,
            String dept_,
            String deptLeader_,
            String idNum_,
            String kidsNum_,
            String mStatus,
            String cell_num_,
            String landline_,
            String email_,
            String salvation_,
            String gender_,
            String waterBapt_,
            String spiritBapt_,
            String surbub_,
            String employer_,
            String workPhone_,
            String position_,
            String cell_leader_,
            boolean updateOption
    ) {
        //TODO Change method to write values to strings
        ID_ = id_;
        Fname_ = fname_;
        Lname_ = lname_;
        Title_ = title_;
        Address_ = address_;
        DOB_ = dob_;
        DateJoined_ = dateJoined_;
        Dept_ = dept_;
        DeptLeader_ = deptLeader_;
        IDNum_ = idNum_;
        KidsNo_ = kidsNum_;
        MStatus_ = mStatus;
        Cell_Num_ = cell_num_;
        Landline_ = landline_;
        Email_ = email_;
        Salvation_ = salvation_;
        Gender_ = gender_;
        WaterBapt_ = waterBapt_;
        SpiritBapt_ = spiritBapt_;
        Surbub_ = surbub_;
        Employer_ = employer_;
        WorkPhone_ = workPhone_;
        Position_ = position_;
        Cell_Leader_ = cell_leader_;
        updateValue = updateOption;

    }

    public  void updateBtn(Boolean res) {
        if (!res) insert_update_kids.setDisable(true);
        else insert_update.setDisable(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resource) {
//        if (updateValue == true) setStrings();

//        else if(updateValue == false) setupComboBoxes();
        setupComboBoxes();

//        else System.out.println("Failed");

//        updateBtn();
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

    private  void setStrings() {
        fnameBox.setText(Fname_);
        lnameBox.setText(Lname_);
        idBox.setText(ID_);
        num_Children.setText(KidsNo_);
        dateJoined.setText(DateJoined_);
        dob.setText(DOB_);
        address.setText(Address_);
        homePhone.setText(Landline_);
        workPhone.setText(WorkPhone_);
        employer.setText(Employer_);
        position.setText(Position_);
        email.setText(Email_);
        homeGroupLeader.setText(Cell_Leader_);
        departmentLeader.setText(DeptLeader_);
        //How to set combobox X_X
        titleBox.setText(Title_);
        genderBox.setText(Gender_);
        maritial_status.setText(MStatus_);
        depBox.setText(Dept_);
        salvationBox.setText(Salvation_);
        baptismBox_1.setText(WaterBapt_);
        baptismBox_2.setText(SpiritBapt_);

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
