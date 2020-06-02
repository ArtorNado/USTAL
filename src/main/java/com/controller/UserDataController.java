package com.controller;

import com.dto.EditDataDto;
import com.models.UserData;
import com.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
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

    @RequestScope
    @RequestMapping("/editData")
    public void editData(@RequestBody EditDataDto editDataDto) {
        userService.editData(editDataDto);
    }

    @RequestMapping("/changePassword")
    public void changePassword(@RequestParam(value = "userId", required = false) Integer userId,
                               @RequestParam(value = "currentPassword", required = false) String currentPassword,
                               @RequestParam(value = "newPassword", required = false) String newPassword) {
        userService.editPassword(userId, currentPassword, newPassword);
    }
}
