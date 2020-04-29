package com.service.user;

import com.dto.UserDataDto;
import com.models.UserData;
import com.repository.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDataRepository userDataRepository;

    @Override
    public UserData getUserData(Integer userId) {
        Optional<UserData> userDataFromDb = userDataRepository.findUserDataByUserId(userId);
        if (userDataFromDb.isPresent()) return userDataFromDb.get();
        else throw new AccessDeniedException("User not found");
    }
}
