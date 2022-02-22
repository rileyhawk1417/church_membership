package com.schoolAdmin.controllers.user;
//TODO:Finish tweaking the files
//TODO: Work on loading up the Table
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.*;
import javafx.event.*;
import java.io.*;
import java.net.URL;
import java.sql.*;
import java.sql.SQLException;
import java.util.ResourceBundle;
import com.schoolAdmin.app.App;
import com.schoolAdmin.modals.AlertModule;
import com.schoolAdmin.modals.MemberModel;
import com.schoolAdmin.database.Psql;
import com.schoolAdmin.controllers.misc.SceneCtrl;
// import com.schoolAdmin.controllers.admin.UpdateCtrl;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import java.nio.file.*;

public class Table_View implements Initializable {
    Stage stage = new Stage();
    Psql psql = new Psql();

    Window owner = stage.getOwner();
    SceneCtrl scene_switcher = new SceneCtrl();
    //Start Of Table Properties
    @FXML
    private TableView<MemberModel> psqlTable;

    @FXML
    private TableColumn<MemberModel, String> id_;

    @FXML
    private TableColumn<MemberModel, String> fname;

    @FXML
    private TableColumn<MemberModel, String> lname;

    @FXML
    private TableColumn<MemberModel, String> Title_;

    @FXML
    private TableColumn<MemberModel, String> Address;

    @FXML
    private TableColumn<MemberModel, String> DOB_;

    @FXML
    private TableColumn<MemberModel, String> DateJoined_;

    @FXML
    private TableColumn<MemberModel, String> Dept;

    @FXML
    private TableColumn<MemberModel, String> DeptLeader;

    @FXML
    private TableColumn<MemberModel, String> HomeGroup;

    @FXML
    private TableColumn<MemberModel, String> Homephone;

    @FXML
    private TableColumn<MemberModel, String> IDNum;

    @FXML
    private TableColumn<MemberModel, String> KidsNo_;

    @FXML
    private TableColumn<MemberModel, String> MStatus_;

    @FXML
    private TableColumn<MemberModel, String> MobilePhone;

    @FXML
    private TableColumn<MemberModel, String> Email;

    @FXML
    private TableColumn<MemberModel, String> Salvation;

    @FXML
    private TableColumn<MemberModel, String> Sex;

    @FXML
    private TableColumn<MemberModel, String> waterBapt;

    @FXML
    private TableColumn<MemberModel, String> SpiritBapt;

    @FXML
    private TableColumn<MemberModel, String> Surbub;

    @FXML
    private TableColumn<MemberModel, String> WorkPhone;

    //Table Properties End


    //Text Properties Start
    @FXML
    private Text address_;

    @FXML
    private Text childrenTotal_;

    @FXML
    private Text chldrenNo_;

    @FXML
    private Text dateJoined_;

    @FXML
    private Text dep;

    @FXML
    private Text depLeader_;

    @FXML
    private Text dob_;

    @FXML
    private Text email_;

    @FXML
    private Text employer_;

    @FXML
    private Text gender_;

    @FXML
    private Text homeGroup_;

    @FXML
    private Text homePhone_;

    @FXML
    private Text idNo_;

    @FXML
    private Text maritial_;

    @FXML
    private Text membersTotal_;

    @FXML
    private Text mobilePhone_;

    @FXML
    private Text name_;

    @FXML
    private Text position_;

    @FXML
    private Text salvation_;

    @FXML
    private TextField searchBar;

    @FXML
    private Text spirit_;
    
    @FXML
    private Text title_;

    @FXML
    private Text surbub_;
    @FXML
    private Text water_;

    @FXML
    private Text workPhone_; 

    
    //Menu Items Start
    @FXML
    private MenuItem switch_user_;

    @FXML
    private MenuItem update_option;

    @FXML
    private MenuItem exit;

    @FXML
    private MenuItem exportBtn;

    @FXML
    private MenuItem export_btn;

    @FXML
    private MenuItem export_current_view;

    @FXML
    private MenuItem export_view;

    @FXML
    private MenuItem export_view_btn;

    @FXML
    private MenuItem about_;

    @FXML
    private MenuItem add_rec;

    @FXML
    private MenuItem log_out;

    @FXML
    private MenuItem manual_;

    @FXML
    private MenuItem delete_by_id;

    @FXML
    private MenuItem delete_by_id_context;

    @FXML
    private MenuItem delete_by_name;

    @FXML
    private MenuItem close;

    @FXML
    private MenuItem reload_;
  

    //Button Start
    @FXML
    private Button subQuery;

    ObservableList<MemberModel>records;

    @Override
    public void initialize(URL location, ResourceBundle bundle){
        assert psqlTable !=null : "Failed to load table";
        
        id_.setCellValueFactory(new PropertyValueFactory<MemberModel, String>("id_"));
        fname.setCellValueFactory(new PropertyValueFactory<MemberModel, String>("fname"));
        lname.setCellValueFactory(new PropertyValueFactory<MemberModel, String>("lname"));
        Title_.setCellValueFactory(new PropertyValueFactory<MemberModel, String>("Title_"));
        Address.setCellValueFactory(new PropertyValueFactory<MemberModel, String>("Address"));
        DOB_.setCellValueFactory(new PropertyValueFactory<MemberModel, String>("DOB_"));
        DateJoined_.setCellValueFactory(new PropertyValueFactory<MemberModel, String>("DateJoined_"));
        Dept.setCellValueFactory(new PropertyValueFactory<MemberModel, String>("Dept"));
        DeptLeader.setCellValueFactory(new PropertyValueFactory<MemberModel, String>("DeptLeader"));
        HomeGroup.setCellValueFactory(new PropertyValueFactory<MemberModel, String>("HomeGroup"));
        Homephone.setCellValueFactory(new PropertyValueFactory<MemberModel, String>("Homephone"));
        IDNum.setCellValueFactory(new PropertyValueFactory<MemberModel, String>("IDNum"));
        KidsNo_.setCellValueFactory(new PropertyValueFactory<MemberModel, String>("KidsNo_"));
        MStatus_.setCellValueFactory(new PropertyValueFactory<MemberModel, String>("MStatus_"));
        MobilePhone.setCellValueFactory(new PropertyValueFactory<MemberModel, String>("MobilePhone"));
        Email.setCellValueFactory(new PropertyValueFactory<MemberModel, String>("Email"));
        Salvation.setCellValueFactory(new PropertyValueFactory<MemberModel, String>("Salvation"));
        Sex.setCellValueFactory(new PropertyValueFactory<MemberModel, String>("Sex"));
        waterBapt.setCellValueFactory(new PropertyValueFactory<MemberModel, String>("waterBapt"));
        SpiritBapt.setCellValueFactory(new PropertyValueFactory<MemberModel, String>("SpiritBapt"));
        Surbub.setCellValueFactory(new PropertyValueFactory<MemberModel, String>("Surbub"));
        WorkPhone.setCellValueFactory(new PropertyValueFactory<MemberModel, String>("WorkPhone"));
        
        try {
            records = loadTable();
            table_Listener();
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    }

}
