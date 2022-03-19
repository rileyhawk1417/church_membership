package com.cm.cm.controllers.admin.usersCtrl;

import com.cm.cm.database.Sqlite;
import com.cm.cm.modals.AlertModule;
import com.cm.cm.modals.UsersModel;
import com.cm.cm.controllers.misc.SceneCtrl;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.font.MFXFontIcon;
import io.github.palexdev.materialfx.utils.others.FunctionalStringConverter;
import javafx.collections.*;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;
import java.sql.*;
import javafx.fxml.FXML;
import javafx.util.StringConverter;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.scene.layout.GridPane;
import com.cm.cm.updater.config.UpdateConfig;

@SuppressWarnings("unchecked")
public class userCtrl implements Initializable {

    static ObservableList<UsersModel> usersList;

    static ObservableList<UsersModel> adminList;

    @FXML
    private GridPane Grid_;

    Sqlite sqlite = new Sqlite();
    Stage stage = new Stage();
    Window owner = stage.getOwner();
    SceneCtrl scene_switcher = new SceneCtrl();
    UpdateConfig update = new UpdateConfig();
    AlertModule diag = new AlertModule();
    String sample[] ={
            "hello",
            "Mike"
    };

    @FXML
    private MFXTableView<UsersModel> userTable;

    @FXML
    private MFXButton createUser;

    @FXML
    private MFXButton refreshUser;

    @FXML
    private MFXButton close;

    @FXML
    private MFXButton deleteBtn;

    @FXML
    private MenuItem refresh;

    @FXML
    private MenuItem deleteUser;

    @FXML
    private MenuItem loadUsers;

    @FXML
    private MenuItem loadAdmin;

    @Override
    public void initialize(URL location, ResourceBundle resource){
        //TODO: Add code to load list.
        try {

            usersList = loadUsers();
            setUpTable();
            userTable.autosizeColumnsOnInitialization();
        } catch(Exception e){
            e.printStackTrace();
        }
    }


    private ObservableList<UsersModel> loadUsers(){
        ObservableList<UsersModel>users_ = FXCollections.observableArrayList();

        try{
            Connection con = Sqlite.connector();

            ResultSet res = con.createStatement().executeQuery( "SELECT * FROM user_login");

            while(res.next()){
                users_.add(new UsersModel(res.getString("user_name"), res.getString("password"), res.getString("user_type"), res.getString("ID")));
            }

        } catch(SQLException e){
            System.out.println(e.getMessage());}

        return users_;
    }

    private void setUpTable(){
        MFXTableColumn<UsersModel> name_ = new MFXTableColumn<>("User Name", true, Comparator.comparing(UsersModel::getUser_name));
        MFXTableColumn<UsersModel> passWord_ = new MFXTableColumn<>("Password", true, Comparator.comparing(UsersModel::getPass));
        MFXTableColumn<UsersModel> user_type_ = new MFXTableColumn<>("User Type", true, Comparator.comparing(UsersModel::getUserType));

        name_.setRowCellFactory(userModel -> new MFXTableRowCell<>(UsersModel::getUser_name));
        passWord_.setRowCellFactory(userModel -> new MFXTableRowCell<>(UsersModel::getPass));
        user_type_.setRowCellFactory(userModel -> new MFXTableRowCell<>(UsersModel::getUserType));

        userTable.getTableColumns().addAll(name_, passWord_, user_type_);
        userTable.setItems(usersList);
    }


    @FXML
    private void newUser(){
        scene_switcher.createUser();
    }

    @FXML
    private void refreshList(){
        usersList.removeAll();
        usersList = loadUsers();
        userTable.setItems(usersList);
    }

    @FXML
    private void deleteSelected(){
//TODO: Rewrite entire function to work with tableview

        ObservableMap<Integer, UsersModel> tableMap = userTable.getSelectionModel().getSelection();
        ObservableList<UsersModel> tableList = FXCollections.observableArrayList(tableMap.values());
        String userId = tableList.listIterator().next().getID();
        String userType = tableList.listIterator().next().getUserType();
        String userName = tableList.listIterator().next().getUser_name();

        deleteRow(userId, userType, userName);
    }

    private void deleteRow(String id_, String user_type, String user_name){

        diag.deleteUserDialog(owner, AlertModule.dialogType.WARN, Grid_, id_, user_type);
    }

    @FXML
    private void closeList(){
        close.getScene().getWindow().hide();
        scene_switcher.admin_rec_scene();
    }
}
