package com.cm.cm.controllers.admin;

import com.cm.cm.app.App;
import com.cm.cm.controllers.misc.ExcelHelper;
import com.cm.cm.controllers.misc.SceneCtrl;
import com.cm.cm.controllers.user.Insert_update;
import com.cm.cm.database.Sqlite;
import com.cm.cm.modals.AlertModule;
import com.cm.cm.modals.MemberModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;




public class Table_View implements Initializable {

    Stage stage = new Stage();
    Sqlite sqlite = new Sqlite();
    App app = new App();
    static String recordSize = "";
//    Insert_update Insert_update = new Insert_update();

    Window owner = stage.getOwner();
    SceneCtrl scene_switcher = new SceneCtrl();
    AlertModule alertBox = new AlertModule();

    ExcelHelper excelFunc = new ExcelHelper();


    @FXML
    private ScrollPane scroll_pane;

    @FXML
    private BorderPane BP;


    // Start Of Table Properties
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
   private TableColumn<MemberModel, String> Employer;

   @FXML
   private TableColumn<MemberModel, String> WorkPhone;

    // Table Properties End

    // Text Properties Start
    @FXML
    private Text address_;

    @FXML
    private Text childrenTotal_;

    @FXML
    private Text children_No_;

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

    // Menu Items Start
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
    private MenuItem import_;

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

    @FXML
    private MenuItem show_users_;

    // Button Start
    @FXML
    private Button subQuery;

    static ObservableList<MemberModel> records;

