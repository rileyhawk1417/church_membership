package com.cm.cm.controllers.misc;

import javafx.scene.layout.GridPane;
import io.github.palexdev.materialfx.controls.MFXButton;
//import com.cm.cm.updater.delegates.StartupView;
import com.cm.cm.controllers.misc.SceneCtrl;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.update4j.LaunchContext;
import org.update4j.SingleInstanceManager;
import org.update4j.inject.InjectSource;
import org.update4j.inject.InjectTarget;
import org.update4j.inject.PostInject;
import org.update4j.service.Launcher;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class UpdateWindow implements Launcher{
    private static final Path LOCK_DIR = Paths.get(System.getProperty("user.home"), ".cm");
    SceneCtrl scene_switcher = new SceneCtrl();

    @Override
    public long version(){
        return 0;
    }

    @InjectTarget
    private CheckBox singleInstanceCheckBox;

    @InjectTarget
    private TextField singleInstanceMessage;

    @InjectTarget
    private CheckBox newWindowCheckBox;

    @InjectTarget
    private Stage primaryStage;

//    private StartupView startup;

//    @PostInject
//    private void getStartupView(StartupView view){
//        startup = view;
//    }

    //    private LoadingView loading;
    private static Stage stage;

    @Override
    public void run(LaunchContext ctx){
        if(singleInstanceCheckBox.isSelected()){
            try{
                Files.createDirectories(LOCK_DIR);
            } catch(IOException e){
                e.printStackTrace();
            }

            SingleInstanceManager.execute(List.of(singleInstanceMessage.getText()), args -> {
                ButtonType dismiss = new ButtonType("Dismiss", ButtonBar.ButtonData.OK_DONE);
//                Platform.runLater(() -> showDialog("Launcher Message", args.get(0), dismiss)); //Show Dialog is similar to AlertModule cheat.
            }, LOCK_DIR);

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    Thread.sleep(5000);
                    if(isEmptyDirectory(LOCK_DIR)){
                        Files.deleteIfExists(LOCK_DIR);
                    }
                } catch(Exception e){
                    e.printStackTrace();
                }
            }));
        }

        Platform.runLater(() -> {
            scene_switcher.admin_add_rec();
        });
    }

    private static boolean isEmptyDirectory(Path path) throws IOException{
        if(Files.isDirectory(path)){
            try(DirectoryStream<Path> dir = Files.newDirectoryStream(path)){
                return !dir.iterator().hasNext();
            }
        }
        return false;
    }
}
