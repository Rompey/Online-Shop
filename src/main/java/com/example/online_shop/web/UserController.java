package com.example.online_shop.web;

import com.example.online_shop.model.User;
import com.example.online_shop.model.dto.UserDTO;
import com.example.online_shop.model.dto.UserRegistrationDTO;
import com.example.online_shop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @DeleteMapping("/r/")
    public void deleteUser(User user) {
        userService.deleteUser(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public Page<UserDTO> getUsers(Pageable pageable) {
        return userService.getUsers(pageable);
    }

    @PutMapping
    public UserDTO updateUser(UserRegistrationDTO userRegistrationDTO, String login){
        return userService.updateUser(userRegistrationDTO, login);
    }
}
