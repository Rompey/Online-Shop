package com.example.online_shop.service;

import com.example.online_shop.model.User;
import com.example.online_shop.model.dto.UserDTO;
import com.example.online_shop.model.dto.UserRegistrationDTO;
import com.example.online_shop.repository.RoleRepository;
import com.example.online_shop.repository.UserRepository;
import com.example.online_shop.utils.ArgonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Transactional
    public UserDTO saveUser(UserRegistrationDTO userRegistrationDTO) {
        User save = userRepository.save(buildUser(userRegistrationDTO));

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
}
