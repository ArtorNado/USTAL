package com.service;

import com.dto.SignInDto;
import com.dto.TokenDto;

public interface SignInService {
    TokenDto signIn(SignInDto signInData);
}

