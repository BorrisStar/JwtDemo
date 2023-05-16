package com.example.jwtdemo.repository;

import com.example.jwtdemo.model.Role;
import com.example.jwtdemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String name);

    User findUserByIdAndRolesNotContaining(Long id, Role role);
}
