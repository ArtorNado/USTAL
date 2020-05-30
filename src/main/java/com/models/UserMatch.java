package com.models;


import javax.persistence.*;

@Entity
@Table(name = "user_single_match")
public class UserMatch {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="match_id")
    private MatchSingle matchId;

    private String role;

    public UserMatch(Integer userId, MatchSingle matchId, String role) {
        this.userId = userId;
        this.matchId = matchId;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

