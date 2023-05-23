package com.example.jwtdemo.service;

import com.example.jwtdemo.dto.TokenResponseDto;
import com.example.jwtdemo.model.User;
import com.example.jwtdemo.security.jwt.JwtTokenProvider;
import com.example.jwtdemo.service.userservice.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    public ResponseEntity<TokenResponseDto> login(String username, String password) {
        try {
            Optional<User> user = userService.findByUsername(username);
            if (user.isEmpty()) {
                throw new UsernameNotFoundException("User with username: " + username + " not found");
            }
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            String token = jwtTokenProvider.generateToken(username, user.get().getRoles());

            return ResponseEntity.ok(new TokenResponseDto(username, token));
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
