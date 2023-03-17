package com.example.online_shop.web;

import com.example.online_shop.model.dto.UserDTO;
import com.example.online_shop.model.dto.UserRegistrationDTO;
import com.example.online_shop.service.RegistrationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;

    @PostMapping()
    public ResponseEntity<UserDTO> saveUser(@RequestBody @Valid UserRegistrationDTO userRegistrationDTO){
        UserDTO userDTO = registrationService.saveUser(userRegistrationDTO);
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }
}
