package com.schoolAdmin.database;
//TODO: Fix up file and the functions.
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

                PreparedStatement pstmt = conn.prepareStatement(QUERY);) {

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

    public boolean validateAdmin(String user, String pass) throws SQLException {

        String QUERY = "SELECT * FROM admin_login WHERE name = ? AND password = ? ";

        try (Connection conn = connector();

                PreparedStatement pstmt = conn.prepareStatement(QUERY);) {
            pstmt.setString(1, user);
            pstmt.setString(2, pass);

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
    
    public boolean validateCashier(String user, String pass) throws SQLException {

        String QUERY = "SELECT * FROM employee_login WHERE name = ? AND password = ? ";

        try (Connection conn = connector();

                PreparedStatement pstmt = conn.prepareStatement(QUERY);) {
            pstmt.setString(1, user);
            pstmt.setString(2, pass);

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
        String search = "select * from members WHERE name LIKE '" + query + "%'";
        try (Connection conn = connector();

                PreparedStatement pstmt = conn.prepareStatement(search);) {
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
            // TODO: handle exception
        }
    }

    public void insertValues( 
        String title,
        String name, 
        String gender, 
        String id_no, 
        String children_num, 
        String maritial_status, 
        String date_joined,
        String dob,
        String address,
        String surbub,
        String homePhone,
        String workPhone,
        String mobilePhone,
        String employer,
        String position,
        String email,
        String homeLeader,
        String deptLeader,
        String dept,
        String salvation,
        String water_bapt,
        String spirit_bapt
        ) {
        String values = 
        "INSERT INTO members (title, name, gender, id_no, children_num, maritial_status, date_joined, dob, address, surbub, home_phone, work_phone, mobile_phone, employer, position, email, home_group_leader, department_leader, department, salvation, water_baptism, spirit_baptism)" + 
        " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";

        try {
            Connection conn = connector();
            PreparedStatement pstmt = conn.prepareStatement(values);
            
            pstmt.setString(1, title);
            pstmt.setString(2, name);
            pstmt.setString(3, gender);
            pstmt.setString(4, id_no);
            pstmt.setString(5, children_num);
            pstmt.setString(6, maritial_status);
            pstmt.setString(7, date_joined);
            pstmt.setString(8, dob);
            pstmt.setString(9, address);
            pstmt.setString(10, surbub);
            pstmt.setString(11, homePhone);
            pstmt.setString(12, workPhone);
            pstmt.setString(13, mobilePhone);
            pstmt.setString(14, employer);
            pstmt.setString(15, position);
            pstmt.setString(16, email);
            pstmt.setString(17, homeLeader);
            pstmt.setString(18, deptLeader);
            pstmt.setString(19, dept);
            pstmt.setString(20, salvation);
            pstmt.setString(21, water_bapt);
            pstmt.setString(22, spirit_bapt);

            pstmt.execute();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }
    
    public void updateValues(
        String title,
        String name, 
        String gender, 
        String id_no, 
        String children_num, 
        String maritial_status, 
        String date_joined,
        String dob,
        String address,
        String surbub,
        String homePhone,
        String workPhone,
        String mobilePhone,
        String employer,
        String position,
        String email,
        String homeLeader,
        String deptLeader,
        String dept,
        String salvation,
        String water_bapt,
        String spirit_bapt
        ) {
        
            String values = 
        "UPDATE members SET title=?, name=?, gender=?, id_no=?, children_num=?, maritial_status=?, date_joined=?, dob=?, address=?, surbub=?, home_phone=?, work_phone=?, mobile_phone=?, employer=?, position=?, email=?, home_group_leader=?, department_leader=?, department=?, salvation=?, water_baptism=?, spirit_baptism=? ";

        try {
            Connection conn = connector();
            PreparedStatement pstmt = conn.prepareStatement(values);
            pstmt.setString(1, title);
            pstmt.setString(2, name);
            pstmt.setString(3, gender);
            pstmt.setString(4, id_no);
            pstmt.setString(5, children_num);
            pstmt.setString(6, maritial_status);
            pstmt.setString(7, date_joined);
            pstmt.setString(8, dob);
            pstmt.setString(9, address);
            pstmt.setString(10, surbub);
            pstmt.setString(11, homePhone);
            pstmt.setString(12, workPhone);
            pstmt.setString(13, mobilePhone);
            pstmt.setString(14, employer);
            pstmt.setString(15, position);
            pstmt.setString(16, email);
            pstmt.setString(17, homeLeader);
            pstmt.setString(18, deptLeader);
            pstmt.setString(19, dept);
            pstmt.setString(20, salvation);
            pstmt.setString(21, water_bapt);
            pstmt.setString(22, spirit_bapt);

            pstmt.execute();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }

    // public void updateValues( String name, String detail, String units_used, String units_left, String unit_price, String restock, String id){
    //     String update = "UPDATE ace_hardware SET name=?, detail=?, units_used=?, units_left=?, unit_price=?, restock=? WHERE id=?";
    //     try{
    //         Connection conn = connector();
    //         PreparedStatement pstmt = conn.prepareStatement(update);
    //         pstmt.setString(1, name);
    //         pstmt.setString(2, detail);
    //         pstmt.setString(3, units_used);
    //         pstmt.setString(4, units_left);
    //         pstmt.setString(5, unit_price);
    //         pstmt.setString(6, restock);
    //         pstmt.setString(7, id);

    //         pstmt.execute();
    //     } catch(SQLException e){
    //         System.out.println(e.getMessage());
    //         e.printStackTrace();
    //     }
    // }

    public void delete_row_by_id( String id_){
        String update = "DELETE FROM members WHERE id_num = ? ";
        try{
            Connection conn = connector();
            PreparedStatement pstmt = conn.prepareStatement(update);
            pstmt.setString(1, id_);
            
            pstmt.execute();
        } catch(SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    //TODO: Rewrite function to avoid deletion of names
    public void delete_row_by_name( String name_){
        // String update = "DELETE FROM members WHERE name LIKE '" +name_ + "%'";
       
        // try(
        //     Connection conn = connector();
        //     PreparedStatement pstmt = conn.prepareStatement(update);){
        //     // pstmt.setString(1, name_);
            
        //     pstmt.executeQuery();
        // } catch(SQLException e){
        //     System.out.println(e.getMessage());
        //     e.printStackTrace();
        // }
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
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static void main(String[] args) throws SQLException {
        Psql mysql = new Psql();
        mysql.checkConnection(); 
    }

}
