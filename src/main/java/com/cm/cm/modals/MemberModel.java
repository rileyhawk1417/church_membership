package com.cm.cm.modals;

public class MemberModel {

    public String ID;
    public String fname;
    public String lname;
    public String Title;
    public String Address;
    public String DOB;
    public String Datejoined;
    public String Dept;
    public String deptLeader_;
    public String HomeGroup;
    public String HomePhone;
    public String ID_Num;
    public String childrenNo_;
    public String M_status;
    public String cellNumber;
    public String Email;
    public String Salvation;
    public String Sex;
    public String waterBapt;
    public String SpiritBapt;
    public String Surbub;
    public String WorkPhone;
    public String Employer_;
    public String Position_;

    public String getID() {
        return this.ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getFname() {
        return this.fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return this.lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getTitle() {
        return this.Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getAddress() {
        return this.Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getDOB() {
        return this.DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getDatejoined() {
        return this.Datejoined;
    }

    public void setDatejoined(String Datejoined) {
        this.Datejoined = Datejoined;
    }

    public String getDept() {
        return this.Dept;
    }

    public void setDept(String Dept) {
        this.Dept = Dept;
    }

    public String getDeptLeader_() {
        return this.deptLeader_;
    }

    public void setDeptLeader_(String deptLeader_) {
        this.deptLeader_ = deptLeader_;
    }

    public String getHomeGroup() {
        return this.HomeGroup;
    }

    public void setHomeGroup(String HomeGroup) {
        this.HomeGroup = HomeGroup;
    }

    public String getHomePhone() {
        return this.HomePhone;
    }

    public void setHomePhone(String HomePhone) {
        this.HomePhone = HomePhone;
    }

    public String getID_Num() {
        return this.ID_Num;
    }

    public void setID_Num(String ID_Num) {
        this.ID_Num = ID_Num;
    }

    public String getChildrenNo_() {
        return this.childrenNo_;
    }

    public void setChildrenNo_(String childrenNo_) {
        this.childrenNo_ = childrenNo_;
    }

    public String getM_status() {
        return this.M_status;
    }

    public void setM_status(String M_status) {
        this.M_status = M_status;
    }

    public String getCellNumber() {
        return this.cellNumber;
    }

    public void setCellNumber(String cellNumber) {
        this.cellNumber = cellNumber;
    }

    public String getEmail() {
        return this.Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getSalvation() {
        return this.Salvation;
    }

    public void setSalvation(String Salvation) {
        this.Salvation = Salvation;
    }

    public String getSex() {
        return this.Sex;
    }

    public void setSex(String Sex) {
        this.Sex = Sex;
    }

    public String getWaterBapt() {
        return this.waterBapt;
    }

    public void setWaterBapt(String waterBapt) {
        this.waterBapt = waterBapt;
    }

    public String getSpiritBapt() {
        return this.SpiritBapt;
    }

    public void setSpiritBapt(String SpiritBapt) {
        this.SpiritBapt = SpiritBapt;
    }

    public String getSurbub() {
        return this.Surbub;
    }

    public void setSurbub(String Surbub) {
        this.Surbub = Surbub;
    }

    public String getWorkPhone() {
        return this.WorkPhone;
    }

    public void setWorkPhone(String WorkPhone) {
        this.WorkPhone = WorkPhone;
    }

    public String getEmployer_(){
        return this.Employer_;
    }

    public void setEmployer_(String Employer_){
        this.Employer_ = Employer_;
    }

    public String getPosition_(){
        return this.Position_;
    }

    public void setPosition_(String Position_){
        this.Position_ = Position_;
    }

    public MemberModel(
            String ID_,
            String title_,
            String fname_,
            String lname_,
            String gender,
            String IdNum,
            String kidsNo_,
            String status,
            String dateJoined_,
            String dob_,
            String address_,
            String surbub_,
            String landline,
            String workPhone_,
            String mobilePhone,
            String employer_,
            String position_,
            String email,
            String cellLeader,
            String dept_leader_,
            String dept_,
            String salvation_,
            String waterBapt_,
            String spiritBapt_
    ) {
        this.ID = ID_;
        this.fname = fname_;
        this.lname = lname_;
        this.Title = title_;
        this.Address = address_;
        this.DOB = dob_;
        this.Datejoined = dateJoined_;
        this.Dept = dept_;
        this.deptLeader_ = dept_leader_;
        this.HomeGroup = cellLeader;
        this.HomePhone = landline;
        this.ID_Num = IdNum;
        this.childrenNo_ = kidsNo_;
        this.M_status = status;
        this.cellNumber = mobilePhone;
        this.Email = email;
        this.Salvation = salvation_;
        this.Sex = gender;
        this.waterBapt = waterBapt_;
        this.SpiritBapt = spiritBapt_;
        this.Surbub = surbub_;
        this.WorkPhone = workPhone_;
        this.Employer_ = employer_;
        this.Position_ = position_;
    }
}
