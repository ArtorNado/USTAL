package com.controller;


import com.dto.StatusDto;
import com.models.Teams;
import com.models.UserData;
import com.dto.TeamDto;
import com.dto.MessageDto;
import com.service.teams.TeamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TeamsController {

    @Autowired
    TeamsService teamsService;

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/createTeam")
    public ResponseEntity<MessageDto> createTeam(@RequestBody TeamDto teamDto){
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

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/getTeam/{teamId}")
    public ResponseEntity<Teams> getTeamByTeamId(@PathVariable("teamId")  Integer teamId){
        return ResponseEntity.ok(teamsService.getTeamById(teamId));
    }

    @RequestMapping("/determineStatus")
    public ResponseEntity<StatusDto> determineUserStatusInTeam(@RequestParam Integer userId, Integer teamId){
        return ResponseEntity.ok(teamsService.determineUserStatusInTeam(userId, teamId));
    }


}
