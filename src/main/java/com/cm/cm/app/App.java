package com.cm.cm.app;

import com.cm.cm.controllers.misc.SceneCtrl;
import com.cm.cm.database.SqliteSetup;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import com.dansoftware.pdfdisplayer.PDFDisplayer;

import java.io.IOException;
import java.sql.SQLException;

public class App extends Application {
    SceneCtrl scene_switcher = new SceneCtrl();
    Stage stage = new Stage();

    @Override
    public void start(Stage stage) throws Exception {
        scene_switcher.decision_scene();
    }

    public static void main(String[] args) throws SQLException {
        SqliteSetup.main(args);
        launch(args);
    }

    public static void hideWindow(Stage stage) throws Exception {
        stage.getScene().getWindow().hide();
    }

    public void manual_pdf(){
        PDFDisplayer pdf_ = new PDFDisplayer();
        try {
            pdf_.loadPDF(getClass().getResource("/resources/pdf/sample.pdf"));
            Scene pdf_manual = new Scene(pdf_.toNode());
            stage.setScene(pdf_manual);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void closeApp() {
        Platform.exit();
        System.exit(0);
    }

}

