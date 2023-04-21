package com.example.online_shop.service;

import com.example.online_shop.model.User;
import com.example.online_shop.model.dto.TokenDTO;
import com.example.online_shop.model.dto.UserLoginDTO;
import com.example.online_shop.repository.UserRepository;
import com.example.online_shop.utils.ArgonUtil;
import com.example.online_shop.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;

    public TokenDTO createToken(UserLoginDTO userLoginDTO) {
        User user = getUserIfLoginAndPasswordAreCorrect(userLoginDTO.login(), userLoginDTO.password());
        String token = JwtUtil.generateToken(buildClaims(user), user.getLogin());

        return new TokenDTO(token, JwtUtil.expiredTime(token));
    }

    private Map<String, Object> buildClaims(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("login", user.getLogin());
        return claims;
    }

    private User getUserIfLoginAndPasswordAreCorrect(String login, String password) {
        Optional<User> optionalUser = userRepository.findUserByLoginOptional(login);

        optionalUser.ifPresent(user -> {
            boolean equals = ArgonUtil.matches(password, user.getPassword());
            if (!equals) {
                throw new AccessDeniedException("Password is incorrect");
            }
        });
        return optionalUser.orElseThrow(() -> new AccessDeniedException("Login is incorrect"));
    }
}
