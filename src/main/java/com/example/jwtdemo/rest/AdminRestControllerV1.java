package com.example.jwtdemo.rest;

import com.example.jwtdemo.dto.AdminUserDto;
import com.example.jwtdemo.model.User;
import com.example.jwtdemo.service.userservice.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/admin")
@RequiredArgsConstructor
public class AdminRestControllerV1 {
    private final UserService userService;

    @GetMapping(value = "/users/{id}")
    public ResponseEntity<AdminUserDto> getUserById(@PathVariable(name = "id") Long id) {
        Optional<User> user = userService.findById(id);

        if (user.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(AdminUserDto.fromUser(user.get()), HttpStatus.OK);
    }
}
