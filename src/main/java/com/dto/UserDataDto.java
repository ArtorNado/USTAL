package com.dto;

public class UserDataDto {

    private String userFirstName;
    private String userSecondName;
    private String userGender;
    private String userCity;

    public UserDataDto() {
    }

    public UserDataDto(String userFirstName, String userSecondName, String userGender, String userCity) {
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
}
