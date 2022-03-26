package com.cm.cm.controllers.misc;

import com.cm.cm.database.Sqlite;
import com.cm.cm.modals.AlertModule;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.*;
import java.nio.file.*;
import java.sql.*;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 *This file handles the export from database to excel
 * This can also be seen as a backup function.
 * Reconfigure to work with present system.
 */

public class ExcelHelper {

    Stage stage = new Stage();
    AlertModule diag = new AlertModule();


    public void exportToExcelAdults(Window owner, Pane ownerPane) {
        try {
//Fix function to export to excel whether kids table or adults table
            String query = "SELECT * FROM members";

            Connection conn = Sqlite.connector();
            ResultSet res = conn.createStatement().executeQuery(query);

            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet worksheet = wb.createSheet("Exported psqlTable");
            XSSFRow header = worksheet.createRow(0);

            header.createCell(0).setCellValue("ID");
            header.createCell(1).setCellValue("Title");
            header.createCell(2).setCellValue("Name");
            header.createCell(3).setCellValue("Surname");
            header.createCell(4).setCellValue("Gender");
            header.createCell(5).setCellValue("ID No.");
            header.createCell(6).setCellValue("Children No.");
            header.createCell(7).setCellValue("Maritial Status");
            header.createCell(8).setCellValue("Date Joined");
            header.createCell(9).setCellValue("D.O.B");
            header.createCell(10).setCellValue("address");
            header.createCell(11).setCellValue("Surbub");
            header.createCell(12).setCellValue("Home Phone");
            header.createCell(13).setCellValue("Work Phone");
            header.createCell(14).setCellValue("Mobile Phone");
            header.createCell(15).setCellValue("Employer");
            header.createCell(16).setCellValue("Position");
            header.createCell(17).setCellValue("Email");
            header.createCell(18).setCellValue("Home Group Leader");
            header.createCell(19).setCellValue("Department Leader");
            header.createCell(20).setCellValue("Department");
            header.createCell(21).setCellValue("Salvation");
            header.createCell(22).setCellValue("Water Baptism");
            header.createCell(23).setCellValue("Spirit Baptism");

            worksheet.autoSizeColumn(0);
            worksheet.autoSizeColumn(1);
            worksheet.autoSizeColumn(2);
            worksheet.autoSizeColumn(3);
            worksheet.autoSizeColumn(4);
            worksheet.autoSizeColumn(5);
            worksheet.autoSizeColumn(6);
            worksheet.autoSizeColumn(7);
            worksheet.autoSizeColumn(8);
            worksheet.autoSizeColumn(9);
            worksheet.autoSizeColumn(10);
            worksheet.autoSizeColumn(11);
            worksheet.autoSizeColumn(12);
            worksheet.autoSizeColumn(13);
            worksheet.autoSizeColumn(14);
            worksheet.autoSizeColumn(15);
            worksheet.autoSizeColumn(16);
            worksheet.autoSizeColumn(17);
            worksheet.autoSizeColumn(18);
            worksheet.autoSizeColumn(19);
            worksheet.autoSizeColumn(20);
            worksheet.autoSizeColumn(21);
            worksheet.autoSizeColumn(22);
            worksheet.autoSizeColumn(23);

            worksheet.setZoom(150); //scale of zoom 150%

            int index = 1;
            while (res.next()) {

                XSSFRow row = worksheet.createRow(index);
                row.createCell(0).setCellValue(res.getString("id"));
                row.createCell(1).setCellValue(res.getString("title"));
                row.createCell(2).setCellValue(res.getString("fname"));
                row.createCell(3).setCellValue(res.getString("lname"));
                row.createCell(4).setCellValue(res.getString("gender"));
                row.createCell(5).setCellValue(res.getString("id_no"));
                row.createCell(6).setCellValue(res.getString("children_num"));
                row.createCell(7).setCellValue(res.getString("maritial_status"));
                row.createCell(8).setCellValue(res.getString("date_joined"));
                row.createCell(9).setCellValue(res.getString("dob"));
                row.createCell(10).setCellValue(res.getString("address"));
                row.createCell(11).setCellValue(res.getString("surbub"));
                row.createCell(12).setCellValue(res.getString("home_phone"));
                row.createCell(13).setCellValue(res.getString("work_phone"));
                row.createCell(14).setCellValue(res.getString("mobile_phone"));
                row.createCell(15).setCellValue(res.getString("employer"));
                row.createCell(16).setCellValue(res.getString("position"));
                row.createCell(17).setCellValue(res.getString("email"));
                row.createCell(18).setCellValue(res.getString("home_group_leader"));
                row.createCell(19).setCellValue(res.getString("department_leader"));
                row.createCell(20).setCellValue(res.getString("department"));
                row.createCell(21).setCellValue(res.getString("salvation"));
                row.createCell(22).setCellValue(res.getString("water_baptism"));
                row.createCell(23).setCellValue(res.getString("spirit_baptism"));
                index++;
            }

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Export Excel Document");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("XLSX files (*.xlsx)", "*.xlsx"),
                    new FileChooser.ExtensionFilter("XLS files (*.xls)", "*.xls"),
                    new FileChooser.ExtensionFilter("ODS files (*.ods)", "*.ods"),
                    new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv"),
                    new FileChooser.ExtensionFilter("HTML files (*.html)", "*.html")
            );
            File saveFile = fileChooser.showSaveDialog(owner);
            String savePath = "Exported Database.xlsx";
            FileOutputStream save_file = new FileOutputStream(savePath);
            fileChooser.setInitialFileName("Exported Excel Database");

            Path src = Paths.get(savePath);
            Path dest = Paths.get(saveFile.getAbsolutePath());

            StandardCopyOption REPLACE_EXISTING = StandardCopyOption.REPLACE_EXISTING;
            StandardCopyOption COPY_ATTRIBUTES = StandardCopyOption.COPY_ATTRIBUTES;
            LinkOption NOFOLLOW_LINKS = LinkOption.NOFOLLOW_LINKS;

            if (saveFile != null) {
                try {
                    //try to save workbook
                    wb.write(save_file);
                    try {
                        //try to copy and delete file
                        Files.copy(src, dest, REPLACE_EXISTING, COPY_ATTRIBUTES, NOFOLLOW_LINKS);
                        Files.delete(src);
                    } catch (IOException e) {
                        diag.showMFXAlert(owner, "Export Failed", "Failed to export database", AlertModule.dialogType.ERR, ownerPane); //Recheck with IDE
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    diag.showMFXAlert(owner, "Export Failed", "Failed to write to file", AlertModule.dialogType.ERR, ownerPane); //Recheck with IDE
                    e.printStackTrace();

                }
            }
            save_file.close();
            res.close();
            wb.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void exportToExcelKids(Window owner, Pane ownerPane) {
        try {
//Fix function to export to excel whether kids table or adults table
            String query = "SELECT * FROM kids_members";

            Connection conn = Sqlite.connector();
            ResultSet res = conn.createStatement().executeQuery(query);

            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet worksheet = wb.createSheet("Exported psqlTable");
            XSSFRow header = worksheet.createRow(0);

            header.createCell(0).setCellValue("ID");
            header.createCell(1).setCellValue("Title");
            header.createCell(2).setCellValue("Name");
            header.createCell(3).setCellValue("Surname");
            header.createCell(4).setCellValue("Gender");
            header.createCell(5).setCellValue("ID No.");
            header.createCell(6).setCellValue("Children No.");
            header.createCell(7).setCellValue("Maritial Status");
            header.createCell(8).setCellValue("Date Joined");
            header.createCell(9).setCellValue("D.O.B");
            header.createCell(10).setCellValue("address");
            header.createCell(11).setCellValue("Surbub");
            header.createCell(12).setCellValue("Home Phone");
            header.createCell(13).setCellValue("Work Phone");
            header.createCell(14).setCellValue("Mobile Phone");
            header.createCell(15).setCellValue("Employer");
            header.createCell(16).setCellValue("Position");
            header.createCell(17).setCellValue("Email");
            header.createCell(18).setCellValue("Home Group Leader");
            header.createCell(19).setCellValue("Department Leader");
            header.createCell(20).setCellValue("Department");
            header.createCell(21).setCellValue("Salvation");
            header.createCell(22).setCellValue("Water Baptism");
            header.createCell(23).setCellValue("Spirit Baptism");

            worksheet.autoSizeColumn(0);
            worksheet.autoSizeColumn(1);
            worksheet.autoSizeColumn(2);
            worksheet.autoSizeColumn(3);
            worksheet.autoSizeColumn(4);
            worksheet.autoSizeColumn(5);
            worksheet.autoSizeColumn(6);
            worksheet.autoSizeColumn(7);
            worksheet.autoSizeColumn(8);
            worksheet.autoSizeColumn(9);
            worksheet.autoSizeColumn(10);
            worksheet.autoSizeColumn(11);
            worksheet.autoSizeColumn(12);
            worksheet.autoSizeColumn(13);
            worksheet.autoSizeColumn(14);
            worksheet.autoSizeColumn(15);
            worksheet.autoSizeColumn(16);
            worksheet.autoSizeColumn(17);
            worksheet.autoSizeColumn(18);
            worksheet.autoSizeColumn(19);
            worksheet.autoSizeColumn(20);
            worksheet.autoSizeColumn(21);
            worksheet.autoSizeColumn(22);
            worksheet.autoSizeColumn(23);

            worksheet.setZoom(150); //scale of zoom 150%

            int index = 1;
            while (res.next()) {

                XSSFRow row = worksheet.createRow(index);
                row.createCell(0).setCellValue(res.getString("id"));
                row.createCell(1).setCellValue(res.getString("title"));
                row.createCell(2).setCellValue(res.getString("fname"));
                row.createCell(3).setCellValue(res.getString("lname"));
                row.createCell(4).setCellValue(res.getString("gender"));
                row.createCell(5).setCellValue(res.getString("id_no"));
                row.createCell(6).setCellValue(res.getString("children_num"));
                row.createCell(7).setCellValue(res.getString("maritial_status"));
                row.createCell(8).setCellValue(res.getString("date_joined"));
                row.createCell(9).setCellValue(res.getString("dob"));
                row.createCell(10).setCellValue(res.getString("address"));
                row.createCell(11).setCellValue(res.getString("surbub"));
                row.createCell(12).setCellValue(res.getString("home_phone"));
                row.createCell(13).setCellValue(res.getString("work_phone"));
                row.createCell(14).setCellValue(res.getString("mobile_phone"));
                row.createCell(15).setCellValue(res.getString("employer"));
                row.createCell(16).setCellValue(res.getString("position"));
                row.createCell(17).setCellValue(res.getString("email"));
                row.createCell(18).setCellValue(res.getString("home_group_leader"));
                row.createCell(19).setCellValue(res.getString("department_leader"));
                row.createCell(20).setCellValue(res.getString("department"));
                row.createCell(21).setCellValue(res.getString("salvation"));
                row.createCell(22).setCellValue(res.getString("water_baptism"));
                row.createCell(23).setCellValue(res.getString("spirit_baptism"));
                index++;
            }

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Export Excel Document");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("XLSX files (*.xlsx)", "*.xlsx"),
                    new FileChooser.ExtensionFilter("XLS files (*.xls)", "*.xls"),
                    new FileChooser.ExtensionFilter("ODS files (*.ods)", "*.ods"),
                    new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv"),
                    new FileChooser.ExtensionFilter("HTML files (*.html)", "*.html")
            );
            File saveFile = fileChooser.showSaveDialog(owner);
            String savePath = "Exported Database.xlsx";
            FileOutputStream save_file = new FileOutputStream(savePath);
            fileChooser.setInitialFileName("Exported Excel Database");

            Path src = Paths.get(savePath);
            Path dest = Paths.get(saveFile.getAbsolutePath());

            StandardCopyOption REPLACE_EXISTING = StandardCopyOption.REPLACE_EXISTING;
            StandardCopyOption COPY_ATTRIBUTES = StandardCopyOption.COPY_ATTRIBUTES;
            LinkOption NOFOLLOW_LINKS = LinkOption.NOFOLLOW_LINKS;

            if (saveFile != null) {
                try {
                    //try to save workbook
                    wb.write(save_file);
                    try {
                        //try to copy and delete file
                        Files.copy(src, dest, REPLACE_EXISTING, COPY_ATTRIBUTES, NOFOLLOW_LINKS);
                        Files.delete(src);
                    } catch (IOException e) {
                        diag.showMFXAlert(owner, "Export Failed", "Failed to export database", AlertModule.dialogType.ERR, ownerPane); //Recheck with IDE

                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    diag.showMFXAlert(owner, "Export Failed", "Failed to export database", AlertModule.dialogType.ERR, ownerPane); //Recheck with IDE
                    e.printStackTrace();

                }
            }
            save_file.close();
            res.close();
            wb.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
//        }
        }
    }

    public void importToDBKids(Window owner, Pane ownerPane) {
        try {
            String query = "INSERT OR IGNORE INTO kids_members (fname, lname, gender, id_no, kids_num, maritial_status, dob, address, cell_num,dept)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";

            Connection conn = Sqlite.connector();
            PreparedStatement statement = conn.prepareStatement(query);

            FileChooser filePicker = new FileChooser();
            File file = filePicker.showOpenDialog(stage);
            String excelFile = file.getAbsolutePath();


            FileInputStream fileIn = new FileInputStream(new File(excelFile));

            long start = System.currentTimeMillis();
            Workbook workbook = new XSSFWorkbook(fileIn);

            Sheet getSheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = getSheet.iterator();
            conn.setAutoCommit(false);
            int count = 0;
            int batchSize = 20;
            rowIterator.next();
            while (rowIterator.hasNext()) {
                Row nextRow = rowIterator.next();
                Iterator<Cell> cellIterator = nextRow.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell nextCell = cellIterator.next();
                    int columnIndex = nextCell.getColumnIndex();

                    switch (columnIndex) {
                        case 0 -> {
                            String lname = nextCell.getStringCellValue();
                            statement.setString(1, lname);
                            break;
                        }
                        case 1 -> {
                            String fname = nextCell.getStringCellValue();
                            statement.setString(2, fname);
                        }
                        case 2 -> {
                            String id_ = nextCell.getStringCellValue();
                            statement.setString(3, id_);
                        }
                        case 3 -> {
                            String mStatus = nextCell.getStringCellValue();
                            statement.setString(4, mStatus);
                        }
                        case 4 -> {
                            String gender = nextCell.getStringCellValue();
                            statement.setString(5, gender);
                        }
                        case 5 -> {
                            String dob_ = nextCell.getStringCellValue();
                            statement.setString(6, dob_);
                        }
                        case 6 -> {
                            String address_ = nextCell.getStringCellValue();
                            statement.setString(7, address_);
                        }
                        case 7 -> {
                            String cell_ = nextCell.getStringCellValue();
                            statement.setString(8, cell_);
                        }
                        case 8 -> {
                            String no_kids = nextCell.getStringCellValue();
                            statement.setString(9, no_kids);
                        }
                        case 9 -> {
                            String dep_ = nextCell.getStringCellValue();
                            statement.setString(10, dep_);
                        }

                        default -> throw new IllegalStateException("Unexpected value: " + columnIndex);
                    }
                }
                statement.addBatch();
                if(count % batchSize == 0){
                    statement.executeBatch();
                }
            }
            workbook.close();
            statement.executeBatch();
            conn.commit();
            conn.close();

            diag.showMFXAlert(owner, "Sucess", "Imported Database", AlertModule.dialogType.INFO, ownerPane);
            Logger.getLogger(ExcelHelper.class.getName()).log(Level.SEVERE, (String) null);

        } catch (FileNotFoundException ex) {
            //TODO: Fix loggers

            diag.showMFXAlert(owner, "Import Failed", "Failed to import database", AlertModule.dialogType.ERR, ownerPane); //Recheck with IDE
            Logger.getLogger(ExcelHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
    }


    public void importToDBAdults(Window owner, Pane ownerPane) {
        try {
            String query = "INSERT OR IGNORE INTO members (fname, lname, gender, id_no, kids_num, maritial_status, dob, address, cell_num,dept)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";

            Connection conn = Sqlite.connector();
            PreparedStatement statement = conn.prepareStatement(query);

            FileChooser filePicker = new FileChooser();
            File file = filePicker.showOpenDialog(stage);
            String excelFile = file.getAbsolutePath();


            FileInputStream fileIn = new FileInputStream(new File(excelFile));

            long start = System.currentTimeMillis();
            Workbook workbook = new XSSFWorkbook(fileIn);

            Sheet getSheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = getSheet.iterator();
            conn.setAutoCommit(false);
            int count = 0;
            int batchSize = 20;
            rowIterator.next();
            while (rowIterator.hasNext()) {
                Row nextRow = rowIterator.next();
                Iterator<Cell> cellIterator = nextRow.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell nextCell = cellIterator.next();
                    int columnIndex = nextCell.getColumnIndex();

                    switch (columnIndex) {
                        case 0 -> {
                            String lname = nextCell.getStringCellValue();
                            statement.setString(1, lname);
                            break;
                        }
                        case 1 -> {
                            String fname = nextCell.getStringCellValue();
                            statement.setString(2, fname);
                        }
                        case 2 -> {
                            String id_ = nextCell.getStringCellValue();
                            statement.setString(3, id_);
                        }
                        case 3 -> {
                            String mStatus = nextCell.getStringCellValue();
                            statement.setString(4, mStatus);
                        }
                        case 4 -> {
                            String gender = nextCell.getStringCellValue();
                            statement.setString(5, gender);
                        }
                        case 5 -> {
                            String dob_ = nextCell.getStringCellValue();
                            statement.setString(6, dob_);
                        }
                        case 6 -> {
                            String address_ = nextCell.getStringCellValue();
                            statement.setString(7, address_);
                        }
                        case 7 -> {
                            String cell_ = nextCell.getStringCellValue();
                            statement.setString(8, cell_);
                        }
                        case 8 -> {
                            String no_kids = nextCell.getStringCellValue();
                            statement.setString(9, no_kids);
                        }
                        case 9 -> {
                            String dep_ = nextCell.getStringCellValue();
                            statement.setString(10, dep_);
                        }

                        default -> throw new IllegalStateException("Unexpected value: " + columnIndex);
                    }
                }
                statement.addBatch();
                if(count % batchSize == 0){
                    statement.executeBatch();
                }
            }
            workbook.close();
            statement.executeBatch();
            conn.commit();
            conn.close();


            diag.showMFXAlert(owner, "Sucess", "Imported Database", AlertModule.dialogType.INFO, ownerPane);
            Logger.getLogger(ExcelHelper.class.getName()).log(Level.INFO, (String) "Imported DB");
        } catch (SQLException | FileNotFoundException ex) {
            //TODO: Fix loggers
            diag.showMFXAlert(owner, "Import Failed", "Failed to import database", AlertModule.dialogType.ERR, ownerPane); //Recheck with IDE
            Logger.getLogger(ExcelHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            diag.showMFXAlert(owner, "Import Failed", "Failed to read from file", AlertModule.dialogType.ERR, ownerPane); //Recheck with IDE
            Logger.getLogger(ExcelHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void exportToExcel(Window owner) {
    }
}

