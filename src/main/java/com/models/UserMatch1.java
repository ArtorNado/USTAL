package com.models;


import org.springframework.context.annotation.Primary;

import javax.persistence.*;

@Entity
@Table(name = "user_single_match2")
public class UserMatch1 {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private Integer iid;

    @Column(name = "user_id")
    private String userId;

    @ManyToOne()
    @JoinColumn(name="match_id")
    private MatchSingle matchId;

    private String role;

    public UserMatch1(Integer iid,  MatchSingle matchId,String userId, String role) {
        this.iid = iid;
        this.matchId = matchId;
        this.userId = userId;
        this.role = role;
    }

    public Integer getId() {
        return iid;
    }

    public void setId(Integer id) {
        this.iid = id;
    }

    public UserMatch1() {
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public MatchSingle getMatchId() {
        return matchId;
    }

    public void setMatchId(MatchSingle matchId) {
        this.matchId = matchId;
    }
}

