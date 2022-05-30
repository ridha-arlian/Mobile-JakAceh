package com.example.jakaceh;

public class UserHelperClass {
    String name, userName, gmail, phoneNo, pass;

    public UserHelperClass() {

    }

    public UserHelperClass(String name, String userName, String gmail, String phoneNo, String pass) {
        this.name = name;
        this.userName = userName;
        this.gmail = gmail;
        this.phoneNo = phoneNo;
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
