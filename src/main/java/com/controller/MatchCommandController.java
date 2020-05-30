package com.controller;

import com.dto.MatchCommandDto;
import com.dto.MessageDto;
import com.models.EndedCommandMatch;
import com.models.MatchCommand;
import com.models.MatchSingle;
import com.service.match.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MatchCommandController {


    @Autowired
    MatchService matchService;

    @RequestMapping("/createCommandMatch")
    public ResponseEntity<MessageDto> createCommandMatch(@RequestBody MatchCommandDto matchCommand) {
        return ResponseEntity.ok(matchService.createCommandMatch(matchCommand));
    }

    @RequestMapping("/joinCommandMatch")
    public ResponseEntity<MessageDto> joinCommandMatch(@RequestParam(value="idCommandMatch", required=false)Integer idCommandMatch,
                                                      @RequestParam(value="recipient", required=false) Integer recipient) {
        return ResponseEntity.ok(matchService.joinCommandMatch(idCommandMatch, recipient));
    }

    @RequestMapping("/getAllCommandMatch")
    public ResponseEntity<List<MatchCommand>> getAllCommandMatch() {
        return ResponseEntity.ok(matchService.getAllCommandMatch());
    }

    @RequestMapping("/endCommandMatch")
    public ResponseEntity<MessageDto> joinCommandMatch(@RequestParam(value="matchId", required=false)Integer matchId,
                                                       @RequestParam(value="firstTeamScore", required=false) Integer firstTeamScore,
                                                       @RequestParam(value="secondTeamsScore", required=false) Integer secondTeamsScore) {
        return ResponseEntity.ok(matchService.endCommandMatch(matchId, firstTeamScore, secondTeamsScore));
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/getEndedCommandMatches")
    public ResponseEntity<List<EndedCommandMatch>> getEndedCommandMatch(@RequestParam(value="teamId", required=false)Integer teamId) {
        return ResponseEntity.ok(matchService.getEndedCommandMatch(teamId));
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/getCommandMatches")
    public ResponseEntity<List<MatchCommand>> getEndedCommandMatches(@RequestParam(value="teamId", required=false)Integer teamId) {
        return ResponseEntity.ok(matchService.getAllMatchesTeam(teamId));
    }

    @RequestMapping("/getCommandMatchByRoleAndCity")
    public ResponseEntity<List<MatchCommand>> getCommandMatchByRoleAndCity(@RequestParam(value="userId", required=false)Integer userId,
                                                       @RequestParam(value="role", required=false) String role,
                                                       @RequestParam(value="city", required=false) String city) {
        return ResponseEntity.ok(matchService.getCommandMatchByRoleAndCity(userId, role, city));
    }

    @RequestMapping("/getAllCommandMatchByCity")
    public ResponseEntity<List<MatchCommand>> getAllCommandMatchByCity(@RequestParam(value="city", required=false) String city) {
        return ResponseEntity.ok(matchService.getAllCommandMatchByCity(city));
    }

    @RequestMapping("/deleteAll")
    public void deleteAll() {
        matchService.deleteAll();
    }
}
