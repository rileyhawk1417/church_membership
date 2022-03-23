package com.cm.cm.modals;

import java.lang.constant.Constable;
import java.util.function.Predicate;

public class UsersModel {
    public String ID;
    public String user_name;
    public String pass;
    public String userType;

    public UsersModel() {

    }

    public String getID() {return ID;}

    public void setID(String id) { this.ID = id;}

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUserType() {return userType;}

    public void setUserType(String user_Type){ this.userType = user_Type;}

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public UsersModel(String userName, String passWord, String user_type, String id){
        this.user_name = userName;
        this.pass = passWord;
        this.userType = user_type;
        this.ID = id;
    }
}
