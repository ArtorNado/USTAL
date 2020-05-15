package com.controller;

import com.service.teams.TeamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
public class MainController {

    @Autowired
    TeamsService teamsService;


    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        return "main";
    }

    @RequestMapping(value = { "/playerList" }, method = RequestMethod.GET)
    public String viewPersonList(Model model) {
        model.addAttribute("list", teamsService.getTeams());
        return "playerList";
    }

}
