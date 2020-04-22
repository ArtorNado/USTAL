package com.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "teams")
public class Teams {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "team_id")
    private Integer teamId;

    private String teamName;

    private String teamCity;

    private Integer creatorId;

    private String teamStatus;

    @JsonIgnore
    @OneToMany(mappedBy="team")
    private Set<UserData> users;

    public Teams(Integer teamId, String teamName, String teamCity, Integer creatorId, Set<UserData> users) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.teamCity = teamCity;
        this.creatorId = creatorId;
        this.users = users;
    }

    public Teams() {
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
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

    public Set<UserData> getUsers() {
        return users;
    }

    public void setUsers(Set<UserData> users) {
        this.users = users;
    }
}
