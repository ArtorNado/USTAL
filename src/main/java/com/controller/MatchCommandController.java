package com.controller;

import com.dto.MatchCommandDto;
import com.dto.MessageDto;
import com.models.MatchCommand;
import com.models.MatchSingle;
import com.service.match.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
