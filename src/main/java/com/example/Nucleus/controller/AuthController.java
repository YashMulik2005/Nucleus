package com.example.Nucleus.controller;

import com.example.Nucleus.dto.SucessResponseHandler;
import com.example.Nucleus.dto.requestDto.authRequestDtos.LoginRequestDto;
import com.example.Nucleus.dto.requestDto.authRequestDtos.RefreshTokenRequestDto;
import com.example.Nucleus.dto.requestDto.authRequestDtos.SignUpRequestDto;
import com.example.Nucleus.service.impl.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<Object>Signup(@RequestBody SignUpRequestDto signUpRequestDto){
        return SucessResponseHandler.SucessResponseBuilder(HttpStatus.CREATED,
                true,"User created successfully", authService.signUp(signUpRequestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<Object>login(@RequestBody LoginRequestDto loginRequestDto){
        return SucessResponseHandler.SucessResponseBuilder(HttpStatus.OK,
                true,"Login successful.", authService.login(loginRequestDto));
    }

    @PostMapping("/refresh")
    public ResponseEntity<Object>refreshAccessToken(@RequestBody RefreshTokenRequestDto refreshTokenRequestDto){
        return SucessResponseHandler.SucessResponseBuilder(HttpStatus.OK,
                true, "New token generated.", authService.refreshToken(refreshTokenRequestDto.getToken()));
    }
}
