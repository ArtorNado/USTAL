package com.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name="userData")
public class UserData {

    @Id
    @Column(name = "user_id")
    private Integer userId;

    private String userFirstName;

    private String userSecondName;

    private String userGender;

    private String userCity;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="team_id")
    private Teams team;

    public UserData() {
    }

    public UserData(Integer userId, String userFirstName, String userSecondName, String userGender, String userCity, Teams team) {
        this.userId = userId;
        this.userFirstName = userFirstName;
        this.userSecondName = userSecondName;
        this.userGender = userGender;
        this.userCity = userCity;
        this.team = team;
    }

    public Teams getTeam() {
        return team;
    }

    public void setTeam(Teams team) {
        this.team = team;
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