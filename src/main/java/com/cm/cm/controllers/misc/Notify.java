package com.cm.cm.controllers.misc;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXIconWrapper;
import io.github.palexdev.materialfx.controls.MFXNotificationCenter;
import io.github.palexdev.materialfx.controls.MFXSimpleNotification;
import io.github.palexdev.materialfx.controls.cell.MFXNotificationCell;
import io.github.palexdev.materialfx.enums.NotificationPos;
import io.github.palexdev.materialfx.enums.NotificationState;
import io.github.palexdev.materialfx.factories.InsetsFactory;
import io.github.palexdev.materialfx.font.MFXFontIcon;
import io.github.palexdev.materialfx.notifications.MFXNotificationCenterSystem;
import io.github.palexdev.materialfx.notifications.MFXNotificationSystem;
import io.github.palexdev.materialfx.notifications.base.INotification;
import io.github.palexdev.materialfx.utils.RandomUtils;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

import com.cm.cm.controllers.misc.ResourceFinder;

public class Notify {
    public Notify(Stage stage) {
        Platform.runLater(() -> {
            MFXNotificationSystem.instance().initOwner(stage);
            MFXNotificationCenterSystem.instance().initOwner(stage);

            MFXNotificationCenter center = MFXNotificationCenterSystem.instance().getCenter();
            center.setCellFactory(notify -> new MFXNotificationCell(center, notify) {
                {
                    setPrefHeight(400);
                }
            });
        });
    }

    public Notify() {

    }
    //TODO build constructor for simple Notification

    public void notification (String txt){
        MFXNotificationCenterSystem.instance().setPosition(NotificationPos.TOP_CENTER).publish(createNotification(txt));
    }

    private INotification createNotification(String msgBody){
        GenericNotify notice = new GenericNotify();
        notice.setContentText(msgBody);
        return notice;
    }

    private static class GenericNotify extends MFXSimpleNotification {
        private final StringProperty headerText = new SimpleStringProperty();
        private final StringProperty contentText = new SimpleStringProperty();
        public GenericNotify() {
            //TODO Find a way to select icon
            MFXIconWrapper iconWrapper = new MFXIconWrapper(RandomUtils.randFromArray(ResourceFinder.notifyIcons).getDescription(), 16, 32);
            Label header = new Label();
            header.textProperty().bind(headerText);
            MFXIconWrapper readIcon = new MFXIconWrapper("mfx-eye", 16, 32);
            ((MFXFontIcon) readIcon.getIcon()).descriptionProperty().bind(Bindings.createStringBinding(
                    () -> (getState() == NotificationState.READ) ? "mfx-eye" : "mfx-eye-slash", notificationStateProperty()
            ));

            //Maybe migrate most of this to FXML?
            StackPane.setAlignment(readIcon, Pos.CENTER_RIGHT);
            StackPane placeHolder = new StackPane(readIcon);
            placeHolder.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(placeHolder, Priority.ALWAYS);
            HBox headerBox = new HBox(10, iconWrapper, header, placeHolder);
            header.setAlignment(Pos.CENTER_LEFT);
            header.setPadding(InsetsFactory.of(5, 0, 5, 0));
            header.setMaxWidth(Double.MAX_VALUE);

            Label contentLabel = new Label();
            contentLabel.getStyleClass().add("content");
            contentLabel.textProperty().bind(contentText);
            contentLabel.setWrapText(true);
            contentLabel.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            contentLabel.setAlignment(Pos.TOP_LEFT);

            MFXButton action1 = new MFXButton("Action 1");
            MFXButton action2 = new MFXButton("Action 2");
            HBox actionBar = new HBox(15, action1, action2);
            actionBar.getStyleClass().add("actions-bar");
            actionBar.setAlignment(Pos.CENTER_RIGHT);
            actionBar.setPadding(InsetsFactory.all(5));

            BorderPane BP = new BorderPane();
            BP.getStyleClass().add("notification");
            BP.setTop(headerBox);
            BP.setCenter(contentLabel);
            BP.setBottom(actionBar);
            //TODO Add stylesheets
            BP.setMinHeight(200);
            BP.setMaxWidth(400);
            setContent(BP);
        }
        public String getHeaderText(){
            return headerText.get();
        }

        public StringProperty headerTextProperty(){
            return headerText;
        }

        public void setHeaderText(String msgTitle){
            this.headerText.set(msgTitle);
        }
        public String getContentText(){
            return contentText.get();
        }

        public StringProperty contentTextProperty(){
            return contentText;
        }

        public void setContentText(String msgBody){
            this.contentText.set(msgBody);
        }
    }
}
