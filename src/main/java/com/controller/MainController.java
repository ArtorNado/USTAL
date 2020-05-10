package com.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class MainController {

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        return "main";
    }

}
