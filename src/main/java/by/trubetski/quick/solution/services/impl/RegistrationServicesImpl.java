package by.trubetski.quick.solution.services.impl;

import by.trubetski.quick.solution.exception.EmailAlreadyExistsException;
import by.trubetski.quick.solution.exception.UsernameAlreadyExistsException;
import by.trubetski.quick.solution.models.User;
import by.trubetski.quick.solution.repositories.UserRepositories;
import by.trubetski.quick.solution.services.RegistrationServices;
import by.trubetski.quick.solution.util.enums.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collections;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class RegistrationServicesImpl implements RegistrationServices {

    private final UserRepositories userRepositories;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void createUser(User user) {
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
    @Override
    public Optional<User> getPersonByUsername(String username) {
        return userRepositories.findByUsername(username);
    }
    @Override
    public Optional<User> getPersonByEmail(String email) {
        return userRepositories.findByEmail(email);
    }
}
