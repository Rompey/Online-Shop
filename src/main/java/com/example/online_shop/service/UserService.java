package com.example.online_shop.service;

import com.example.online_shop.model.User;
import com.example.online_shop.model.dto.TokenDTO;
import com.example.online_shop.model.dto.UserDTO;
import com.example.online_shop.model.dto.UserLoginDTO;
import com.example.online_shop.model.dto.UserRegistrationDTO;
import com.example.online_shop.repository.RoleRepository;
import com.example.online_shop.repository.UserRepository;
import com.example.online_shop.utils.ArgonUtil;
import com.example.online_shop.utils.JwtUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Transactional
    public void deleteUser(String login, String password) {
        User user = getUserIfLoginAndPasswordAreCorrect(login, password);
        userRepository.deleteById(user.getId());
    }

    public Page<User> getUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public Page<User> getUsersByRoleName(String roleName, Pageable pageable) {
        return userRepository.findUsersByRoleName(roleName, pageable);
    }

    @Transactional
    public void updateUser(String login, String password, UserRegistrationDTO userRegistrationDTO) {
        User user = getUserIfLoginAndPasswordAreCorrect(login, password);
        userRepository.changeUserCredentials(
                userRegistrationDTO.name(),
                userRegistrationDTO.login(),
                ArgonUtil.hashPassword(userRegistrationDTO.password()),
                user.getId());
    }


    public UserDTO saveUser(UserRegistrationDTO userRegistrationDTO) {
        User save = userRepository.save(buildUser(userRegistrationDTO));

        return getUserDTO(save);
    }

    public TokenDTO createToken(UserLoginDTO userLoginDTO) {
        User user = getUserIfLoginAndPasswordAreCorrect(userLoginDTO.login(), userLoginDTO.password());
        String token = JwtUtil.generateToken(buildClaims(user), user.getLogin());

        return new TokenDTO(token, JwtUtil.expiredTime(token));
    }

    protected User getUserByNameAndLogin(String name, String login) {
        return userRepository.findUserByNameAndLogin(name, login);
    }

    private UserDTO getUserDTO(User save) {
        return new UserDTO(save.getName(), save.getLogin());
    }

    private User buildUser(UserRegistrationDTO userDTO) {
        return User.builder()
                .name(userDTO.name())
                .login(userDTO.login())
                .password(ArgonUtil.hashPassword(userDTO.password()))
                .role(roleRepository.findRoleByName("USER"))
                .build();
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
            boolean equals = ArgonUtil.matchesUserPassword(password, user.getPassword());
            if (!equals) {
                throw new AccessDeniedException("Password is incorrect");
            }
        });
        return optionalUser.orElseThrow(() -> new AccessDeniedException("Login is incorrect"));
    }
}
