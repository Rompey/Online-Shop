package com.example.online_shop.web;

import com.example.online_shop.model.dto.UserDTO;
import com.example.online_shop.model.dto.UserRegistrationDTO;
import com.example.online_shop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @DeleteMapping("/users/r/{login}")
    public void deleteUser(@PathVariable(name = "login") String login) {
        userService.deleteUser(login);
    }

    @GetMapping("adm/users")
    public Page<UserDTO> getUsers(Pageable pageable) {
        return userService.getUsers(pageable);
    }

    @PutMapping("/users/u")
    public UserDTO updateUser(@RequestBody UserRegistrationDTO userRegistrationDTO, String login) {
        return userService.updateUser(userRegistrationDTO, login);
    }
}
