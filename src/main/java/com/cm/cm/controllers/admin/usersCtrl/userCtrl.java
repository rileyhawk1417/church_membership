package com.cm.cm.controllers.admin.usersCtrl;

import com.cm.cm.database.Sqlite;
import com.cm.cm.modals.UsersModel;
import com.cm.cm.controllers.misc.SceneCtrl;
import io.github.palexdev.materialfx.controls.MFXListView;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.cell.MFXListCell;
import io.github.palexdev.materialfx.font.MFXFontIcon;
import io.github.palexdev.materialfx.utils.others.FunctionalStringConverter;
import io.github.palexdev.virtualizedfx.utils.ListChangeHelper;
import javafx.beans.value.ObservableMapValue;
import javafx.collections.*;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.sql.*;
import javafx.fxml.FXML;
import javafx.util.StringConverter;
import javafx.stage.Stage;
import javafx.stage.Window;

public class userCtrl implements Initializable {

    static ObservableList<UsersModel> usersList;

    static ObservableList<UsersModel> adminList;

    Sqlite sqlite = new Sqlite();
    Stage stage = new Stage();
    Window owner = stage.getOwner();
    SceneCtrl scene_switcher = new SceneCtrl();

    String sample[] ={
            "hello",
            "Mike"
    };

    @FXML
    private MFXListView<UsersModel> userList;

    @FXML
    private MFXListView<UsersModel> adminUserList;

    @FXML
    private MFXButton createUser;

    @FXML
    private MFXButton refreshUser;

    @FXML
    private MFXButton close;

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
            usersList = loadAdmins();
            adminList = loadUsers();
            setUpAdminList();
            setUpUserList();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    private ObservableList<UsersModel> loadAdmins(){
        ObservableList<UsersModel>users_ = FXCollections.observableArrayList();

        try{
            Connection con = Sqlite.connector();

            ResultSet res = con.createStatement().executeQuery( "SELECT * FROM admin_login");

            while(res.next()){
                users_.add(new UsersModel(res.getString("user_name"), res.getString("password"), res.getString("user_type"), res.getString("ID")));
            }

            } catch(SQLException e){
            System.out.println(e.getMessage());}

        return users_;
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

    private void setUpAdminList(){
        StringConverter<UsersModel> convert = FunctionalStringConverter.to(user -> (user == null) ? "" : "user: " + user.getUser_name()+ " | " + "password: " + user.getPass());
        adminUserList.setConverter(convert);
        adminUserList.setCellFactory(userModel -> new UserCellFactory(userList, userModel));
        adminUserList.setItems(adminList);
        adminUserList.features().enableBounceEffect();
        adminUserList.features().enableSmoothScrolling(0.5);
    }

    private void setUpUserList(){
        StringConverter<UsersModel> convert = FunctionalStringConverter.to(user -> (user == null) ? "" : "user: " + user.getUser_name()+ " | " + "password: " + user.getPass());
        userList.setConverter(convert);
        userList.setCellFactory(userModel -> new UserCellFactory(userList, userModel));
        userList.setItems(usersList);
        userList.features().enableBounceEffect();
        userList.features().enableSmoothScrolling(0.5);
    }

    private static class UserCellFactory extends MFXListCell<UsersModel>{
        private MFXFontIcon userIcon;
        public UserCellFactory(MFXListView<UsersModel> listView, UsersModel data){
            super(listView, data);

            userIcon = new MFXFontIcon("mfx-user", 18);
            userIcon.getStyleClass().add("user-icon");
            render(data);
        }

        @Override
        protected void render(UsersModel data){
            super.render(data);
            if (userIcon != null) getChildren().add(0, userIcon);

        }


    }

    @FXML
    private void newUser(){
        scene_switcher.createUser();
    }

    @FXML
    private void refreshList(){
        usersList.removeAll();
        adminList.removeAll();
        adminList = loadUsers();
        usersList = loadAdmins();
        userList.setItems(usersList);
        adminUserList.setItems(adminList);
    }

    UsersModel model = new UsersModel();
    @FXML
    private void deleteSelected(){
        ObservableMap<Integer, UsersModel> listValues = userList.getSelectionModel().getSelection();
        ObservableList<UsersModel> namesList = FXCollections.observableArrayList(listValues.values());

        System.out.println(namesList.listIterator().next().getID());

    }

    @FXML
    private void loadUserTable(){}

    @FXML
    private void loadAdminTable(){}

    @FXML
    private void closeList(){
//        close.getScene().getWindow().hide();
        close.getScene().getWindow().hide();
        scene_switcher.admin_rec_scene();
    }
}
