package com.cm.cm.app;

import com.cm.cm.controllers.misc.SceneCtrl;
//import main.java.com.c_m.cm.database.Sqlite;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.sql.SQLException;

public class App extends Application {

    //    public static Stage stage = new Stage();
//    public static Sqlite database = new Sqlite();
    SceneCtrl scene_switcher = new SceneCtrl();

    @Override
    public void start(Stage stage) throws Exception {
        // scene_switcher.rec_scene();
        scene_switcher.decision_scene();
    }

    public static void main(String[] args) throws SQLException {
//        Sqlite.main(args);
        launch(args);
    }

    public static void hideWindow(Stage stage) throws Exception {
        stage.getScene().getWindow().hide();
    }

    public void manual_pdf(){
//        PDFViewer pdf_ = new PDFViewer();
//        try {
//            pdf_.loadPDF(getClass().getResource("/resources/pdf/sample.pdf"));
//            Scene pdf_manual = new Scene(pdf_);
//            stage.setScene(pdf_manual);
//            stage.show();
//        } catch (PDFException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
    }

    public static void closeApp() {
        Platform.exit();
        System.exit(0);
    }

}

