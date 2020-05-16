package com.dto;

public class MatchCommandDto {

    private String date;

    private String time;

    private Integer creatorId;

    private String matchCity;

    private String description;


    public MatchCommandDto(String date, String time, Integer creatorId, String matchCity, String description) {
        this.date = date;
        this.time = time;
        this.creatorId = creatorId;
        this.matchCity = matchCity;
        this.description = description;
    }

    public MatchCommandDto() {
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

    public String getMatchCity() {
        return matchCity;
    }

    public void setMatchCity(String matchCity) {
        this.matchCity = matchCity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
