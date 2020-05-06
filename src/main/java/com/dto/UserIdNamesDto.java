package com.dto;

public class UserIdNamesDto {

    private Integer userId;

    private String userFirstName;

    private String userSecondName;

    public UserIdNamesDto(Integer userId, String userFirstName, String userSecondName) {
        this.userId = userId;
        this.userFirstName = userFirstName;
        this.userSecondName = userSecondName;
    }

    public UserIdNamesDto() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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
}
