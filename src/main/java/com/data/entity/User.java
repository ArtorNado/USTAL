package com.data.entity;

import javax.persistence.*;

@Entity
@Table(name="usr")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer userId;

    private String userLogin;

    private String userPassword;

    public User() {
    }

    public User(String userLogin, String userPassword) {
        this.userLogin = userLogin;
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
