package by.trubetski.quickSolution.services;

import by.trubetski.quickSolution.exception.EmailAlreadyExistsException;
import by.trubetski.quickSolution.exception.UsernameAlreadyExistsException;
import by.trubetski.quickSolution.models.User;
import by.trubetski.quickSolution.repositories.UserRepositories;
import by.trubetski.quickSolution.util.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
@Slf4j
public class RegistrationServices {
    private final UserRepositories userRepositories;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationServices(UserRepositories userRepositories, PasswordEncoder passwordEncoder) {
        this.userRepositories = userRepositories;
        this.passwordEncoder = passwordEncoder;
    }
    @Transactional
    public void createUser(User user) {
        // в строках 33-39 мне кажется я дублирую код находящийся в UserValidator
        if (userRepositories.findByUsername(user.getUsername()).isPresent()) {
            throw new UsernameAlreadyExistsException("Username already exists");
        }

        if (userRepositories.findByEmail(user.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email already exists");
        }
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setType(Collections.singleton(Role.ROLE_USER.getRoleName()).toString());
            userRepositories.save(user);
            log.info("User saved successfully: " + user.getUsername());
        } catch (Exception e) {
            log.error("Exception saving user", e);
            throw new RuntimeException("Exception saving user", e);
        }
    }
    public Optional<User> getPersonByUsername(String username) {
        return userRepositories.findByUsername(username);
    }
    public Optional<User> getPersonByEmail(String email) {
        return userRepositories.findByEmail(email);
    }

}
