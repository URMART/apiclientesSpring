package com.api_clientes.app.security.controller;

import com.api_clientes.app.security.authCredentials.AuthCredentials;
import com.api_clientes.app.security.dto.LoginResponseDto;
import com.api_clientes.app.security.login.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody AuthCredentials authCredentials) {
        return LoginResponseDto
                .builder()
                .token(loginService.login(authCredentials))
                .build();
    }
}