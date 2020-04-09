package com.dto;

public class UserIdDto {

    private String userID;

    public UserIdDto() {
    }

    public UserIdDto(String userID) {
        this.userID = userID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
