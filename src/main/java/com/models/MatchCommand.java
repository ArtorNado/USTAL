package com.models;

import javax.persistence.*;

@Entity
@Table(name = "match_command")
public class MatchCommand {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "match_id")
    private Integer matchId;

    private String date;

    private String time;

    private Integer creatorId;

    private Integer firstTeamId;

    private Integer secondTeamId;

    private String matchCity;

    private String description;

    public MatchCommand(String date, String time, Integer creatorId, Integer firstTeamId, Integer secondTeamId, String matchCity, String description) {
        this.date = date;
        this.time = time;
        this.creatorId = creatorId;
        this.firstTeamId = firstTeamId;
        this.secondTeamId = secondTeamId;
        this.matchCity = matchCity;
        this.description = description;
    }

    public MatchCommand() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
