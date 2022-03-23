package com.cm.cm.database;

import java.sql.*;

public class Psql {
    private final static String db = "jdbc:postgresql://localhost/church";
    private final static String user = "guest";
    private final static String password = "1234567890";

    public static Connection connector() throws SQLException {
        return DriverManager.getConnection(db, user, password);
    }

    public boolean check(String search) throws SQLException {
        String QUERY = "SELECT * FROM user_login WHERE name LIKE ?";

        try (Connection conn = connector();

             PreparedStatement pstmt = conn.prepareStatement(QUERY)) {

            ResultSet res = pstmt.executeQuery();
            if (res.next()) {
                return true;
            }

        } catch (SQLException e) {
            printSQLException(e);
        }
        return false;
    }

    public boolean validateAdmin(String user, String pass) throws SQLException {

        String QUERY = "SELECT * FROM admin_login WHERE user_name = ? AND password = ? ";

        try (Connection conn = Psql.connector();

             PreparedStatement pstmt = conn.prepareStatement(QUERY)) {
            pstmt.setString(1, user);
            pstmt.setString(2, pass);

            ResultSet res = pstmt.executeQuery();
            if (res.next()) {
                return true;
            }

        } catch (SQLException e) {
            printSQLException(e);
        }
        return false;
    }

    public boolean validateCashier(String user, String pass) throws SQLException {

        String QUERY = "SELECT * FROM employee_login WHERE name = ? AND password = ? ";

        try (Connection conn = connector();

             PreparedStatement pstmt = conn.prepareStatement(QUERY)) {
            pstmt.setString(1, user);
            pstmt.setString(2, pass);

            ResultSet res = pstmt.executeQuery();
            if (res.next()) {
                return true;
            }

        } catch (SQLException e) {
            printSQLException(e);
        }
        return false;
    }

    public void searchDB(String query) {
        String search = "select * from members WHERE name LIKE '" + query + "%'";
        try (Connection conn = connector();

             PreparedStatement pstmt = conn.prepareStatement(search)) {
            ResultSet res = pstmt.executeQuery();
            try {
                while (res.next()) {
                    System.out.println(res.getString("id") + res.getString("name") + res.getString("detail"));
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
        }
    }

    public void searchDBKids(String query) {
        String search = "select * from kids_members WHERE name LIKE '" + query + "%'";
        try (Connection conn = connector();

             PreparedStatement pstmt = conn.prepareStatement(search)) {
            ResultSet res = pstmt.executeQuery();
            try {
                while (res.next()) {
                    System.out.println(res.getString("id") + res.getString("name") + res.getString("detail"));
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
        }
    }

    public void insertValues(String title, String fname, String lname, String gender, String id_no, String children_num,
                             String maritial_status, String date_joined, String dob, String address, String surbub, String homePhone,
                             String workPhone, String mobilePhone, String employer, String position, String email, String homeLeader,
                             String deptLeader, String dept, String salvation, String water_bapt, String spirit_bapt) {
        String values = "INSERT INTO members (title, fname, lname, gender, id_no, children_num, maritial_status, date_joined, dob, address, surbub, home_phone, work_phone, mobile_phone, employer, position, email, home_group_leader, department_leader, department, salvation, water_baptism, spirit_baptism)"
                + " VALUES (?, ?, ?, ?, ?, ?::integer, ?, ?::date, ?::date, ?, ?, ?::integer, ?::integer, ?::integer, ?, ?, ?, ?, ?, ?, ?, ?, ? )";

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
        String values = "INSERT INTO kids_members (title, fname, lname, gender, id_no, children_num, maritial_status, date_joined, dob, address, surbub, home_phone, work_phone, mobile_phone, employer, position, email, home_group_leader, department_leader, department, salvation, water_baptism, spirit_baptism)"
                + " VALUES (?, ?, ?, ?, ?, ?::integer, ?, ?::date, ?::date, ?, ?, ?::integer, ?::integer, ?::integer, ?, ?, ?, ?, ?, ?, ?, ?, ? )";

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

        String values = "UPDATE kids_members SET title=?, fname=?, lname=? gender=?, id_no=?, children_num=?, maritial_status=?, date_joined=?, dob=?, address=?, surbub=?, home_phone=?, work_phone=?, mobile_phone=?, employer=?, position=?, email=?, home_group_leader=?, department_leader=?, department=?, salvation=?, water_baptism=?, spirit_baptism=? ";

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
                                 String deptLeader, String dept, String salvation, String water_bapt, String spirit_bapt) {

        String values = "UPDATE kids_members SET title=?, fname=?, lname=? gender=?, id_no=?, children_num=?, maritial_status=?, date_joined=?, dob=?, address=?, surbub=?, home_phone=?, work_phone=?, mobile_phone=?, employer=?, position=?, email=?, home_group_leader=?, department_leader=?, department=?, salvation=?, water_baptism=?, spirit_baptism=? ";

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

    // TODO: Rewrite function to avoid deletion of names
    public void delete_row_by_name(String name_) {
        System.out.println("'Delete By Name' : Function needs to be fixed");
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

            conn = DriverManager.getConnection(db, user, password);

            if (conn != null) {
                System.out.println("DB Connection ok!");
            } else {
                System.out.println("Connection Failed");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static void main(String[] args) throws SQLException {
        Psql psql = new Psql();
        psql.checkConnection();
    }

}

