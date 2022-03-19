package com.cm.cm.database;

import java.sql.*;

public class Sqlite {
    private final static String db = "jdbc:sqlite:sqlite/church.db";

    public static Connection connector() throws SQLException {
        return DriverManager.getConnection(db);
    }

    public boolean validateUser(String user, String pass, String userType) throws SQLException {

        String QUERY = "SELECT * FROM user_login WHERE user_name = ? AND password = ? AND user_type = ?";

        try (Connection conn = connector();

             PreparedStatement pstmt = conn.prepareStatement(QUERY)) {
            pstmt.setString(1, user);
            pstmt.setString(2, pass);
            pstmt.setString(3, userType);

            ResultSet res = pstmt.executeQuery();
            if (res.next()) {
                return true;
            }

        } catch (SQLException e) {
            printSQLException(e);
            // TODO: handle exception
        }
        return false;
    }

    public void searchDB(String query) {
        String search = "select * from members WHERE fname LIKE '" + query + "%'";
        try (Connection conn = connector();

             PreparedStatement pstmt = conn.prepareStatement(search);) {
            ResultSet res = pstmt.executeQuery();
            try {
                while (res.next()) {
                    System.out.println(res.getString("id") + res.getString("fname"));
                }
                if (!res.next()) {
                    System.out.println("Search failed");
                }
            } catch (Exception e) {
                System.out.println(e);
                System.out.println("Nothing found");
            }
        } catch (SQLException e) {
            printSQLException(e);
            // TODO: handle exception
        }
    }

    public void searchDBKids(String query) {
        String search = "select * from kids_members WHERE fname LIKE '" + query + "%'";
        try (Connection conn = connector();

             PreparedStatement pstmt = conn.prepareStatement(search);) {
            ResultSet res = pstmt.executeQuery();
            try {
                while (res.next()) {
                    System.out.println(res.getString("id") + res.getString("fname"));
                }
                if (!res.next()) {
                    System.out.println("Search failed");
                }
            } catch (Exception e) {
                System.out.println(e);
                System.out.println("Nothing found");
            }
        } catch (SQLException e) {
            printSQLException(e);
            // TODO: handle exception
        }
    }



    public void insertUser(String userName, String passWord, String userType){
        String sql = "INSERT OR IGNORE INTO user_login (user_name, password, user_type) VALUES (?, ?, ?)";

        try{
            Connection conn = connector();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, userName);
            pstmt.setString(2, passWord);
            pstmt.setString(3, userType);

            pstmt.execute();
        } catch(SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }


