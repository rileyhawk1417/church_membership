package com.cm.cm.controllers.user;

import com.cm.cm.app.App;
import com.cm.cm.controllers.misc.SceneCtrl;
import com.cm.cm.database.Sqlite;
import com.cm.cm.modals.AlertModule;
import com.cm.cm.modals.MemberModel;
import com.cm.cm.controllers.misc.ExcelHelper;
import io.github.palexdev.materialfx.selection.base.IMultipleSelectionModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
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
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import io.github.palexdev.materialfx.controls.MFXPaginatedTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.EnumFilter;
import io.github.palexdev.materialfx.filter.IntegerFilter;
import io.github.palexdev.materialfx.filter.StringFilter;

import javafx.geometry.Insets;
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
import java.util.Comparator;
import java.util.ResourceBundle;


public class MFXControl implements Initializable {

    Stage stage = new Stage();
    Sqlite sqlite = new Sqlite();
    App app = new App();
    static String recordSize = "";


    Window owner = stage.getOwner();
    SceneCtrl scene_switcher = new SceneCtrl();

    @FXML
    private MFXScrollPane scroll_pane;

    @FXML
    private BorderPane BP;


    // Start Of Table Properties

    @FXML
    private MFXTableView<MemberModel> psqlTable;

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

    // Button Start
    @FXML
    private Button subQuery;

    static ObservableList<MemberModel> records;

