package com.data.x;

public class U {

    private String userLogin;
    private String userPassword;
    private String userFirstName;
    private String userSecondName;
    private String userGender;
    private String userCity;

    public U() {
    }

    public U(String userLogin, String userPassword, String userFirstName, String userSecondName, String userGender, String userCity) {
        this.userLogin = userLogin;
        this.userPassword = userPassword;
        this.userFirstName = userFirstName;
        this.userSecondName = userSecondName;
        this.userGender = userGender;
        this.userCity = userCity;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserSecondName() {
        return userSecondName;
    }

    public void setUserSecondName(String userSecondName) {
        this.userSecondName = userSecondName;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public String getUserCity() {
        return userCity;
    }

    public void setUserCity(String userCity) {
        this.userCity = userCity;
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
