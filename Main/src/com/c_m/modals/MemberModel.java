package com.c_m.modals;

public class MemberModel {

  public String id_ = new String();
  public String fname = new String();
  public String lname = new String();
  public String Title_ = new String();
  public String Address = new String();
  public String DOB_ = new String();
  public String DateJoined_ = new String();
  public String Dept = new String();
  public String DeptLeader = new String();
  public String HomeGroup = new String();
  public String Homephone = new String();
  public String IDNum = new String();
  public String KidsNo_ = new String();
  public String MStatus_ = new String();
  public String MobilePhone = new String();
  public String Email = new String();
  public String Salvation = new String();
  public String Sex = new String();
  public String waterBapt = new String();
  public String SpiritBapt = new String();
  public String Surbub = new String();
  public String WorkPhone = new String();

  public MemberModel(
    String id,
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
    this.id_ = id;
    this.fname = fname_;
    this.lname = lname_;
    this.Title_ = title_;
    this.Address = address_;
    this.DOB_ = dob_;
    this.DateJoined_ = dateJoined_;
    this.Dept = dept_;
    this.DeptLeader = dept_leader_;
    this.HomeGroup = cellLeader;
    this.Homephone = landline;
    this.IDNum = IdNum;
    this.KidsNo_ = kidsNo_;
    this.MStatus_ = status;
    this.MobilePhone = mobilePhone;
    this.Email = email;
    this.Salvation = salvation_;
    this.Sex = gender;
    this.waterBapt = waterBapt_;
    this.SpiritBapt = spiritBapt_;
    this.Surbub = surbub_;
  }

  public String getID() {
    return this.id_;
  }

  public void setID(String id) {
    this.id_ = id;
  }

  public String getFname() {
    return this.fname;
  }

  public void setFname(String name) {
    this.fname = name;
  }

  public String getLname() {
    return this.lname;
  }

  public void setLname(String Lname) {
    this.lname = Lname;
  }

  public String getTitle() {
    return this.Title_;
  }

  public void setTitle(String title) {
    this.Title_ = title;
  }

  public String getAddress() {
    return this.Address;
  }

  public void setAddress(String address) {
    this.Address = address;
  }

  public String getDob() {
    return this.DOB_;
  }

  public void setDob(String dob) {
    this.DOB_ = dob;
  }

  public String getDateJoined() {
    return this.DateJoined_;
  }

  public void setDateJoined(String date) {
    this.DateJoined_ = date;
  }

  public String getDept() {
    return this.Dept;
  }

  public void setDept(String dept) {
    this.Dept = dept;
  }

  public String getHomeGroup() {
    return this.HomeGroup;
  }

  public void setHomeGroup(String homeGroup) {
    this.HomeGroup = homeGroup;
  }

  public String getHomPhone() {
    return this.Homephone;
  }

  public void getHomePhone(String homePhone) {
    this.Homephone = homePhone;
  }

  public String getIDNo() {
    return this.IDNum;
  }

  public void getIDNo(String idNum) {
    this.IDNum = idNum;
  }

  public String getKidsNo() {
    return this.KidsNo_;
  }

  public void setKidsNo(String no_) {
    this.KidsNo_ = no_;
  }

  public String getMStatus() {
    return this.MStatus_;
  }

  public void setMstatus(String status) {
    this.MStatus_ = status;
  }

  public String getMobile() {
    return this.MobilePhone;
  }

  public void setMobile(String phone) {
    this.MobilePhone = phone;
  }

  public String getEmail() {
    return this.Email;
  }

  public void setEmail(String email) {
    this.Email = email;
  }

  public String getSalvation() {
    return this.Salvation;
  }

  public void setSalvation(String salvation) {
    this.Salvation = salvation;
  }

  public String getSex() {
    return this.Sex;
  }

  public void setSex(String sex) {
    this.Sex = sex;
  }

  public String getWaterBapt() {
    return this.waterBapt;
  }

  public String getSpiritBapt() {
    return this.SpiritBapt;
  }

  public void setSpiritBapt(String spirit) {
    this.SpiritBapt = spirit;
  }

  public String getSurbub() {
    return this.Surbub;
  }

  public void setSurbub(String surbub) {
    this.Surbub = surbub;
  }

  public String getWorkPhone() {
    return this.WorkPhone;
  }

  public void setWorkPhone(String workPhone) {
    this.WorkPhone = workPhone;
  }
}
