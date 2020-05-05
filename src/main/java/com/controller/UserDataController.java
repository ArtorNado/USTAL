package com.controller;

import com.aspect.LogExecutionTime;
import com.dto.UserDataDto;
import com.models.UserData;
import com.repository.UserDataRepository;
import com.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

@RestController
public class UserDataController {

    @Autowired
    private UserService userService;

    @PreAuthorize("isAuthenticated()")
    @RequestScope
    @GetMapping("/user/{user-id}")
    public ResponseEntity<UserData> getUser(@PathVariable("user-id") Integer userId) {
        return ResponseEntity.ok(userService.getUserData(userId));
    }
}
