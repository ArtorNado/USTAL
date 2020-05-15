package com.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ended_command_match")
public class EndedCommandMatch {

    @Id
    @Column(name = "match_Id")
    private Integer matchId;

    private Integer firstTeamId;

    private Integer secondTeamId;

    private Integer winTeamId;

    private Integer goalsFirstTeam;

    private Integer goalsSecondTeam;


    public EndedCommandMatch(Integer matchId, Integer firstTeamId, Integer secondTeamId, Integer winTeamId, Integer goalsFirstTeam, Integer goalsSecondTeam) {
        this.matchId = matchId;
        this.firstTeamId = firstTeamId;
        this.secondTeamId = secondTeamId;
        this.winTeamId = winTeamId;
        this.goalsFirstTeam = goalsFirstTeam;
        this.goalsSecondTeam = goalsSecondTeam;
    }

    public EndedCommandMatch() {
    }

    public Integer getMatchId() {
        return matchId;
    }

    public void setMatchId(Integer matchId) {
        this.matchId = matchId;
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

    public Integer getWinTeamId() {
        return winTeamId;
    }

    public void setWinTeamId(Integer winTeamId) {
        this.winTeamId = winTeamId;
    }

    public Integer getGoalsFirstTeam() {
        return goalsFirstTeam;
    }

    public void setGoalsFirstTeam(Integer goalsFirstTeam) {
        this.goalsFirstTeam = goalsFirstTeam;
    }

    public Integer getGoalsSecondTeam() {
        return goalsSecondTeam;
    }

    public void setGoalsSecondTeam(Integer goalsSecondTeam) {
        this.goalsSecondTeam = goalsSecondTeam;
    }
}
