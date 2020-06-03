package com.service.registration;

import com.aspect.LogExecutionTime;
import com.models.Role;
import com.models.User;
import com.models.UserData;
import com.repository.UserDataRepository;
import com.repository.UserRepository;
import com.dto.UserDto;
import com.dto.MessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Scope(scopeName = "tenant")
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDataRepository userDataRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @LogExecutionTime
    public MessageDto registr(UserDto u) {
        Optional<List<User>> userFromDb = userRepository.getUserByUserLogin(u.getUserLogin());
        if (userFromDb.isPresent()) {
            throw new IllegalArgumentException("Этот логин уже существует");
        } else {
            User newUser = new User(u.getUserLogin(), u.getUserPassword(), Role.USER);
            userRepository.save(newUser);
            UserData newUserData = new UserData(newUser.getUserId(), u.getUserFirstName(), u.getUserSecondName(),
                    u.getUserGender(), u.getUserCity(), null);
            userDataRepository.save(newUserData);
            return new MessageDto("success");
        }
    }
}