    public void deleteUser(String id, String userType){
        String sql = "DELETE FROM user_login WHERE id = ? AND user_type = ?";

        try{
            Connection conn = connector();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, id);
            pstmt.setString(2, userType);
            pstmt.execute();
        } catch (SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void insertValues(String title, String fname, String lname, String gender, String id_no, String children_num,
                             String maritial_status, String date_joined, String dob, String address, String surbub, String homePhone,
                             String workPhone, String mobilePhone, String employer, String position, String email, String homeLeader,
                             String deptLeader, String dept, String salvation, String water_bapt, String spirit_bapt) {
        String values = "INSERT OR IGNORE INTO members (title, fname, lname, gender, id_no, kids_num, maritial_status, date_joined, dob, address, surbub, landline, work_num, cell_num, employer, position, email, home_group_leader, department_leader, dept, salvation, water_bapt, spirit_bapt)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";

        try {
            Connection conn = connector();
            PreparedStatement pstmt = conn.prepareStatement(values);

            pstmt.setString(1, title);
            pstmt.setString(2, fname);
            pstmt.setString(3, lname);
            pstmt.setString(4, gender);
            pstmt.setString(5, id_no);
            pstmt.setString(6, children_num);
            pstmt.setString(7, maritial_status);
            pstmt.setString(8, date_joined);
            pstmt.setString(9, dob);
            pstmt.setString(10, address);
            pstmt.setString(11, surbub);
            pstmt.setString(12, homePhone);
            pstmt.setString(13, workPhone);
            pstmt.setString(14, mobilePhone);
            pstmt.setString(15, employer);
            pstmt.setString(16, position);
            pstmt.setString(17, email);
            pstmt.setString(18, homeLeader);
            pstmt.setString(19, deptLeader);
            pstmt.setString(20, dept);
            pstmt.setString(21, salvation);
            pstmt.setString(22, water_bapt);
            pstmt.setString(23, spirit_bapt);

            pstmt.execute();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }

    public void insertValuesKids(String title, String fname, String lname, String gender, String id_no, String children_num,
                                 String maritial_status, String date_joined, String dob, String address, String surbub, String homePhone,
                                 String workPhone, String mobilePhone, String employer, String position, String email, String homeLeader,
                                 String deptLeader, String dept, String salvation, String water_bapt, String spirit_bapt) {
        String values = "INSERT OR IGNORE INTO kids_members (title, fname, lname, gender, id_no, kids_num, maritial_status, date_joined, dob, address, surbub, landline, work_num, cell_num, employer, position, email, home_group_leader, dept_leader, dept, salvation, water_bapt, spirit_bapt)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";

        try {
            Connection conn = connector();
            PreparedStatement pstmt = conn.prepareStatement(values);

            pstmt.setString(1, title);
            pstmt.setString(2, fname);
            pstmt.setString(3, lname);
            pstmt.setString(4, gender);
            pstmt.setString(5, id_no);
            pstmt.setString(6, children_num);
            pstmt.setString(7, maritial_status);
            pstmt.setString(8, date_joined);
            pstmt.setString(9, dob);
            pstmt.setString(10, address);
            pstmt.setString(11, surbub);
            pstmt.setString(12, homePhone);
            pstmt.setString(13, workPhone);
            pstmt.setString(14, mobilePhone);
            pstmt.setString(15, employer);
            pstmt.setString(16, position);
            pstmt.setString(17, email);
            pstmt.setString(18, homeLeader);
            pstmt.setString(19, deptLeader);
            pstmt.setString(20, dept);
            pstmt.setString(21, salvation);
            pstmt.setString(22, water_bapt);
            pstmt.setString(23, spirit_bapt);

            pstmt.execute();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }

    public void updateValues(String title, String fname, String lname, String gender, String id_no, String children_num,
                             String maritial_status, String date_joined, String dob, String address, String surbub, String homePhone,
                             String workPhone, String mobilePhone, String employer, String position, String email, String homeLeader,
                             String deptLeader, String dept, String salvation, String water_bapt, String spirit_bapt) {

        String values = "UPDATE kids_members SET title=?, fname=?, lname=? gender=?, id_no=?, kids_num=?, maritial_status=?, date_joined=?, dob=?, address=?, surbub=?, landline=?, work_num=?, cell_num=?, employer=?, position=?, email=?, home_group_leader=?, dept_leader=?, dept=?, salvation=?, water_bapt=?, spirit_bapt=? WHERE id =?";

        try {
            Connection conn = connector();
            PreparedStatement pstmt = conn.prepareStatement(values);
            pstmt.setString(1, title);
            pstmt.setString(2, fname);
            pstmt.setString(3, lname);
            pstmt.setString(4, gender);
            pstmt.setString(5, id_no);
            pstmt.setString(6, children_num);
            pstmt.setString(7, maritial_status);
            pstmt.setString(8, date_joined);
            pstmt.setString(9, dob);
            pstmt.setString(10, address);
            pstmt.setString(11, surbub);
            pstmt.setString(12, homePhone);
            pstmt.setString(13, workPhone);
            pstmt.setString(14, mobilePhone);
            pstmt.setString(15, employer);
            pstmt.setString(16, position);
            pstmt.setString(17, email);
            pstmt.setString(18, homeLeader);
            pstmt.setString(19, deptLeader);
            pstmt.setString(20, dept);
            pstmt.setString(21, salvation);
            pstmt.setString(22, water_bapt);
            pstmt.setString(23, spirit_bapt);

            pstmt.execute();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }

    public void updateValuesKids(String title, String fname, String lname, String gender, String id_no, String children_num,
                                 String maritial_status, String date_joined, String dob, String address, String surbub, String homePhone,
                                 String workPhone, String mobilePhone, String employer, String position, String email, String homeLeader,
                                 String deptLeader, String dept, String salvation, String water_bapt, String spirit_bapt, String id) {

        String values = "UPDATE kids_members SET title=?, fname=?, lname=? gender=?, id_no=?, kids_num=?, maritial_status=?, date_joined=?, dob=?, address=?, surbub=?, landline=?, work_num=?, cell_num=?, employer=?, position=?, email=?, home_group_leader=?, dept_leader=?, dept=?, salvation=?, water_bapt=?, spirit_bapt=? WHERE id = ? ";

        try {
            Connection conn = connector();
            PreparedStatement pstmt = conn.prepareStatement(values);
            pstmt.setString(1, title);
            pstmt.setString(2, fname);
            pstmt.setString(3, lname);
            pstmt.setString(4, gender);
            pstmt.setString(5, id_no);
            pstmt.setString(6, children_num);
            pstmt.setString(7, maritial_status);
            pstmt.setString(8, date_joined);
            pstmt.setString(9, dob);
            pstmt.setString(10, address);
            pstmt.setString(11, surbub);
            pstmt.setString(12, homePhone);
            pstmt.setString(13, workPhone);
            pstmt.setString(14, mobilePhone);
            pstmt.setString(15, employer);
            pstmt.setString(16, position);
            pstmt.setString(17, email);
            pstmt.setString(18, homeLeader);
            pstmt.setString(19, deptLeader);
            pstmt.setString(20, dept);
            pstmt.setString(21, salvation);
            pstmt.setString(22, water_bapt);
            pstmt.setString(23, spirit_bapt);
            pstmt.setString(24, id);

            pstmt.execute();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }


    public void delete_row_by_id(String id_) {
        String update = "DELETE FROM members WHERE id = ? ";
        try {
            Connection conn = connector();
            PreparedStatement pstmt = conn.prepareStatement(update);
            pstmt.setString(1, id_);

            pstmt.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void delete_row_by_id_kids(String id_) {
        String update = "DELETE FROM kids_members WHERE id = ? ";
        try {
            Connection conn = connector();
            PreparedStatement pstmt = conn.prepareStatement(update);
            pstmt.setString(1, id_);

            pstmt.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }

            }
        }
    }

    public Connection checkConnection() {
        Connection conn = null;

        try {

            conn = DriverManager.getConnection(db);

            if (conn != null) {
                System.out.println("DB Connection ok!");
            } else {
                System.out.println("Connection Failed");
            }

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static void main(String[] args) throws SQLException {
        Sqlite sqlite3 = new Sqlite();
        sqlite3.checkConnection();
    }

}