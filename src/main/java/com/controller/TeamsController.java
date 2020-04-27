package com.controller;


import com.data.entity.Teams;
import com.data.entity.UserData;
import com.dto.TeamDto;
import com.response.Message;
import com.service.teams.TeamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/getTeams")
    public ResponseEntity<List<Teams>> getTeams(){
        return ResponseEntity.ok(teamsService.getTeams());
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/getTeamsByCity/{city}")
    public ResponseEntity<List<Teams>> getTeamsByTeamCity(@PathVariable("city")  String city){
        return ResponseEntity.ok(teamsService.getTeamsByTeamCity(city));
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/getTeamsByName/{name}")
    public ResponseEntity<List<Teams>> getTeamsByTeamName(@PathVariable("name")  String name){
        return ResponseEntity.ok(teamsService.getTeamsByName(name));
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/getTeamsByStatus/{status}")
    public ResponseEntity<List<Teams>> getTeamsByTeamStatus(@PathVariable("status")  String status){
        return ResponseEntity.ok(teamsService.getTeamsByTeamStatus(status));
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/getTeamsByCityAndStatus")
    public ResponseEntity<List<Teams>> getTeamsByTeamStatus(@RequestParam String city, String status){
        return ResponseEntity.ok(teamsService.getTeamsByCityAndStatus(city, status));
    }


}
