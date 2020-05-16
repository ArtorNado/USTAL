package com.dto;

public class MatchSingleDto {

    private String date;

    private String time;

    private Integer creatorId;

    private Integer numberParticipant;

    private String description;

    private String matchCity;


    public MatchSingleDto(String date, String time, Integer creatorId, Integer numberParticipant, String description, String matchCity) {
        this.date = date;
        this.time = time;
        this.creatorId = creatorId;
        this.numberParticipant = numberParticipant;
        this.description = description;
        this.matchCity = matchCity;
    }

    public MatchSingleDto() {
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
}
