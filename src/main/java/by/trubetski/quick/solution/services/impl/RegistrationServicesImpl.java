package by.trubetski.quick.solution.services.impl;

import by.trubetski.quick.solution.exception.EmailAlreadyExistsException;
import by.trubetski.quick.solution.exception.UsernameAlreadyExistsException;
import by.trubetski.quick.solution.exception.ValidationException;
import by.trubetski.quick.solution.models.User;
import by.trubetski.quick.solution.repositories.UserRepositories;
import by.trubetski.quick.solution.services.RegistrationServices;
import by.trubetski.quick.solution.services.ValidationServices;
import by.trubetski.quick.solution.util.enums.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class RegistrationServicesImpl implements RegistrationServices {

    public static final String EXCEPTION_SAVING_USER = "Exception saving user";
    private final UserRepositories userRepositories;
    private final PasswordEncoder passwordEncoder;
    private final ValidationServices validationServices;

    @Override
    @Transactional
    public void createUser(User user) {
        String role = "User";
        duplicateCheck(user);
        save(user, role);
    }

    @Transactional
    public void createCourier(User user, String role){
        BindingResult bindingResult = validationServices.validate(user);
        if (bindingResult.hasErrors()){
            throw new ValidationException("error of validation");
        }
        duplicateCheck(user);
        save(user, role);
    }
    public void save(User user, String role){
        try {
           if (role.equals("User")){
               user.setRole(Role.ROLE_USER.getRoleName());
           } else if (role.equals("Admin")) {
               user.setRole(Role.ROLE_ADMIN.getRoleName());
           } else {
               user.setRole(Role.ROLE_COURIER.getRoleName());
           }
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepositories.save(user);
            log.info("User saved successfully: " + user.getUsername());
        } catch (Exception e) {
            log.error(EXCEPTION_SAVING_USER, e);
            throw new RuntimeException(EXCEPTION_SAVING_USER, e);
        }
    }
    public void duplicateCheck(User user){
        if (userRepositories.findByUsername(user.getUsername()).isPresent()) {
            throw new UsernameAlreadyExistsException("Username already exists");
        }

        if (userRepositories.findByEmail(user.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email already exists");
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
