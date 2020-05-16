package com.models;


import javax.persistence.*;

@Entity
@Table(name = "user_single_match")
public class UserMatch {

    @Id
    @Column(name = "user_id")
    private Integer userId;

    @ManyToOne
    @JoinColumn(name="match_id")
    private MatchSingle matchId;

    private String role;

    public UserMatch(MatchSingle match, Integer userId, String role) {
        this.userId = userId;
        this.matchId = match;
        this.role = role;
    }

    public UserMatch() {
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public MatchSingle getMatchId() {
        return matchId;
    }

    public void setMatchId(MatchSingle matchId) {
        this.matchId = matchId;
    }
}

