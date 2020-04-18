package com.dto;

public class TeamDto {

    private String teamName;

    private String teamCity;

    private Integer creatorId;

    public TeamDto(String teamName, String teamCity, Integer creatorId) {
        this.teamName = teamName;
        this.teamCity = teamCity;
        this.creatorId = creatorId;
    }

    public TeamDto() {
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamCity() {
        return teamCity;
    }

    public void setTeamCity(String teamCity) {
        this.teamCity = teamCity;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }
}
