package com.example.jwtdemo.rest;

import com.example.jwtdemo.dto.AuthenticationRequestDto;
import com.example.jwtdemo.dto.TokenResponseDto;
import com.example.jwtdemo.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationRestControllerV1 {
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login(@RequestBody AuthenticationRequestDto requestDto) {
        return authenticationService.login(requestDto.username(), requestDto.password());
    }
}
