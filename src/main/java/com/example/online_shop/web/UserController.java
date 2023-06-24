package com.example.online_shop.web;

import com.example.online_shop.model.User;
import com.example.online_shop.model.dto.TokenDTO;
import com.example.online_shop.model.dto.UserDTO;
import com.example.online_shop.model.dto.UserLoginDTO;
import com.example.online_shop.model.dto.UserRegistrationDTO;
import com.example.online_shop.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @DeleteMapping("/users/r/{login}/{password}")
    public void deleteUser(@PathVariable(name = "login") String login,
                           @PathVariable(name = "password") String password) {
        userService.deleteUser(login, password);
    }

    @GetMapping("adm/users")
    public Page<User> getUsers(Pageable pageable) {
        return userService.getUsers(pageable);
    }

    @PutMapping("/users/u")
    public UserDTO updateUser(@RequestBody UserRegistrationDTO userRegistrationDTO, String login) {
        return userService.updateUser(userRegistrationDTO, login);
    }

    @PostMapping("/registration")
    public ResponseEntity<UserDTO> saveUser(@RequestBody @Valid UserRegistrationDTO userRegistrationDTO){
        UserDTO userDTO = userService.saveUser(userRegistrationDTO);
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> loginUser(@RequestBody UserLoginDTO userLoginDTO){
        TokenDTO tokenDTO = userService.createToken(userLoginDTO);
        return ResponseEntity.ok(tokenDTO);
    }
}
