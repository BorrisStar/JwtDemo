package com.example.jwtdemo.rest;

import com.example.jwtdemo.dto.UserDto;
import com.example.jwtdemo.exception.UserNotFoundException;
import com.example.jwtdemo.model.User;
import com.example.jwtdemo.service.userservice.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/users")
@RequiredArgsConstructor
public class UserRestControllerV1 {
    private final UserService userService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") Long id) {
        Optional<User> user = userService.findOnlyUsersById(id);

        if (user.isEmpty()) {
            throw new UserNotFoundException("User " + id + " not found");
        }

        return new ResponseEntity<>(UserDto.fromUser(user.get()), HttpStatus.OK);
    }
}
