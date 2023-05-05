package com.example.online_shop.service;

import com.example.online_shop.mappers.UserMapper;
import com.example.online_shop.model.User;
import com.example.online_shop.model.dto.UserDTO;
import com.example.online_shop.model.dto.UserRegistrationDTO;
import com.example.online_shop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public void deleteUser(String login) {
        User user = userRepository.findUserByLoginOptional(login)
                        .orElseThrow(() -> new UsernameNotFoundException("This login doesn't exist"));
        userRepository.deleteById(user.getId());
    }


    public Page<UserDTO> getUsers(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        return UserMapper.USER_MAPPER.map(users);
    }


    public UserDTO updateUser(UserRegistrationDTO userDTO, String login) {
        return userRepository.findUserByLoginOptional(login)
                .map(user -> {
                    user.setName(userDTO.name());
                    user.setLogin(userDTO.login());
                    user.setPassword(userDTO.password());
                    User save = userRepository.save(user);
                    return UserMapper.USER_MAPPER.map(save);
                }).orElseThrow(() -> new NotFoundException("User not found"));
    }
}
