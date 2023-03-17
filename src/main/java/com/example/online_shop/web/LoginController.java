package com.example.online_shop.web;

import com.example.online_shop.model.dto.TokenDTO;
import com.example.online_shop.model.dto.UserLoginDTO;
import com.example.online_shop.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping
    public ResponseEntity<TokenDTO> loginUser(@RequestBody UserLoginDTO userLoginDTO){
        TokenDTO tokenDTO = loginService.createToken(userLoginDTO);
        return ResponseEntity.ok(tokenDTO);
    }
}
