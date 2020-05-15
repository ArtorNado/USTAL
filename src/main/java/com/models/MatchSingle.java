package com.models;

import javax.persistence.*;

@Entity
@Table(name = "match_single")
public class MatchSingle {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "match_Id")
    private Integer matchId;

    private String date;

    private String time;

    private Integer creatorId;

    private Integer numberParticipant;

    private Integer currentNumberParticipant;

    private String description;

    public MatchSingle(String date, String time, Integer creatorId, Integer numberParticipant, Integer currentNumberParticipant, String description) {
        this.date = date;
        this.time = time;
        this.creatorId = creatorId;
        this.numberParticipant = numberParticipant;
        this.currentNumberParticipant = currentNumberParticipant;
        this.description = description;
    }

    public MatchSingle() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}
