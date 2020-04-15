package com.dto;

public class UserIdDto {

    private Integer userID;

    public UserIdDto() {
    }

    public UserIdDto(Integer userID) {
        this.userID = userID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }
}
