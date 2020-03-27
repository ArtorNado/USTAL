package com.controller;

import com.data.entity.User;
import com.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class RegistrationController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("registration")
    public String openRegistrPage(){
        return "registration";
    }

    @GetMapping("/registration")
    public String addUser(@RequestParam String login, @RequestParam String password, Map<String, Object> model){
        User userFromDb = userRepository.findByUserLogin(login);
        if(userFromDb != null){
            model.put("message", "User exist");
            return "/registration";
        }
        System.out.println(login);
        System.out.println(password);
        User u = new User(login, password);
        userRepository.save(u);
        return "redirect:/login";
    }
}
