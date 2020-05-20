package com.dto;

public class UserTeamStatus {
    private Integer userId;
    private Integer teamId;
    private String status;

    public UserTeamStatus(Integer userId, Integer teamId, String status) {
        this.userId = userId;
        this.teamId = teamId;
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
