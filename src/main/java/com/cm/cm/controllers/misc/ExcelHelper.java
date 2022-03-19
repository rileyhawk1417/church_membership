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


    public void exportToExcelAdults(Window owner, Pane ownerPane){
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
            while(res.next()){

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
            //TODO Set initial filename not working when saving?

            Path src = Paths.get(savePath);
            Path dest = Paths.get(saveFile.getAbsolutePath());

            StandardCopyOption REPLACE_EXISTING = StandardCopyOption.REPLACE_EXISTING;
            StandardCopyOption COPY_ATTRIBUTES = StandardCopyOption.COPY_ATTRIBUTES;
            LinkOption NOFOLLOW_LINKS = LinkOption.NOFOLLOW_LINKS;

            if(saveFile !=null){
                try {
                    //try to save workbook
                    wb.write(save_file);
                    try {
                        //try to copy and delete file
                        Files.copy(src, dest, REPLACE_EXISTING, COPY_ATTRIBUTES, NOFOLLOW_LINKS);
                        Files.delete(src);
                    } catch (IOException e){
                        diag.showMFXAlert(owner, "Export Failed", "Failed to export database", AlertModule.dialogType.ERR, ownerPane ); //Recheck with IDE
                        e.printStackTrace();
                    }
                } catch(IOException e){
                    diag.showMFXAlert(owner, "Export Failed", "Failed to write to file", AlertModule.dialogType.ERR, ownerPane ); //Recheck with IDE
                    e.printStackTrace();

                }
            }
            save_file.close();
            res.close();
            wb.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        catch (IOException ex){
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
         //TODO Set initial filename not working when saving?

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
                     diag.showMFXAlert(owner, "Export Failed", "Failed to export database", AlertModule.dialogType.ERR, ownerPane ); //Recheck with IDE

                     e.printStackTrace();
                 }
             } catch (IOException e) {
                 diag.showMFXAlert(owner, "Export Failed", "Failed to export database", AlertModule.dialogType.ERR, ownerPane ); //Recheck with IDE
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
            String query = "INSERT INTO kids_members (title, fname, lname, gender, id_no, kids_num, maritial_status, date_joined, dob, address, surbub, landline, work_num, cell_num, employer, position, email, home_group_leader, dept_leader, dept, salvation, water_bapt, spirit_bapt)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";

            Connection conn = Sqlite.connector();
            PreparedStatement statement = conn.prepareStatement(query);
//            ResultSet res = conn.createStatement().executeQuery(query);

            //TODO: Use file chooser to get absolute path and load custom File
            /*
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
            //TODO Set initial filename not working when saving?

            Path src = Paths.get(savePath);
            Path dest = Paths.get(saveFile.getAbsolutePath());

            StandardCopyOption REPLACE_EXISTING = StandardCopyOption.REPLACE_EXISTING;
            StandardCopyOption COPY_ATTRIBUTES = StandardCopyOption.COPY_ATTRIBUTES;
            LinkOption NOFOLLOW_LINKS = LinkOption.NOFOLLOW_LINKS;

            if(saveFile !=null){
                try {
                    //try to save workbook
                    wb.write(save_file);
                    try {
                        //try to copy and delete file
                        Files.copy(src, dest, REPLACE_EXISTING, COPY_ATTRIBUTES, NOFOLLOW_LINKS);
                        Files.delete(src);
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                } catch(IOException e){
                    e.printStackTrace();

                }
            }
            save_file.close();
            res.close();
            wb.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        catch (IOException ex){
            ex.printStackTrace();
        }

            */

            //Reference Code
/*
            try {
            int batchSize = 20;
            Connection connection = null;

            long start = System.currentTimeMillis();
            FileInputStream inputStream = new FileInputStream(filePath);
            Workbook workbook = new XSSFWorkbook(inputStream);

            sheet firstSheet = workbook.getSheetAt(0);
            Itertator<Row> rowIterator = firstSheet.iterator();
            
            connection = DriverManager.getConnection(jdbcURL, username, password);
            connection.setAutoCommit(false);
            
String sql = "INSERT INTO students (name, enrolled, progress) VALUES (?, ?, ?)";
            PreparedStatement = statement = connection.prepareStatement(sql);
            int count = 0;
            rowIterator.next(); //skip header row
            while (rowIterator.hasNext()){
            Row nextRow = rowIterator.next();
            Iterator<Cell> cellIterator = nextRow.cellIterator();
            while (cellIterator.hasNext()){
            Cell nextCell = cellIterator.next();
            int columnIndex = nextCell.getColumnIndex();

            switch (columnIndex){
                case 0:
                    String name = nextCell.getStringCellValue();
                    statement.setString(1, name);
                    break;
                case 1:
                    Date enrollDate = nextCell.getStringCellValue();
                    statement.setString (1, new Timestamp(enrollDate.getTime()));
                case 2:
                    int progress = (int) nextCell.getNumericCellValue();
                    statement.setInt(3, progress);
                }            
            }    
            statement.addBatch();

            if (count % batchSize == 0){
                statement.executeBatch();            
            }
        }
        worbook.close();
        statement.executeBatch(); //Execute remaining batch tasks
        connection.commit();
        connection.close();
}
*/

            //TODO: Fix input stream
            FileInputStream fileIn = new FileInputStream(new File("sample.xlsx"));

            long start = System.currentTimeMillis();
            Workbook workbook = new XSSFWorkbook(fileIn);

            Sheet getSheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = getSheet.iterator();
            conn.setAutoCommit(false);
            int count = 0;
            rowIterator.next();
            while(rowIterator.hasNext()){
                Row nextRow = rowIterator.next();
                Iterator<Cell> cellIterator = nextRow.cellIterator();
                while(cellIterator.hasNext()){
                    Cell nextCell = cellIterator.next();
                    int columnIndex = nextCell.getColumnIndex();

                    switch(columnIndex){
                        case 0:
                            String fname = nextCell.getStringCellValue();
                            statement.setString(1, fname);
                            break;
                        case 1:
                            String lname = nextCell.getStringCellValue();
                            statement.setString(2, lname);
                        case 2:
                            String gender = nextCell.getStringCellValue();
                            statement.setString(3, gender);
                        case 3:
                            String id_ = nextCell.getStringCellValue();
                            statement.setString(4, id_);
                        case 4:
                            String no_kids = nextCell.getStringCellValue();
                            statement.setString(5, no_kids);
                        case 5:
                            String mStatus = nextCell.getStringCellValue();
                            statement.setString(6, mStatus);
                        case 6:
                            String dateJoined = nextCell.getStringCellValue();
                            statement.setString(7, dateJoined);
                        case 7:
                            String dob_ = nextCell.getStringCellValue();
                            statement.setString(8, dob_);
                        case 8:
                            String address_ = nextCell.getStringCellValue();
                            statement.setString(9, address_);
                        case 9:
                            String surbub_ = nextCell.getStringCellValue();
                            statement.setString(10, surbub_);
                        case 10:
                            String cell_ = nextCell.getStringCellValue();
                            statement.setString(11, cell_);
                        case 11:
                            String employer_ = nextCell.getStringCellValue();
                            statement.setString(12, employer_);
                        case 12:
                            String email_ = nextCell.getStringCellValue();
                            statement.setString(13, email_);
                        case 13:
                            String cell_leader = nextCell.getStringCellValue();
                            statement.setString(14, cell_leader);
                        case 14:
                            String depLeader = nextCell.getStringCellValue();
                            statement.setString(15, depLeader);
                        case 15:
                            String dept = nextCell.getStringCellValue();
                            statement.setString(16, dept);
                        case 16:
                            String salvation_ = nextCell.getStringCellValue();
                            statement.setString(17, salvation_);


                    }
                }
            }

            diag.showMFXAlert(owner, "Sucess", "Imported Database", AlertModule.dialogType.INFO, ownerPane);

            } catch (FileNotFoundException ex) {
               //TODO: Fix loggers

               diag.showMFXAlert(owner, "Import Failed", "Failed to import database", AlertModule.dialogType.ERR, ownerPane ); //Recheck with IDE
               Logger.getLogger(ExcelHelper.class.getName()).log(Level.SEVERE, null, ex);
           } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
    }


    public void importToDBAdults (Window owner, Pane ownerPane) {
        try {

            String query = "INSERT INTO kids_members (title, fname, lname, gender, id_no, kids_num, maritial_status, date_joined, dob, address, surbub, landline, work_num, cell_num, employer, position, email, home_group_leader, dept_leader, dept, salvation, water_bapt, spirit_bapt)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";

            Connection conn = Sqlite.connector();
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet res = conn.createStatement().executeQuery(query);

            //TODO: Fix input stream
            FileInputStream fileIn = new FileInputStream(new File("sample.xlsx"));

            XSSFWorkbook wb = new XSSFWorkbook(fileIn);
            XSSFSheet sheet = wb.getSheetAt(0);
            XSSFRow row;
            for(int i=1; i<=sheet.getLastRowNum(); i++){
                row = sheet.getRow(i);
                pst.setInt(1, (int) row.getCell(0).getNumericCellValue());
                pst.setString(2, row.getCell(1).getStringCellValue());
                pst.setString(3, row.getCell(2).getStringCellValue());
                pst.setString(4, row.getCell(3).getStringCellValue());
                pst.setString(5, row.getCell(4).getStringCellValue());
                pst.setString(6, row.getCell(5).getStringCellValue());
                pst.setString(7, row.getCell(6).getStringCellValue());
                pst.setString(8, row.getCell(7).getStringCellValue());
                pst.setString(9, row.getCell(8).getStringCellValue());
                pst.setString(10, row.getCell(9).getStringCellValue());
                pst.setString(11, row.getCell(10).getStringCellValue());
                pst.setString(12, row.getCell(11).getStringCellValue());
                pst.setString(13, row.getCell(12).getStringCellValue());
                pst.setString(14, row.getCell(13).getStringCellValue());
                pst.setString(15, row.getCell(14).getStringCellValue());
                pst.setString(16, row.getCell(15).getStringCellValue());
                pst.setString(17, row.getCell(16).getStringCellValue());
                pst.setString(18, row.getCell(17).getStringCellValue());
                pst.setString(19, row.getCell(18).getStringCellValue());
                pst.setString(20, row.getCell(19).getStringCellValue());
                pst.setString(21, row.getCell(20).getStringCellValue());
                pst.setString(22, row.getCell(21).getStringCellValue());
                pst.setString(23, row.getCell(22).getStringCellValue());
                pst.setString(24, row.getCell(23).getStringCellValue());
                pst.setString(25, row.getCell(24).getStringCellValue());
                pst.execute();
            }
            diag.showMFXAlert(owner, "Sucess", "Imported Database", AlertModule.dialogType.INFO, ownerPane);
            wb.close();
            fileIn.close();
            pst.close();
            res.close();
        } catch (SQLException | FileNotFoundException ex) {
            //TODO: Fix loggers
            diag.showMFXAlert(owner, "Import Failed", "Failed to import database", AlertModule.dialogType.ERR, ownerPane ); //Recheck with IDE
            Logger.getLogger(ExcelHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            diag.showMFXAlert(owner, "Import Failed", "Failed to read from file", AlertModule.dialogType.ERR, ownerPane ); //Recheck with IDE
            Logger.getLogger(ExcelHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void exportToExcel(Window owner) {
    }
}

