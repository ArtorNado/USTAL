package com.controller;

import com.models.UserData;
import com.repository.UserDataRepository;
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
    private UserDataRepository userDataRepository;

    @PreAuthorize("isAuthenticated()")
    @RequestScope
    @GetMapping("/user/{user-id}")
    public ResponseEntity<UserData> getUser(@PathVariable("user-id") String userId) {
        return ResponseEntity.ok(userDataRepository.findUserDataByUserId(Integer.parseInt(userId)));
    }
}
