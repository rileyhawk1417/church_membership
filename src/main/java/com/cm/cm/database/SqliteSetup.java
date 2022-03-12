package com.cm.cm.database;

import java.sql.*;


public class SqliteSetup {

    Sqlite sqlite = new Sqlite();

    public static void createDB(String fileName) {
        String url = "jdbc:sqlite:sqlite/" + fileName;

        try (Connection conn = DriverManager.getConnection(url)) {
            DatabaseMetaData meta = conn.getMetaData();
            if (conn == null) {

//                meta.getURL();
                System.out.println("DB has been created @ " + meta.getURL());
            } else {
                Sqlite.connector();
                System.out.println(meta.getURL());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void setUpDB() {
        String adminTable = "CREATE TABLE IF NOT EXISTS admin_login " +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "user_name TEXT UNIQUE, " +
                "password TEXT, " +
                "user_type TEXT)";

        String userTable = "CREATE TABLE IF NOT EXISTS user_login " +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "user_name TEXT UNIQUE, " +
                "password TEXT, " +
                "user_type TEXT)";

        String fillUserTable = "INSERT OR IGNORE INTO user_login (user_name, password, user_type) VALUES ('guest', '', 'user')" ;
        /*
        * INSERT INTO #table1 (Id, guidd, TimeAdded, ExtraData)
            SELECT Id, guidd, TimeAdded, ExtraData
            FROM #table2
            WHERE NOT EXISTS (Select Id, guidd From #table1 WHERE #table1.id = #table2.id)
        * */
//        String fillAdminTable = "INSERT INTO admin_login (user_name, password) \n" +
//                "SELECT * \n FROM admin_login \n WHERE NOT EXISTS (SELECT user_name FROM admin_login WHERE admin_login.user_name = 'root'";
        String fillAdminTable = "INSERT OR IGNORE INTO admin_login (user_name, password, user_type) VALUES ('root', 'toor', 'admin')";
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

        Statement stmt = null;
        try {
            Connection conn = Sqlite.connector();
            stmt = conn.createStatement();

            stmt.addBatch( adminTable);
            stmt.addBatch(userTable);
            stmt.addBatch(fillAdminTable);
            stmt.addBatch(fillUserTable);
            stmt.addBatch(membersTable);
            stmt.addBatch(kidsTable);
            stmt.executeBatch();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String args[]) {
        createDB("church.db"); //Find a way to bind DB properties
        setUpDB();
    }

}
