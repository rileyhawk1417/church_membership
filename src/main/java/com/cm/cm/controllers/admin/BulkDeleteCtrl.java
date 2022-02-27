package com.cm.cm.controllers.admin;

import com.cm.cm.modals.AlertModule;
import com.cm.cm.database.Psql;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
// import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.stage.Window;

public class BulkDeleteCtrl {
    Psql psql = new Psql();

    @FXML
    private Button cancel_;

    @FXML
    private Button bulk_delete;

    @FXML
    private TextField search_;

    Window owner;
    @FXML
    private void cancel_delete(){
        owner = search_.getScene().getWindow();
        owner.hide();
    }


    @FXML
    private void confirm_delete(){
        owner = search_.getScene().getWindow();
        // boolean result =
        try {
            boolean result = confirm();
            if(result){
                //Only run if records got deleted
                System.out.println("Records deleted");
                AlertModule.showAlert(Alert.AlertType.INFORMATION, owner, "Records Deleted", "Records were successfully deleted");
                owner.hide();
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private boolean confirm() throws Exception{
        if (search_.getText().isEmpty()){
            //dont run
            AlertModule.showAlert(Alert.AlertType.ERROR, owner, "Empty Fields", "No empty fields allowed!");
            return false;
        } else {
            psql.delete_row_by_name(search_.getText());
            return true;
        }
    }
}
