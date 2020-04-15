package com.service.signIn;

import com.dto.SignInDto;
import com.dto.TokenDto;
import com.dto.UserIdDto;
import org.springframework.stereotype.Service;

@Service
public interface SignInService {
    TokenDto signIn(SignInDto signInData);

    UserIdDto getUserId(String userLogin);
}

