package com.controller;


import com.dto.SignInDto;
import com.dto.TokenDto;
import com.dto.UserIdDto;
import com.service.signIn.SignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

@RestController
public class SignInController {

    @Autowired
    private SignInService signInService;

/*
    @Autowired
    private UserDataRepository userDataRepository;
*/

    @PostMapping("/signIn")
    @RequestScope
    public ResponseEntity<TokenDto> signIn(@RequestBody SignInDto signInData) {
        return ResponseEntity.ok(signInService.signIn(signInData));
    }

    @PreAuthorize("isAuthenticated()")
    @RequestScope
    @GetMapping("/userId/{user-login}")
    public ResponseEntity<UserIdDto> getUserByLogin(@PathVariable("user-login")  String userLogin){
        return ResponseEntity.ok(signInService.getUserId(userLogin));
    }


  /*  @PreAuthorize("isAuthenticated()")
    @GetMapping("/user/{user-id}")
    public ResponseEntity<UserData> getUser(@PathVariable("user-id") String userId) {
        System.out.println(userId);
        return ResponseEntity.ok(userDataRepository.findUserDataByUserId(Integer.parseInt(userId)));
    }*/
}

