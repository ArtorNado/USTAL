package com.controller;

import com.data.entity.User;
import com.data.repository.UserRepository;
import com.response.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Controller
public class RegistrationController {

    @Autowired
    UserRepository userRepository;

    /*@RequestMapping("/registration")
    public Message addUser(@RequestParam String login, @RequestParam String password, Map<String, Object> model){
        User userFromDb = userRepository.findByUserLogin(login);
        if(userFromDb != null){
            model.put("message", "User exist");
            System.out.println("failed");
            return new Message("failed");
        }
        System.out.println(login);
        System.out.println(password);
        User u = new User(login, password);
        userRepository.save(u);
        model.put("message", "User exist");
        return new Message("successful");
    }*/

    @RequestMapping("/registration")
    public Message addUser(User user, Map<String, Object> model){
        User userFromDb = userRepository.findByUserLogin(user.getUserLogin());
        if(userFromDb != null){
            model.put("message", "User exist");
            System.out.println("failed");
            return new Message("failed");
        }
        System.out.println(user.getUserLogin());
        System.out.println(user.getUserPassword());
        User u = new User(user.getUserLogin(), user.getUserPassword());
        userRepository.save(u);
        model.put("message", "User exist");
        return new Message("successful");
    }

    @RequestMapping(value = "/bla", method = RequestMethod.POST)
    public ResponseEntity<User> update(@RequestBody User user) {

        if (user != null) {
            System.out.println("userLog");
            System.out.println(user.getUserLogin());
        }
        System.out.println("NULL");
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
}
