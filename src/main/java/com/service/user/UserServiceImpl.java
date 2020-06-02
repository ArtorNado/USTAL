package com.service.user;

import com.aspect.LogExecutionTime;
import com.dto.EditDataDto;
import com.dto.UserDataDto;
import com.models.User;
import com.models.UserData;
import com.repository.UserDataRepository;
import com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Scope(scopeName = "tenant")
public class UserServiceImpl implements UserService {

    @Autowired
    UserDataRepository userDataRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    @LogExecutionTime
    public UserData getUserData(Integer userId) {
        Optional<UserData> userDataFromDb = userDataRepository.findUserDataByUserId(userId);
        if (userDataFromDb.isPresent()) return userDataFromDb.get();
        else throw new AccessDeniedException("User not found");
    }

    @Override
    public void editData(EditDataDto editDataDto) {
        Optional<UserData> userDataFromDb = userDataRepository.findUserDataByUserId(editDataDto.getUserId());
        if(userDataFromDb.isPresent()){
            if(!editDataDto.getUserFirstName().equals("")) userDataFromDb.get().setUserFirstName(editDataDto.getUserFirstName());
            if(!editDataDto.getUserSecondName().equals("")) userDataFromDb.get().setUserSecondName(editDataDto.getUserSecondName());
            if(!editDataDto.getUserCity().equals("")) userDataFromDb.get().setUserCity(editDataDto.getUserCity());
            System.out.println(userDataFromDb.get().getUserFirstName());
            userDataRepository.save(userDataFromDb.get());
        }
    }

    @Override
    public void editPassword(Integer id, String currentPassword, String newPassword) {
        Optional<User> userFromDb = userRepository.findUserByUserId(id);
        if(userFromDb.isPresent()){
            if(currentPassword.equals(userFromDb.get().getUserPassword())){
                userFromDb.get().setUserPassword(newPassword);
                userRepository.save(userFromDb.get());
            } else throw new IllegalArgumentException("Not valid current password");
        }
    }

}
