package by.trubetski.quick.solution.services;

import by.trubetski.quick.solution.exception.EmailAlreadyExistsException;
import by.trubetski.quick.solution.exception.UsernameAlreadyExistsException;
import by.trubetski.quick.solution.models.User;
import by.trubetski.quick.solution.repositories.UserRepositories;
import by.trubetski.quick.solution.util.Role;
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

    /**
     *The method will check for the absence of duplicate email and name in the database. Additionally,
     * it hashes the password and saves it when all user conditions are met for storing in the database.
     * Also add roles user.
     *
     * @param user The User entity to be created.
     * @throws UsernameAlreadyExistsException if the username already exists in the database.
     * @throws EmailAlreadyExistsException    if the email already exists in the database.
     * @throws RuntimeException              if an exception occurs while saving the user.
     */
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
    public Optional<User> getPersonByUsername(String username) {
        return userRepositories.findByUsername(username);
    }
    public Optional<User> getPersonByEmail(String email) {
        return userRepositories.findByEmail(email);
    }

}