    @Override
    public void initialize(URL location, ResourceBundle bundle) {
        assert psqlTable != null : "Failed to load table";


        try {
            records = loadTable();
             setUpTable();
            psqlTable.autosizeColumnsOnInitialization();
             scroll_pane.setContent(psqlTable);
            scroll_pane.setPrefSize(650, 200);
            scroll_pane.setFitToHeight(true);
            scroll_pane.setHmax(3);
            scroll_pane.setHvalue(0);
            scroll_pane.setDisable(false);

            BP.setCenter(scroll_pane);
            BorderPane.setMargin(scroll_pane, new Insets(0, 10, 10, 10));



            mouse_listener();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ObservableList<MemberModel> loadTable() {
        ObservableList<MemberModel> loadList = FXCollections.observableArrayList();

        try {
            Connection con = Sqlite.connector();

            ResultSet res = con.createStatement().executeQuery("SELECT * FROM members");

            while (res.next()) {
                loadList.add(new MemberModel(res.getString("id"), res.getString("title"), res.getString("fname"),
                        res.getString("lname"), res.getString("gender"), res.getString("id_no"), res.getString("children_num"),
                        res.getString("maritial_status"), res.getString("date_joined"), res.getString("dob"),
                        res.getString("address"), res.getString("surbub"), res.getString("home_phone"), res.getString("work_phone"),
                        res.getString("mobile_phone"), res.getString("employer"), res.getString("position"), res.getString("email"),
                        res.getString("home_group_leader"), res.getString("department_leader"), res.getString("department"),
                        res.getString("salvation"), res.getString("water_baptism"), res.getString("spirit_baptism")));
            }
            int rowSize = loadList.size();
            recordSize = Integer.toString(rowSize);
            System.out.println(loadList.size());
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.out.println("Error loading Table");
        }
        return loadList;
    }

    // public static String childrenSize(){
    // try{Connection con = Psql.connector();

    // Statement stmt = con.createStatement();
    // ResultSet res = stmt.executeQuery("SELECT SUM (children_num) FROM members");
    // ResultSetMetaData rs = res.getMetaData();
    // int children =
    // } catch (Exception e){
    // e.printStackTrace();
    // };

    // return children = childrenSize;
    // }

    private void setUpTable(){
         MFXTableColumn<MemberModel> id_ = new MFXTableColumn<>("ID", true, Comparator.comparing(MemberModel::getID));

         MFXTableColumn<MemberModel> fname = new MFXTableColumn<>("Name", true, Comparator.comparing(MemberModel::getFname));

         MFXTableColumn<MemberModel> lname = new MFXTableColumn<>("Surname", true, Comparator.comparing(MemberModel::getLname));

         MFXTableColumn<MemberModel> Title_ = new MFXTableColumn<>("Title", true, Comparator.comparing(MemberModel::getTitle));

         MFXTableColumn<MemberModel> Address = new MFXTableColumn<>("Address", true, Comparator.comparing(MemberModel::getAddress));

         MFXTableColumn<MemberModel> DOB_ = new MFXTableColumn<>("D.O.B", true, Comparator.comparing(MemberModel::getDOB));

         MFXTableColumn<MemberModel> DateJoined_ = new MFXTableColumn<>("Date Joined", true, Comparator.comparing(MemberModel::getDatejoined));

         MFXTableColumn<MemberModel> Dept = new MFXTableColumn<>("Department", true, Comparator.comparing(MemberModel::getDept));

         MFXTableColumn<MemberModel> DeptLeader = new MFXTableColumn<>("Dept Leader", true, Comparator.comparing(MemberModel::getDeptLeader_));

         MFXTableColumn<MemberModel> HomeGroup = new MFXTableColumn<>("Home Group", true, Comparator.comparing(MemberModel::getHomeGroup));

         MFXTableColumn<MemberModel> Homephone = new MFXTableColumn<>("Landline", true, Comparator.comparing(MemberModel::getHomePhone));

         MFXTableColumn<MemberModel> IDNum = new MFXTableColumn<>("ID No.", true, Comparator.comparing(MemberModel::getID_Num));

         MFXTableColumn<MemberModel> KidsNo_ = new MFXTableColumn<>("No. Of Kids", true, Comparator.comparing(MemberModel::getChildrenNo_));

         MFXTableColumn<MemberModel> MStatus_ = new MFXTableColumn<>("Maritial Status", true, Comparator.comparing(MemberModel::getM_status));

         MFXTableColumn<MemberModel> MobilePhone = new MFXTableColumn<>("Mobile No.", true, Comparator.comparing(MemberModel::getCellNumber));

         MFXTableColumn<MemberModel> Email = new MFXTableColumn<>("Email", true, Comparator.comparing(MemberModel::getEmail));

         MFXTableColumn<MemberModel> Salvation = new MFXTableColumn<>("Salvation", true, Comparator.comparing(MemberModel::getSalvation));

         MFXTableColumn<MemberModel> Sex = new MFXTableColumn<>("Gender", true, Comparator.comparing(MemberModel::getSex));

         MFXTableColumn<MemberModel> waterBapt = new MFXTableColumn<>("Water Baptism", true, Comparator.comparing(MemberModel::getWaterBapt));

         MFXTableColumn<MemberModel> SpiritBapt = new MFXTableColumn<>("Spirit Baptism", true, Comparator.comparing(MemberModel::getSpiritBapt));

         MFXTableColumn<MemberModel> Surbub = new MFXTableColumn<>("Surbub", true, Comparator.comparing(MemberModel::getSurbub));

         MFXTableColumn<MemberModel> Employer = new MFXTableColumn<>("Employer", true, Comparator.comparing(MemberModel::getEmployer_));

         MFXTableColumn<MemberModel> WorkPhone = new MFXTableColumn<>("Work Phone", true, Comparator.comparing(MemberModel::getWorkPhone));

         id_.setRowCellFactory(memberModal -> new MFXTableRowCell<>(MemberModel::getID));
         fname.setRowCellFactory(memberModal -> new MFXTableRowCell<>(MemberModel::getFname));
         lname.setRowCellFactory(memberModal -> new MFXTableRowCell<>(MemberModel::getLname));
         Title_.setRowCellFactory(memberModal -> new MFXTableRowCell<>(MemberModel::getTitle));
         Address.setRowCellFactory(memberModal -> new MFXTableRowCell<>(MemberModel::getAddress));
         DOB_.setRowCellFactory(memberModal -> new MFXTableRowCell<>(MemberModel::getDOB));
         DateJoined_.setRowCellFactory(memberModal -> new MFXTableRowCell<>(MemberModel::getDatejoined));
         Dept.setRowCellFactory(memberModal -> new MFXTableRowCell<>(MemberModel::getDept));
         DeptLeader.setRowCellFactory(memberModal -> new MFXTableRowCell<>(MemberModel::getDeptLeader_));
         HomeGroup.setRowCellFactory(memberModal -> new MFXTableRowCell<>(MemberModel::getHomeGroup));
         Homephone.setRowCellFactory(memberModal -> new MFXTableRowCell<>(MemberModel::getHomePhone));
         IDNum.setRowCellFactory(memberModal -> new MFXTableRowCell<>(MemberModel::getID_Num));
         KidsNo_.setRowCellFactory(memberModal -> new MFXTableRowCell<>(MemberModel::getChildrenNo_));
         MStatus_.setRowCellFactory(memberModal -> new MFXTableRowCell<>(MemberModel::getM_status));
         MobilePhone.setRowCellFactory(memberModal -> new MFXTableRowCell<>(MemberModel::getCellNumber));
         Email.setRowCellFactory(memberModal -> new MFXTableRowCell<>(MemberModel::getEmail));
         Salvation.setRowCellFactory(memberModal -> new MFXTableRowCell<>(MemberModel::getSalvation));
         Sex.setRowCellFactory(memberModal -> new MFXTableRowCell<>(MemberModel::getSex));
         waterBapt.setRowCellFactory(memberModal -> new MFXTableRowCell<>(MemberModel::getWaterBapt));
         SpiritBapt.setRowCellFactory(memberModal -> new MFXTableRowCell<>(MemberModel::getSpiritBapt));
         Surbub.setRowCellFactory(memberModal -> new MFXTableRowCell<>(MemberModel::getSurbub));
         WorkPhone.setRowCellFactory(memberModal -> new MFXTableRowCell<>(MemberModel::getWorkPhone));
         Employer.setRowCellFactory(memberModal -> new MFXTableRowCell<>(MemberModel::getEmployer_));

         psqlTable.getTableColumns().addAll(
                 id_,
                 Title_,
                 fname,
                 lname,
                 Sex,
                 IDNum,
                 KidsNo_,
                 MStatus_,
                 DateJoined_,
                 DOB_,
                 Address,
                 Surbub,
                 Homephone,
                 WorkPhone,
                 MobilePhone,
                 Email,
                 Employer,
                 HomeGroup,
                 DeptLeader,
                 Dept,
                 Salvation,
                 waterBapt,
                 SpiritBapt
         );

         psqlTable.setItems(records);


    }

    public static ObservableList<MemberModel> searchDB(String query, Window owner) {
        ObservableList<MemberModel> queryList = FXCollections.observableArrayList();
        String search = "select * from members WHERE fname LIKE '" + query + "%'";
        try (Connection conn = Sqlite.connector(); PreparedStatement pstmt = conn.prepareStatement(search);) {
            ResultSet res = pstmt.executeQuery();

            try {
                while (res.next()) {
                    queryList.add(new MemberModel(res.getString("id_num"), res.getString("title"), res.getString("fname"),
                            res.getString("lname"), res.getString("gender"), res.getString("id_no"), res.getString("children_num"),
                            res.getString("maritial_status"), res.getString("date_joined"), res.getString("dob"),
                            res.getString("address"), res.getString("surbub"), res.getString("home_phone"),
                            res.getString("work_phone"), res.getString("mobile_phone"), res.getString("employer"),
                            res.getString("position"), res.getString("email"), res.getString("home_group_leader"),
                            res.getString("department_leader"), res.getString("department"), res.getString("salvation"),
                            res.getString("water_baptism"), res.getString("spirit_baptism")));
                }
                // TODO:Error still triggered even if results are found
                // if(!res.next()){
                // AlertModule.showAlert(Alert.AlertType.ERROR, owner, "System Error", "Failed
                // to load results from DB");

                // System.out.println("Search failed");
                // }
            } catch (Exception e) {
                AlertModule.showAlert(Alert.AlertType.ERROR, owner, "System Error", "Nothing matches your search");
                System.out.println(e);
                System.out.println("Nothing found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // TODO: handle exception
        }
        return queryList;
    }

    public void addScreen() {
        try {
            scene_switcher.add_scene();
            loadTable();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void deleteRow() {
//        IMultipleSelectionModel<MemberModel> selectedRow;
        ObservableList<MemberModel> allRows, selectedRow;
        allRows = psqlTable.getItems();
//        selectedRow = psqlTable.getSelectionModel().getSelectedItem();

//        String id_ = String.valueOf(psqlTable.getSelectionModel().getSelection());
        System.out.println(id_);
//        sqlite.delete_row_by_id(id_);
//        selectedRow.clearSelection();
//        selectedRow.forEach(allRows::remove);
    }

    // File actions
    @FXML
    private void add_screen() {
        addScreen();
    }

    /*
     * Reference methods or reminders to show that when dealing with private values
     * they cant be passed to another class //TODO Method 1 //
     * update.setField("150", "25", "3", "4", "5");
     *
     * //TODO Method 2 // UpdateCtrl update = new UpdateCtrl("1", "2", "3", "4",
     * "5");
     */

    /*
     * Method 3 or function table_click_listener This method listens for a selected
     * record then displays it on the side panel for update.
     */

    public void mouse_listener() {
//        psqlTable.getSelectionModel().selectedItemProperty().addListener((obs, old_selection, new_selection) -> {
//            title_.setText(new_selection.getTitle());
//            name_.setText(new_selection.getFname());
//            gender_.setText(new_selection.getSex());
//            idNo_.setText(new_selection.getID_Num());
//            children_No_.setText(new_selection.getChildrenNo_());
//            maritial_.setText(new_selection.getM_status());
//            dateJoined_.setText(new_selection.getDatejoined());
//            dob_.setText(new_selection.getDOB());
//            address_.setText(new_selection.getAddress());
//            surbub_.setText(new_selection.getSurbub());
//            homePhone_.setText(new_selection.getHomePhone());
//            workPhone_.setText(new_selection.getWorkPhone());
//            mobilePhone_.setText(new_selection.getCellNumber());
//            employer_.setText(new_selection.getEmployer_());
//            position_.setText(new_selection.getPosition_());
//            email_.setText(new_selection.getEmail());
//            homeGroup_.setText(new_selection.getHomeGroup());
//            depLeader_.setText(new_selection.getDeptLeader_());
//            dep.setText(new_selection.getDept());
//            salvation_.setText(new_selection.getSalvation());
//            water_.setText(new_selection.getWaterBapt());
//            spirit_.setText(new_selection.getSpiritBapt());
//            membersTotal_.setText(recordSize);
//
//            // TODO: Set childrens totals.
//
//        });
    }

    @FXML
    private void confirm_update() {
        try {
            // TODO: Fix this function
            // psql.updateValues(side_name_entry.getText(), side_detail_entry.getText(),
            // side_units_used_entry.getText(), side_units_left_entry.getText(),
            // side_unit_price_entry.getText(), side_restock_entry.getText(),
            // side_id_entry.getText());
            AlertModule.showAlert(Alert.AlertType.CONFIRMATION, owner, "Action Completed", "Record updated successfully");
            reloadBtn();
        } catch (Exception e) {
            AlertModule.showAlert(Alert.AlertType.ERROR, owner, "Failed to complete action", "Failed to update record");
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
        scene_switcher.bulk_delete_scene();
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
            AlertModule.showAlert(Alert.AlertType.ERROR, owner, "Action Error", "Unable to complete search");
        }
    }

    // TODO cannot make static reference to FXML as they would crash program
    // @FXML
    private void reloadBtn() {
        // Clear current view then load results
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
        ExcelHelper.exportToExcel(owner);
    }

    @FXML
    private void export_tableView() {
//        Workbook wb = new HSSFWorkbook();
//        Sheet spreadsheet = wb.createSheet("Current TableView");
//
//        Row row = spreadsheet.createRow(0);
//
//        for (int i = 0; i < psqlTable.getColumns().size(); i++) {
//            row.createCell(i).setCellValue(psqlTable.getColumns().get(i).getText());
//        }
//
//        for (int x = 0; x < psqlTable.getItems().size(); x++) {
//            row = spreadsheet.createRow(x + 1);
//            for (int y = 0; y < psqlTable.getColumns().size(); y++) {
//                if (psqlTable.getColumns().get(y).getCellData(x) != null) {
//                    row.createCell(y).setCellValue(psqlTable.getColumns().get(y).getCellData(x).toString());
//                } else {
//                    row.createCell(y).setCellValue("");
//                }
//            }
//        }
//        FileChooser fileChooser = new FileChooser();
//        fileChooser.setTitle("Export Excel Document");
//        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("XLSX files (*.xlsx)", "*.xlsx"),
//                new FileChooser.ExtensionFilter("XLS files (*.xls)", "*.xls"),
//                new FileChooser.ExtensionFilter("ODS files (*.ods)", "*.ods"),
//                new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv"),
//                new FileChooser.ExtensionFilter("HTML files (*.html)", "*.html"));
//        try {
//            File saveFile = fileChooser.showSaveDialog(owner);
//            String savePath = "Exported TableView.xlsx";
//            FileOutputStream save_file = new FileOutputStream(savePath);
//            fileChooser.setInitialFileName("Exported Table");
//            // TODO Set initial filename not working when saving?
//
//            Path src = Paths.get(savePath);
//            Path dest = Paths.get(saveFile.getAbsolutePath());
//
//            StandardCopyOption REPLACE_EXISTING = StandardCopyOption.REPLACE_EXISTING;
//            StandardCopyOption COPY_ATTRIBUTES = StandardCopyOption.COPY_ATTRIBUTES;
//            LinkOption NOFOLLOW_LINKS = LinkOption.NOFOLLOW_LINKS;
//
//            if (saveFile != null) {
//                try {
//                    // try to save workbook
//                    wb.write(save_file);
//
//                    // try to copy and delete file
//                    Files.copy(src, dest, REPLACE_EXISTING, COPY_ATTRIBUTES, NOFOLLOW_LINKS);
//                    Files.delete(src);
//                    wb.close();
//                } catch (IOException e) {
//                    e.getMessage();
//                    e.printStackTrace();
//                }
//            }
//        } catch (FileNotFoundException e) {
//            e.getMessage();
//            e.printStackTrace();
//        }
    }

    /*
     * User actions
     */
    @FXML
    private void manual_btn() {
        try {
            app.manual_pdf();
        } catch (Exception e) {
            // TODO Auto-generated catch block
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
        scene_switcher.kids_records_scene();
    }

    @FXML
    private void exitBtn(ActionEvent event) {
        App.closeApp();
    }

}


