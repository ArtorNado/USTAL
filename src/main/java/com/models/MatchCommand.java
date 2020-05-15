package com.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "match_command")
public class MatchCommand {

    @Id
    @Column(name = "match_Id")
    private Integer matchId;

    private String date;

    private String time;

    private Integer creatorId;

    private Integer firstTeamId;

    private Integer secondTeamId;

    public MatchCommand(Integer matchId, String date, String time, Integer creatorId, Integer firstTeamId, Integer secondTeamId) {
        this.matchId = matchId;
        this.date = date;
        this.time = time;
        this.creatorId = creatorId;
        this.firstTeamId = firstTeamId;
        this.secondTeamId = secondTeamId;
    }

    public MatchCommand() {
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

    public Integer getFirstTeamId() {
        return firstTeamId;
    }

    public void setFirstTeamId(Integer firstTeamId) {
        this.firstTeamId = firstTeamId;
    }

    public Integer getSecondTeamId() {
        return secondTeamId;
    }

    public void setSecondTeamId(Integer secondTeamId) {
        this.secondTeamId = secondTeamId;
    }
}
