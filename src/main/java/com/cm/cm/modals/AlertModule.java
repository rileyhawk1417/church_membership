package com.cm.cm.modals;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.dialogs.MFXGenericDialog;
import io.github.palexdev.materialfx.dialogs.MFXGenericDialogBuilder;
import io.github.palexdev.materialfx.font.MFXFontIcon;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.stage.Window;
import io.github.palexdev.materialfx.dialogs.MFXStageDialog;
import io.github.palexdev.materialfx.dialogs.MFXStageDialogBuilder;
import com.cm.cm.database.Sqlite;

import java.util.Map;

/*
 * *This module is responsible for pop up messages
 *
 */
public class AlertModule {
    Sqlite sqlite = new Sqlite();
     MFXGenericDialog genericDialog = new MFXGenericDialog();
    MFXStageDialog dialog = new MFXStageDialog();
    MFXStageDialogBuilder dialogBuilder = new MFXStageDialogBuilder();

    MFXFontIcon errorIcon = new MFXFontIcon("mfx-info-circle-filled", 18);
    MFXFontIcon warnIcon = new MFXFontIcon("mfx-do-not-enter-circle", 18);
    MFXFontIcon infoIcon = new MFXFontIcon("mfx-exclamation-circle-filled", 18);
    MFXFontIcon empty_;

    String deleteUserMsg = "Do you want to delete user? ";
    String deleteUserTitle = "Delete User ?";

    String deleteRecTitle = "Delete Record ?";
    String deleteRecMsg = "Do you want to delete this record? ";


    public enum dialogType {
        WARN,
        INFO,
        ERR
    }
    String styleValue ="";

    public void showMFXAlert(Window win, String msgTitle, String msgBody, Enum<dialogType>style_, Pane ownerPane){
        this.genericDialog = MFXGenericDialogBuilder.build()
        .setHeaderText(msgTitle)
        .setContentText(msgBody)
                .get();
        genericDialog.addActions(
                Map.entry(new MFXButton("Close"), event -> dialog.close())
        );
        genericDialog.setMaxSize(400, 200);

        this.dialog = MFXGenericDialogBuilder.build(genericDialog)
                .toStageDialogBuilder()
        .initOwner(win)
        .setTitle(msgTitle)
        .setOwnerNode(ownerPane)
                .get();
        styleDialogConvertor(style_);
        iconDialogConvertor(style_);

        genericDialog.setHeaderText(msgTitle);
        genericDialog.setMaxSize(400, 200);
        dialog.showDialog();
    }

    public void deleteRecDialog(Window win, Enum<dialogType>style_, Pane ownerPane, String id, String choice){
        this.genericDialog = MFXGenericDialogBuilder.build()
                .setHeaderText(deleteRecTitle)
                .setContentText(deleteRecMsg)
                .get();
        genericDialog.addActions(
                Map.entry(new MFXButton("Confirm"), event -> {
                    switch (choice){
                        case "members" -> {
                            try {
                                sqlite.delete_row_by_id(id);
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                                showMFXAlert(win, "Error", "Failed to delete record", dialogType.ERR, ownerPane);
                                e.printStackTrace();
                            }
                            dialog.close();
                        }

                        case "kids" -> {
                            try {
                                sqlite.delete_row_by_id_kids(id);
                            } catch(Exception e){
                                System.out.println(e.getMessage());
                                showMFXAlert(win, "Error", "Failed to delete record", dialogType.ERR, ownerPane);
                            }
                            dialog.close();
                        }

                        default -> throw new IllegalStateException("Unexpected value: " + choice);
                    }
                }),
                Map.entry(new MFXButton("Cancel"), event -> dialog.close())
        );
        genericDialog.setMaxSize(400, 200);

        this.dialog = MFXGenericDialogBuilder.build(genericDialog)
                .toStageDialogBuilder()
                .initOwner(win)
                .setTitle(deleteRecTitle)
                .setOwnerNode(ownerPane)
                .get();
        styleDialogConvertor(style_);
        iconDialogConvertor(style_);

        genericDialog.setHeaderText(deleteRecTitle);
        genericDialog.setMaxSize(400, 200);
        dialog.showDialog();

    }

    public void deleteUserDialog(Window win, Enum<dialogType>style_, Pane ownerPane, String id, String choice){
        this.genericDialog = MFXGenericDialogBuilder.build()
                .setHeaderText(deleteUserTitle)
                .setContentText(deleteUserMsg)
                .get();
        genericDialog.addActions(
                Map.entry(new MFXButton("Confirm"), event -> {
                        switch (choice){
                            case "admin", "user" -> {
                                try {
                                    sqlite.deleteUser(id, choice);
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                    e.printStackTrace();
                                    showMFXAlert(win, "Error", "Failed to delete user", dialogType.ERR, ownerPane);

                                }
                                dialog.close();
                            }

                            default -> throw new IllegalStateException("Unexpected value: " + choice);
                        }
                }),
                Map.entry(new MFXButton("Cancel"), event -> dialog.close())
        );
        genericDialog.setMaxSize(400, 200);

        this.dialog = MFXGenericDialogBuilder.build(genericDialog)
                .toStageDialogBuilder()
                .initOwner(win)
                .setTitle(deleteUserTitle)
                .setOwnerNode(ownerPane)
                .get();
        styleDialogConvertor(style_);
        iconDialogConvertor(style_);

        genericDialog.setHeaderText(deleteUserTitle);
        genericDialog.setMaxSize(400, 200);
        dialog.showDialog();

    }


    private void iconDialogConvertor(Enum<dialogType> style){
        if (dialogType.WARN.equals(style)) {
            genericDialog.setHeaderIcon(warnIcon);
        } else if (dialogType.INFO.equals(style)) {
            genericDialog.setHeaderIcon(infoIcon);
        } else if (dialogType.ERR.equals(style)) {
            genericDialog.setHeaderIcon(errorIcon);
        }
        else {
            throw new IllegalStateException("Unexpected value: " + style);
        }
    }

    private void styleDialogConvertor(Enum<dialogType> style){

        if (dialogType.WARN.equals(style)) {
            genericDialog.getStyleClass().removeIf(s -> s.equals("mfx-warn-dialog"));
            styleValue = "mfx-warn-dialog";
        } else if (dialogType.INFO.equals(style)) {
            genericDialog.getStyleClass().removeIf(s -> s.equals("mfx-info-dialog"));
            styleValue = "mfx-info-dialog";
        } else if (dialogType.ERR.equals(style)) {
            genericDialog.getStyleClass().removeIf(s -> s.equals("mfx-error-dialog"));
            styleValue = "mfx-error-dialog";
        } else if(style != null){
            genericDialog.getStyleClass().add(styleValue);
        }else {
            throw new IllegalStateException("Unexpected value: " + style);
        }
    }
}
