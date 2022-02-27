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
    // TODO not seeing resources folder
    String admin_login = "/com/cm/cm/fxml/admin/login_screen.fxml";
    String cashier_login = "/com/cm/cm/fxml/cashier/login_screen.fxml";
    String decision_screen = "/com/cm/cm/fxml/misc/login_choice.fxml";
    String add_screen = "/com/cm/cm/fxml/user/add_records.fxml";
    String admin_records = "/com/cm/cm/fxml/user/records_view.fxml";
    //    String decision_screen = "/test/hello-view.fxml";
    String cashier_records = "/com/cm/cm/fxml/cashier/records_view.fxml";
    String greeting_screen = "/com/cm/cm/fxml/greeting_banner.fxml";
    String bulk_delete_screen = "/com/cm/cm/fxml/admin/delete_by_name.fxml";
    String about_screen = "/com/cm/cm/fxml/misc/about.fxml";
    String kids_records = "/com/cm/cm/fxml/user/kids_records_view.fxml";

    public static void switchScene(Scene scene, boolean truth, String title, boolean option) {
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

        // stage.show();

    }
    public void decision_scene() {
        try {
            FXMLLoader loader = new FXMLLoader();
            Parent root = loader.load(getClass().getResource(decision_screen));
            Scene login = new Scene(root);
            switchScene(login, true, "Select Login Type", false);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public void admin_login_scene() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(admin_login));
            Scene login = new Scene(root);
            switchScene(login, true, "Admin Login", false);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void cashier_login_scene() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(cashier_login));
            Scene login = new Scene(root);
            switchScene(login, true, "Employee Login", false);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public void admin_rec_scene() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(admin_records));
            Scene records = new Scene(root);
            switchScene(records, true, "View Inventory ", true);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void cashier_rec_scene() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(cashier_records));
            Scene records = new Scene(root);
            switchScene(records, true, "View Inventory ", true);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public void add_scene() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(add_screen));
            Scene add = new Scene(root);
            switchScene(add, true, "Add Record", false);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void bulk_delete_scene() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(bulk_delete_screen));
            Scene bulk_delete = new Scene(root);
            switchScene(bulk_delete, true, "Delete Records By Name", false);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void about_scene() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(about_screen));
            Scene about = new Scene(root);
            switchScene(about, true, "About Screen", false);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void kids_records_scene() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(kids_records));
            Scene kids_rec = new Scene(root);
            switchScene(kids_rec, true, "Kids Records", true);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
