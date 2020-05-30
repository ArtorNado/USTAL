package com.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "match_single")
public class MatchSingle {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "match_id")
    private Integer matchId;

    private String date;

    private String time;

    private Integer creatorId;

    private Integer numberParticipant;

    private Integer currentNumberParticipant;

    private String description;

    private String matchCity;

    @JsonIgnore
    @ManyToMany(mappedBy="matchId")
    private Set<UserMatch1> users;


    public MatchSingle(String date, String time, Integer creatorId, Integer numberParticipant, Integer currentNumberParticipant, String description, String matchCity /*Set<UserMatch1> users*/) {
        this.date = date;
        this.time = time;
        this.creatorId = creatorId;
        this.numberParticipant = numberParticipant;
        this.currentNumberParticipant = currentNumberParticipant;
        this.description = description;
        this.matchCity = matchCity;
        /*this.users = users;*/
    }

    public MatchSingle() {
    }

    public String getMatchCity() {
        return matchCity;
    }

    public void setMatchCity(String matchCity) {
        this.matchCity = matchCity;
    }

    public Integer getMatchId() {
        return matchId;
    }

    public void setMatchId(Integer matchId) {
        this.matchId = matchId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public Integer getNumberParticipant() {
        return numberParticipant;
    }

    public void setNumberParticipant(Integer numberParticipant) {
        this.numberParticipant = numberParticipant;
    }

    public Integer getCurrentNumberParticipant() {
        return currentNumberParticipant;
    }

    public void setCurrentNumberParticipant(Integer currentNumberParticipant) {
        this.currentNumberParticipant = currentNumberParticipant;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<UserMatch1> getUsers() {
        return users;
    }

    public void setUsers(Set<UserMatch1> users) {
        this.users = users;
    }
}