    @Override
    public void initialize(URL location, ResourceBundle bundle) {
        assert psqlTable != null : "Failed to load table";

        try {
            // reload();
            records = loadTable();
            setUpTable();
            mouse_listener();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<MemberModel> loadTable() {
        ObservableList<MemberModel> loadList = FXCollections.observableArrayList();

        try {
            Connection con = Sqlite.connector();

            ResultSet res = con.createStatement().executeQuery("SELECT * FROM members");

            while (res.next()) {
                loadList.add(new MemberModel(res.getString("id"), res.getString("title"), res.getString("fname"),
                        res.getString("lname"), res.getString("gender"), res.getString("id_no"), res.getString("kids_num"),
                        res.getString("maritial_status"), res.getString("date_joined"), res.getString("dob"),
                        res.getString("address"), res.getString("surbub"), res.getString("landline"), res.getString("work_num"),
                        res.getString("cell_num"), res.getString("employer"), res.getString("position"), res.getString("email"),
                        res.getString("home_group_leader"), res.getString("dept_leader"), res.getString("dept"),
                        res.getString("salvation"), res.getString("water_bapt"), res.getString("spirit_bapt")));
            }
            int rowSize = loadList.size();
            recordSize = Integer.toString(rowSize);
            System.out.println(loadList.size());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.out.println("Error loading Table");
        }
        return loadList;
    }

    private void setUpTable(){

        id_.setCellValueFactory(new PropertyValueFactory<MemberModel, String>("ID"));
        fname.setCellValueFactory(new PropertyValueFactory<MemberModel, String>("fname"));
        lname.setCellValueFactory(new PropertyValueFactory<MemberModel, String>("lname"));
        Title_.setCellValueFactory(new PropertyValueFactory<MemberModel, String>("Title"));
        Address.setCellValueFactory(new PropertyValueFactory<MemberModel, String>("Address"));
        DOB_.setCellValueFactory(new PropertyValueFactory<MemberModel, String>("DOB"));
        DateJoined_.setCellValueFactory(new PropertyValueFactory<MemberModel, String>("Datejoined"));
        Dept.setCellValueFactory(new PropertyValueFactory<MemberModel, String>("Dept"));
        DeptLeader.setCellValueFactory(new PropertyValueFactory<MemberModel, String>("deptLeader_"));
        HomeGroup.setCellValueFactory(new PropertyValueFactory<MemberModel, String>("HomeGroup"));
        Homephone.setCellValueFactory(new PropertyValueFactory<MemberModel, String>("HomePhone"));
        IDNum.setCellValueFactory(new PropertyValueFactory<MemberModel, String>("ID_Num"));
        KidsNo_.setCellValueFactory(new PropertyValueFactory<MemberModel, String>("childrenNo_"));
        MStatus_.setCellValueFactory(new PropertyValueFactory<MemberModel, String>("M_status"));
        MobilePhone.setCellValueFactory(new PropertyValueFactory<MemberModel, String>("cellNumber"));
        Email.setCellValueFactory(new PropertyValueFactory<MemberModel, String>("Email"));
        Salvation.setCellValueFactory(new PropertyValueFactory<MemberModel, String>("Salvation"));
        Sex.setCellValueFactory(new PropertyValueFactory<MemberModel, String>("Sex"));
        waterBapt.setCellValueFactory(new PropertyValueFactory<MemberModel, String>("waterBapt"));
        SpiritBapt.setCellValueFactory(new PropertyValueFactory<MemberModel, String>("SpiritBapt"));
        Surbub.setCellValueFactory(new PropertyValueFactory<MemberModel, String>("Surbub"));
        WorkPhone.setCellValueFactory(new PropertyValueFactory<MemberModel, String>("WorkPhone"));
        Employer.setCellValueFactory(new PropertyValueFactory<MemberModel, String>("Employer_"));

//        psqlTable.setTableMenuButtonVisible(true);
        scroll_pane.setContent(psqlTable);
        scroll_pane.setPrefSize(600, 200);
        scroll_pane.setFitToHeight(true);
        scroll_pane.setHmax(3);
        scroll_pane.setHvalue(0);
        scroll_pane.setDisable(false);

        BP.setCenter(scroll_pane);
        BorderPane.setMargin(scroll_pane, new Insets(0, 10, 10, 10));
        psqlTable.setItems(records);
    }

    public ObservableList<MemberModel> searchDB(String query, Window owner) {
        ObservableList<MemberModel> queryList = FXCollections.observableArrayList();
        String search = "select * from members WHERE fname LIKE '" + query + "%'";
        try (Connection conn = Sqlite.connector(); PreparedStatement pstmt = conn.prepareStatement(search)) {
            ResultSet res = pstmt.executeQuery();

            try {
                while (res.next()) {
                    //Find a way to get tableName then load it into members
                    queryList.add(new MemberModel(res.getString("id_num"), res.getString("title"), res.getString("fname"),
                            res.getString("lname"), res.getString("gender"), res.getString("id_no"), res.getString("kids_num"),
                            res.getString("maritial_status"), res.getString("date_joined"), res.getString("dob"),
                            res.getString("address"), res.getString("surbub"), res.getString("landline"),
                            res.getString("work_num"), res.getString("cell_num"), res.getString("employer"),
                            res.getString("position"), res.getString("email"), res.getString("home_group_leader"),
                            res.getString("dept_leader"), res.getString("dept"), res.getString("salvation"),
                            res.getString("water_bapt"), res.getString("spirit_bapt")));
                }
                // TODO:Error still triggered even if results are found
                // if(!res.next()){
                // AlertModule.showAlert(Alert.AlertType.ERROR, owner, "System Error", "Failed
                // to load results from DB");

                // System.out.println("Search failed");
                // }
            } catch (Exception e) {
                alertBox.showMFXAlert( owner, "System Error", "Nothing matches your search", AlertModule.dialogType.ERR, BP);
                System.out.println(e);
                System.out.println("Nothing found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return queryList;
    }

    public void addScreen() {
        try {

            scene_switcher.admin_add_rec();
            Insert_update.updateBtn(false);
            loadTable();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void deleteRow(){
        ObservableList<MemberModel> allRows, selectedRow;
        allRows = psqlTable.getItems();
        selectedRow = psqlTable.getSelectionModel().getSelectedItems();
        String id_ = psqlTable.getSelectionModel().getSelectedItem().getID();

        deletePopup(id_);
        selectedRow.forEach(allRows::remove);
    }

    public void deletePopup(String id) {
        alertBox.deleteRecDialog(owner, AlertModule.dialogType.ERR, BP, id, "members");
    }

    // File actions
    @FXML
    private void add_screen() {
        addScreen();
    }

    /*
     * Reference methods or reminders to show that when dealing with private values
     * they cant be passed to another class Method 1
     * update.setField("150", "25", "3", "4", "5");
     *
     * UpdateCtrl update = new UpdateCtrl("1", "2", "3", "4",
     * "5");
     */

    /*
     * Method 3 or function table_click_listener This method listens for a selected
     * record then displays it on the side panel for update.
     */

    private void mouse_listener() {
        psqlTable.getSelectionModel().selectedItemProperty().addListener((obs, old_selection, new_selection) -> {
            title_.setText(new_selection.getTitle());
            name_.setText(new_selection.getFname() + new_selection.getLname());
            gender_.setText(new_selection.getSex());
            idNo_.setText(new_selection.getID_Num());
            children_No_.setText(new_selection.getChildrenNo_());
            maritial_.setText(new_selection.getM_status());
            dateJoined_.setText(new_selection.getDatejoined());
            dob_.setText(new_selection.getDOB());
            address_.setText(new_selection.getAddress());
            surbub_.setText(new_selection.getSurbub());
            homePhone_.setText(new_selection.getHomePhone());
            workPhone_.setText(new_selection.getWorkPhone());
            mobilePhone_.setText(new_selection.getCellNumber());
            employer_.setText(new_selection.getEmployer_());
            position_.setText(new_selection.getPosition_());
            email_.setText(new_selection.getEmail());
            homeGroup_.setText(new_selection.getHomeGroup());
            depLeader_.setText(new_selection.getDeptLeader_());
            dep.setText(new_selection.getDept());
            salvation_.setText(new_selection.getSalvation());
            water_.setText(new_selection.getWaterBapt());
            spirit_.setText(new_selection.getSpiritBapt());
            membersTotal_.setText(recordSize);
        });
    }

    @FXML
    private void confirm_update() {
        try {
            // TODO: Fix this function
            reloadBtn();
        } catch (Exception e) {
            alertBox.showMFXAlert(owner, "Failed to complete action", "Failed to update record", AlertModule.dialogType.ERR, BP);
            e.getMessage();
            e.printStackTrace();
        }
    }

    @FXML
    private void discard_changes() {
        // side_id_entry.clear();
        // side_name_entry.clear();
        // side_detail_entry.clear();
        // side_units_used_entry.clear();
        // side_units_left_entry.clear();
        // side_unit_price_entry.clear();

    }

    @FXML
    private void delete_by_id_btn() {
        deleteRow();
    }

    @FXML
    private void delete_by_name_btn() {

    }

    @FXML
    private void searchBtn(ActionEvent event) {
        Window owner = subQuery.getScene().getWindow();
        try {
            records.removeAll();
            records = searchDB(searchBar.getText(), owner);
            psqlTable.setItems(records);
        } catch (Exception e) {
            e.printStackTrace();
            alertBox.showMFXAlert(owner, "Search Failed", "Nothing matches your search", AlertModule.dialogType.ERR, BP);
        }
    }

    private void reloadBtn() {
        records.removeAll();
        records = loadTable();
        psqlTable.setItems(records);
    }

    @FXML
    private void reload() {
        reloadBtn();
    }

    /**
     *
     * *The methods to export records to excel *ExportExcel.exportToExcel() exports
     * all records in database. *export_tableView() exports the current table view
     * of the records.
     */
    @FXML
    private void export_rec(ActionEvent event) throws IOException {
        Window owner = psqlTable.getScene().getWindow();
        excelFunc.exportToExcel(owner);
    }

    @FXML
    private void export_tableView() {
        Workbook wb = new HSSFWorkbook();
        Sheet spreadsheet = wb.createSheet("Current TableView");

        Row row = spreadsheet.createRow(0);

        for (int i = 0; i < psqlTable.getColumns().size(); i++) {
            row.createCell(i).setCellValue(psqlTable.getColumns().get(i).getText());
        }

        for (int x = 0; x < psqlTable.getItems().size(); x++) {
            row = spreadsheet.createRow(x + 1);
            for (int y = 0; y < psqlTable.getColumns().size(); y++) {
                if (psqlTable.getColumns().get(y).getCellData(x) != null) {
                    row.createCell(y).setCellValue(psqlTable.getColumns().get(y).getCellData(x).toString());
                } else {
                    row.createCell(y).setCellValue("");
                }
            }
        }
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Export Excel Document");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("XLSX files (*.xlsx)", "*.xlsx"),
                new FileChooser.ExtensionFilter("XLS files (*.xls)", "*.xls"),
                new FileChooser.ExtensionFilter("ODS files (*.ods)", "*.ods"),
                new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv"),
                new FileChooser.ExtensionFilter("HTML files (*.html)", "*.html"));
        try {
            File saveFile = fileChooser.showSaveDialog(owner);
            String savePath = "Exported TableView.xlsx";
            FileOutputStream save_file = new FileOutputStream(savePath);
            fileChooser.setInitialFileName("Exported Table");

            Path src = Paths.get(savePath);
            Path dest = Paths.get(saveFile.getAbsolutePath());

            StandardCopyOption REPLACE_EXISTING = StandardCopyOption.REPLACE_EXISTING;
            StandardCopyOption COPY_ATTRIBUTES = StandardCopyOption.COPY_ATTRIBUTES;
            LinkOption NOFOLLOW_LINKS = LinkOption.NOFOLLOW_LINKS;

            if (saveFile != null) {
                try {
                    // try to save workbook
                    wb.write(save_file);

                    // try to copy and delete file
                    Files.copy(src, dest, REPLACE_EXISTING, COPY_ATTRIBUTES, NOFOLLOW_LINKS);
                    Files.delete(src);
                    wb.close();
                } catch (IOException e) {
                    e.getMessage();
                    e.printStackTrace();
                }
            }
        } catch (FileNotFoundException e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    @FXML
    private void importer(){
        excelFunc.importToDBAdults(owner, BP);
    }

    /*
     * User actions
     */
    @FXML
    private void manual_btn() {
        try {
            app.manual_pdf();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void about_btn() {
        scene_switcher.about_scene();
    }

    @FXML
    private void switch_user() {
        psqlTable.getScene().getWindow().hide();
        scene_switcher.kids_records_Admin();
    }

    @FXML
    private void logout(){
        owner.hide();
        scene_switcher.decision_scene(); }

    @FXML
    private void exitBtn(ActionEvent event) {
        App.closeApp();
    }

    @FXML
    private void show_users(){
        psqlTable.getScene().getWindow().hide();
        scene_switcher.displayUsers();
    }

}

