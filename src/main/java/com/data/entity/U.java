package com.data.entity;

public class U {
    private String userLogin;
    private String userPassword;

    public U(String userName, String userPassword) {
        this.userLogin = userName;
        this.userPassword = userPassword;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
