package com.data.entity;

public class U {
    public int userName;
    public String userPassword;

    public U() {
    }

    public U(int userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public int getUserName() {
        return userName;
    }

    public void setUserName(int userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
