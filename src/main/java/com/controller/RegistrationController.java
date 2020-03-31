package com.controller;

import com.data.x.U;
import com.data.entity.User;
import com.data.entity.UserData;
import com.data.repository.UserDataRepository;
import com.data.repository.UserRepository;
import com.data.x.UD;
import com.response.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Controller
public class RegistrationController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserDataRepository userDataRepository;

    @RequestMapping("/registration")
    public Message addUser(@RequestBody U u, Map<String, Object> model){
        User userFromDb = userRepository.findByUserLogin(u.getUserLogin());
        if(userFromDb != null){
            model.put("message", "User exist");
            return new Message("failed");
        }
        User newUser = new User(u.getUserLogin(), u.getUserPassword());
        userRepository.save(newUser);
        UserData newUserData = new UserData(newUser.getUserId(), u.getUserFirstName(), u.getUserSecondName(),
                u.getUserGender(), u.getUserCity());
        userDataRepository.save(newUserData);
        model.put("message", "User exist");
        return new Message("successful");
    }


    @PostMapping("/bla")
    public Message signIn(@RequestBody U u) {
        return new Message("SUC");
    }

}
