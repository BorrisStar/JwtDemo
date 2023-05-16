package com.example.jwtdemo.service.userservice;

import com.example.jwtdemo.model.User;

import java.util.List;

public interface UserService {

    User register(User user);

    List<User> getAll();

    User findByUsername(String username);

    User findById(Long id);

    User findOnlyUsersById(Long id);

    void delete(Long id);
}
