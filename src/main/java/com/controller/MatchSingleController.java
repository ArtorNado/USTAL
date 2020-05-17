package com.controller;

import com.dto.MatchSingleDto;
import com.dto.MessageDto;
import com.models.MatchSingle;
import com.service.match.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MatchSingleController {

    @Autowired
    MatchService matchService;

    @RequestMapping("/createMatch")
    public ResponseEntity<MessageDto> createMatch(@RequestBody MatchSingleDto matchSingle) {
        return ResponseEntity.ok(matchService.createSingleMatch(matchSingle));
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/endSingleMatch/{id}")
    public ResponseEntity<MessageDto> getNotificationByRecipientId(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(matchService.endSingleMatch(id));
    }

    @RequestMapping("/joinSingleMatch")
    public ResponseEntity<MessageDto> joinSingleMatch(@RequestParam(value="idSingleMatch", required=false)Integer idSingleMatch,
                                                      @RequestParam(value="participant", required=false) Integer participant) {
        return ResponseEntity.ok(matchService.joinSingleMatch(idSingleMatch, participant));
    }

    @RequestMapping("/getAllSingleMatch")
    public ResponseEntity<List<MatchSingle>> getAllSingleMatch() {
        return ResponseEntity.ok(matchService.getAllMatch());
    }

    @RequestMapping("/getSingleMatchByRole")
    public ResponseEntity<List<MatchSingle>> getByRole(@RequestParam(value="userId", required=false)Integer userId,
                                                      @RequestParam(value="role", required=false) String role) {
        return ResponseEntity.ok(matchService.getSingleMatchByRole(userId, role));
    }
}
