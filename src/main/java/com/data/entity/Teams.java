package com.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "teams")
public class Teams {

    @Id
    @Column(name = "team_id")
    private Integer teamId;

    private String teamName;
}
