package com.models;

import javax.persistence.*;

@Entity
@Table(name = "ended_command_match")
public class EndedCommandMatch {

    @Id
    @Column(name = "match_Id")
    private Integer matchId;

    private Integer winTeamId;

    private Integer goalsFirstTeam;

    private Integer goalsSecondTeam;

    @ManyToOne
    @JoinColumn(name="first_team", insertable=false, updatable=false)
    private Teams firstTeam;

    @ManyToOne
    @JoinColumn(name="second_team", insertable=false, updatable=false)
    private Teams secondTeam;

    public EndedCommandMatch(Integer matchId, Integer winTeamId, Integer goalsFirstTeam, Integer goalsSecondTeam, Teams firstTeam, Teams secondTeam) {
        this.matchId = matchId;
        this.winTeamId = winTeamId;
        this.goalsFirstTeam = goalsFirstTeam;
        this.goalsSecondTeam = goalsSecondTeam;
        this.firstTeam = firstTeam;
        this.secondTeam = secondTeam;
    }

    public EndedCommandMatch() {
    }

    public Teams getFirstTeam() {
        return firstTeam;
    }

    public void setFirstTeam(Teams firstTeam) {
        this.firstTeam = firstTeam;
    }

    public Teams getSecondTeam() {
        return secondTeam;
    }

    public void setSecondTeam(Teams secondTeam) {
        this.secondTeam = secondTeam;
    }

    public Integer getMatchId() {
        return matchId;
    }

    public void setMatchId(Integer matchId) {
        this.matchId = matchId;
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
