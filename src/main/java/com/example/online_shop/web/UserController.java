package com.example.online_shop.web;

import com.example.online_shop.mappers.UserMapper;
import com.example.online_shop.model.User;
import com.example.online_shop.model.dto.UserDTO;
import com.example.online_shop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @DeleteMapping
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @GetMapping
    public Page<UserDTO> getUsers(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        return UserMapper.USER_MAPPER.map(users);
    }
}
