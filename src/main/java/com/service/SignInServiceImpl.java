package com.service;

import com.data.entity.User;
import com.data.repository.UserRepository;
import com.dto.SignInDto;
import com.dto.TokenDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class SignInServiceImpl implements SignInService {

    @Autowired
    private UserRepository userRepository;

    @Value("${jwt.secret}")
    private String secret;

    @Override
    public TokenDto signIn(SignInDto signInData) {
        // получаем пользователя по его email
        Optional<User> userOptional = userRepository.findByUserLogin(signInData.getUserLogin());
        // если у меня есть этот пользвователь
        if (userOptional.isPresent()) {
            // получаем его
            User user = userOptional.get();
            // если пароль подходит
                // создаем токен
                String token = Jwts.builder()
                        .setSubject(user.getUserId().toString()) // id пользователя
                        .claim("name", user.getUserLogin()) // имя
                        /*.claim("role", user.getRole().name()) // роль*/
                        .signWith(SignatureAlgorithm.HS256, secret) // подписываем его с нашим secret
                        .compact(); // преобразовали в строку
                return new TokenDto(token);
        } else throw new AccessDeniedException("User not found");
    }
}

