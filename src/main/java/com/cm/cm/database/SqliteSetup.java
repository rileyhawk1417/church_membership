package com.cm.cm.database;

import java.sql.*;
import java.io.File;


public class SqliteSetup {
    private static void createFolder(){
        File folder = new File("sqlite");

        boolean dir = folder.mkdir();

        try{
            if(dir) {
                System.out.println("Database Directory created");
            }
        } catch(Exception e) {
            System.out.println("Failed to create Database Directory");
            System.out.println(e.getMessage());
        }
    }

    private static void createDB(String fileName) {
        String url = "jdbc:sqlite:sqlite/" + fileName;

        try (Connection conn = DriverManager.getConnection(url)) {
            DatabaseMetaData meta = conn.getMetaData();
            if (conn == null) {

                System.out.println("DB has been created @ " + meta.getURL());
            } else {
                Sqlite.connector();
                System.out.println(meta.getURL());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void setUpDB() {

        String userTable = "CREATE TABLE IF NOT EXISTS user_login " +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "user_name TEXT UNIQUE, " +
                "password TEXT, " +
                "user_type TEXT)";

        String fillUserTable = "INSERT OR IGNORE INTO user_login (user_name, password, user_type) VALUES ('guest', '', 'user'), ('root', 'toor', 'admin')" ;

        String membersTable = "CREATE TABLE IF NOT EXISTS members" +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "title TEXT, fname TEXT, lname TEXT, gender TEXT, " +
                "id_no TEXT, kids_num INTEGER, maritial_status TEXT, " +
                "date_joined TEXT, dob TEXT, address TEXT, surbub TEXT, " +
                "landline INTEGER, work_num INTEGER, cell_num INTEGER, " +
                "employer TEXT, position TEXT, email TEXT, home_group_leader TEXT, " +
                "dept_leader TEXT, dept TEXT, salvation TEXT, water_bapt TEXT, spirit_bapt TEXT)";

        String kidsTable = "CREATE TABLE IF NOT EXISTS kids_members" +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "title TEXT, fname TEXT, lname TEXT, gender TEXT, " +
                "id_no TEXT, kids_num INTEGER, maritial_status TEXT, " +
                "date_joined TEXT, dob TEXT, address TEXT, surbub TEXT, " +
                "landline INTEGER, work_num INTEGER, cell_num INTEGER, " +
                "employer TEXT, position TEXT, email TEXT, home_group_leader TEXT, " +
                "dept_leader TEXT, dept TEXT, salvation TEXT, water_bapt TEXT, spirit_bapt TEXT)";

        String guest_data = "CREATE TABLE IF NOT EXISTS guest_data" +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "title TEXT, fname TEXT, lname TEXT, gender TEXT, " +
                "id_no TEXT, kids_num INTEGER, maritial_status TEXT, " +
                "date_joined TEXT, dob TEXT, address TEXT, surbub TEXT, " +
                "landline INTEGER, work_num INTEGER, cell_num INTEGER, " +
                "employer TEXT, position TEXT, email TEXT, home_group_leader TEXT, " +
                "dept_leader TEXT, dept TEXT, salvation TEXT, water_bapt TEXT, spirit_bapt TEXT)";

        String guest_data_users = "CREATE TABLE IF NOT EXISTS guest_data_users " +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "user_name TEXT UNIQUE, " +
                "password TEXT, " +
                "user_type TEXT)";

        Statement stmt = null;
        try {
            Connection conn = Sqlite.connector();
            stmt = conn.createStatement();

            stmt.addBatch(userTable);
            stmt.addBatch(guest_data);
            stmt.addBatch(guest_data_users);
            stmt.addBatch(fillUserTable);
            stmt.addBatch(membersTable);
            stmt.addBatch(kidsTable);
            stmt.executeBatch();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        createFolder();
        createDB("church.db");
        setUpDB();
    }

}
