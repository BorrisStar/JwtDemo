package com.example.jwtdemo.repository;

import com.example.jwtdemo.model.Role;
import com.example.jwtdemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String name);

    Optional<User> findUserByIdAndRolesNotContaining(Long id, Role role);
}
