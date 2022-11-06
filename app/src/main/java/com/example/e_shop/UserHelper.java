package com.example.e_shop;

public class UserHelper {
    String name, userName, useremail,userPhoneNo, userPassword;

    public UserHelper() {
    }

    public UserHelper(String userName, String userUserName, String useremail, String userPhoneNo, String userPassword) {
        this.name = userName;
        this.userName = userUserName;
        this.useremail = useremail;
        this.userPhoneNo = userPhoneNo;
        this.userPassword = userPassword;
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

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getUserPhoneNo() {
        return userPhoneNo;
    }

    public void setUserPhoneNo(String userPhoneNo) {
        this.userPhoneNo = userPhoneNo;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
