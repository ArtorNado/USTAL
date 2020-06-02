package com.service.user;

import com.dto.EditDataDto;
import com.models.UserData;

public interface UserService {

    UserData getUserData(Integer userId);

    void editData(EditDataDto editDataDto);

    void editPassword(Integer id, String currentPassword, String newPassword);

}
