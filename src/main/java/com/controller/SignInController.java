package com.controller;


import com.data.entity.User;
import com.data.entity.UserData;
import com.data.repository.UserDataRepository;
import com.data.repository.UserRepository;
import com.dto.SignInDto;
import com.dto.TokenDto;
import com.dto.UserIdDto;
import com.service.SignInService;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class SignInController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDataRepository userDataRepository;

    @Autowired
    private SignInService signInService;

    @PostMapping("/signIn")
    public ResponseEntity<TokenDto> signIn(@RequestBody SignInDto signInData) {
        return ResponseEntity.ok(signInService.signIn(signInData));
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/user/{user-id}")
    public ResponseEntity<UserData> getUser(@PathVariable("user-id") String userId) {
        System.out.println(userId);
        return ResponseEntity.ok(userDataRepository.findUserDataByUserId(Integer.parseInt(userId)));
    }
}

