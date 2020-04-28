package com.controller;

import com.dto.UserDto;
import com.repository.UserDataRepository;
import com.repository.UserRepository;
import com.dto.MessageDto;
import com.service.registration.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDataRepository userDataRepository;

    @Autowired
    private RegistrationService registrationService;

    @RequestMapping("/registration")
    @RequestScope
    public ResponseEntity<MessageDto> registration(@RequestBody UserDto u){
        return ResponseEntity.ok(registrationService.registr(u));    }
}
