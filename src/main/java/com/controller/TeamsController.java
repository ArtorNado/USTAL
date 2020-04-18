package com.controller;


import com.data.entity.UserData;
import com.dto.TeamDto;
import com.response.Message;
import com.service.teams.TeamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TeamsController {

    @Autowired
    TeamsService teamsService;

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/createTeam")
    public ResponseEntity<Message> registration(@RequestBody TeamDto teamDto){
        return ResponseEntity.ok(teamsService.createTeam(teamDto));}


    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/getPlayers/{teamId}")
    public  ResponseEntity<List<UserData>> getPlayers(@PathVariable("teamId")  String teamId){
        return ResponseEntity.ok(teamsService.getTeamPlayers(Integer.parseInt(teamId)));
    }

}
