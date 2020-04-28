package com.service.registration;

import com.dto.UserDto;
import com.dto.MessageDto;
import org.springframework.stereotype.Service;

public interface RegistrationService {
    MessageDto registr(UserDto u);
}
