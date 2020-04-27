package com.controller;

import com.data.x.U;
import com.data.repository.UserDataRepository;
import com.data.repository.UserRepository;
import com.response.Message;
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
    public ResponseEntity<Message> registration(@RequestBody U u){
        return ResponseEntity.ok(registrationService.registr(u));    }
}
