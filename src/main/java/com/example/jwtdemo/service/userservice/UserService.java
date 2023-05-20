package com.example.jwtdemo.service.userservice;

import com.example.jwtdemo.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User register(User user);

    List<User> getAll();

    Optional<User> findByUsername(String username);

    Optional<User> findById(Long id);

    Optional<User> findOnlyUsersById(Long id);

    void delete(Long id);
}
