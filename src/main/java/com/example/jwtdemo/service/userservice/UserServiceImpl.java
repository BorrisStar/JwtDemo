package com.example.jwtdemo.service.userservice;

import com.example.jwtdemo.model.Role;
import com.example.jwtdemo.model.Status;
import com.example.jwtdemo.model.User;
import com.example.jwtdemo.repository.RoleRepository;
import com.example.jwtdemo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public User register(User user) {
        Role roleUser = roleRepository.findByName("ROLE_USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);
        user.setStatus(Status.ACTIVE);

        User registeredUser = userRepository.save(user);

        log.info("IN register - user: {} successfully registered", registeredUser);

        return registeredUser;
    }

    @Override
    public List<User> getAll() {
        List<User> result = userRepository.findAll();
        log.info("IN getAll - {} users found", result.size());
        return result;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        Optional<User> result = userRepository.findByUsername(username);
        log.info("IN findByUsername - user: {} found by username: {}", result, username);
        return result;
    }

    @Override
    public Optional<User> findById(Long id) {
        Optional<User> result = userRepository.findById(id);

        if (result.isEmpty()) {
            log.error("IN findById - no user found by id: {}", id);
            return result;
        }

        log.info("IN findById - user: {} found by id: {}", result.get(), id);
        return result;
    }

    @Override
    public Optional<User> findOnlyUsersById(Long id) {
        Role role = roleRepository.findByName("ROLE_ADMIN");
        Optional<User> result = userRepository.findUserByIdAndRolesNotContaining(id, role);

        if (result.isEmpty()) {
            log.error("IN findById - no user found by id: {}", id);
            return result;
        }

        log.info("IN findById - user: {} found by id: {}", result.get(), id);
        return result;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
        log.info("IN delete - user with id: {} successfully deleted", id);
    }
}
