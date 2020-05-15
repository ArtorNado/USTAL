package com.models;


import javax.persistence.*;

@Entity
@Table(name = "user_match")
public class UserMatch {

    @Id
    @Column(name = "match_id")
    private Integer matchId;

    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getMatchId() {
        return matchId;
    }

    public void setMatchId(Integer matchId) {
        this.matchId = matchId;
    }

    public UserMatch(Integer matchId, Integer userId) {
        this.matchId = matchId;
        this.userId = userId;
    }

    public UserMatch() {
    }
}

