package com.cm.cm.controllers.misc;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/*
 * Similar to a cheat script
 * To avoid repetition in several files
 */

/*
 * Format when using intellij
 * String decision_screen = "/test/hello-view.fxml";
 *
 * Format when using VSCode or raw
 * String cashier_login = "/resources/fxml/cashier/login_screen.fxml";
 * */
public class SceneCtrl {

    public String admin_records = "/com/cm/cm/fxml/admin/records_view.fxml";
    public String add_screen_admin = "/com/cm/cm/fxml/admin/add_records.fxml";
    public String kidsRecordsAdmin = "/com/cm/cm/fxml/admin/kids_records_view.fxml";
    public String users_screen = "/com/cm/cm/fxml/admin/usersCtrl/userList.fxml";
    public String create_user_screen = "/com/cm/cm/fxml/admin/usersCtrl/addUsers.fxml";

    public String add_screen_user = "/com/cm/cm/fxml/user/add_records.fxml";
    public String user_records = "/com/cm/cm/fxml/user/records_view.fxml";
    public String kidsRecordsUser = "/com/cm/cm/fxml/user/kids_records_view.fxml";

    public String guestRecords = "/com/cm/cm/fxml/guest/records_view.fxml";
    public String guestKidsRecords = "/com/cm/cm/fxml/guest/kids_records_view";

    public String decision_screen = "/com/cm/cm/fxml/misc/login_choice.fxml";
    public String update_screen = "/com/cm/cm/fxml/misc/update_window.fxml";
    public String startup_screen = "/com/cm/cm/fxml/misc/startup_view.fxml";
    public String greeting_screen = "/com/cm/cm/fxml/misc/greeting_banner.fxml";
    public String about_screen = "/com/cm/cm/fxml/misc/about.fxml";



    //TODO: Build user and guest screens for table entry.
    public Stage stage = new Stage();

    public void switchScene(Scene scene, boolean truth, String title, boolean option) {
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(truth);
        stage.setTitle(title);
        if (option == true) {
            stage.show();
        } else if (option == false) {
            stage.showAndWait();
        } else {
            System.out.println("Invalid Option");
        }
    }


    public void displayUsers() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(users_screen));
            Scene login = new Scene(root);
            switchScene(login, true, "Registered Users", false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createUser() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(create_user_screen));
            Scene records = new Scene(root);
            switchScene(records, true, "Create User", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void admin_add_rec() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(add_screen_admin));
            Scene add = new Scene(root);
            switchScene(add, true, "Admin: Add Record", false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void admin_rec_scene() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(admin_records));
            Scene records = new Scene(root);
            switchScene(records, true, "View Members", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void kids_records_Admin() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(kidsRecordsAdmin));
            Scene kids_rec = new Scene(root);
            switchScene(kids_rec, true, "Kids Records", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void user_add_rec() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(add_screen_user));
            Scene add = new Scene(root);
            switchScene(add, true, "User: Add Record", false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void user_rec_scene() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(user_records));
            Scene records = new Scene(root);
            switchScene(records, true, "User: View Members", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void kids_records_User(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource(kidsRecordsUser));
            Scene kids_rec = new Scene(root);
            switchScene(kids_rec, true, "Kids Records", true);
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void guest_rec_scene(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource(guestRecords));
            Scene records = new Scene(root);
            switchScene(records, true, "Guest: View Members", false);
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void guest_kids_rec_scene(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource(guestKidsRecords));
            Scene records = new Scene(root);
            switchScene(records, true, "Guest: View Kids Records", false);
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void decision_scene() {
        try {
            FXMLLoader loader = new FXMLLoader();
            Parent root = loader.load(getClass().getResource(decision_screen));
            Scene login = new Scene(root);
            switchScene(login, true, "Select Login Type", false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void about_scene() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(about_screen));
            Scene about = new Scene(root);
            switchScene(about, true, "About Screen", false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update_app_scene() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(update_screen));
            Scene about = new Scene(root);
            switchScene(about, true, "Update Application", false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startup_scene() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(startup_screen));
            Scene about = new Scene(root);
            switchScene(about, true, "Update Application", false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
