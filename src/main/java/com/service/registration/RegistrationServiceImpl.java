package com.service.registration;

import com.data.entity.User;
import com.data.entity.UserData;
import com.data.repository.UserDataRepository;
import com.data.repository.UserRepository;
import com.data.x.U;
import com.response.Message;
import com.service.registration.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDataRepository userDataRepository;

    @Override
    public Message registr(U u) {
        Optional<User> userFromDb = userRepository.findByUserLogin(u.getUserLogin());
        if(userFromDb.isPresent()){
            return new Message("Этот логин уже существует");
        }
        User newUser = new User(u.getUserLogin(), u.getUserPassword());
        userRepository.save(newUser);
        UserData newUserData = new UserData(newUser.getUserId(), u.getUserFirstName(), u.getUserSecondName(),
                u.getUserGender(), u.getUserCity(), null);
        userDataRepository.save(newUserData);
        return new Message("success");
    }
}
