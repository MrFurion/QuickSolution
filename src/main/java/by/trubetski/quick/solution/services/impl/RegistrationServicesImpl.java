package by.trubetski.quick.solution.services.impl;

import by.trubetski.quick.solution.exception.EmailAlreadyExistsException;
import by.trubetski.quick.solution.exception.UsernameAlreadyExistsException;
import by.trubetski.quick.solution.exception.ValidationException;
import by.trubetski.quick.solution.models.CourierInf;
import by.trubetski.quick.solution.models.User;
import by.trubetski.quick.solution.repositories.CourierInfoRepositories;
import by.trubetski.quick.solution.repositories.UserRepositories;
import by.trubetski.quick.solution.services.RegistrationServices;
import by.trubetski.quick.solution.services.ValidationServices;
import by.trubetski.quick.solution.util.enums.CourierStatus;
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
    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_COURIER = "ROLE_COURIER";
    private final UserRepositories userRepositories;
    private final PasswordEncoder passwordEncoder;
    private final ValidationServices validationServices;
    private final CourierInfoRepositories courierInfoRepositories;

    @Override
    @Transactional
    public void createUser(User user) {
        String role = Role.ROLE_USER.getRoleName();
        duplicateCheck(user);
        save(user, role);
    }

    @Transactional
    public void createUserByRole(User user, String role) {
        BindingResult bindingResult = validationServices.validate(user);
        if (bindingResult.hasErrors()) {
            throw new ValidationException("Please try again");
        }

        duplicateCheck(user);
        save(user, role);
    }


    public User save(User user, String role) {
        try {
            switch (role) {
                case ROLE_USER -> {
                    return createUser(user, Role.ROLE_USER);
                }
                case ROLE_ADMIN -> {
                    return createUser(user, Role.ROLE_ADMIN);
                }
                case ROLE_COURIER -> {
                    return createCourier(user);
                }
                default -> throw new ValidationException("unknown user role");
            }
        } catch (ValidationException ve) {
            throw ve;
        } catch (Exception e) {
            log.error(EXCEPTION_SAVING_USER, e);
            throw new RuntimeException(EXCEPTION_SAVING_USER, e);
        }
    }

    private User createUser(User user, Role role) {
        user.setRole(role.getRoleName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepositories.save(user);
        log.info("User saved successfully: " + user.getUsername());
        return savedUser;
    }

    private User createCourier(User user) {
        User savedUser = createUser(user, Role.ROLE_COURIER);
        CourierInf courierInf = new CourierInf();
        courierInf.setStatus(CourierStatus.Free.getStatusName());
        courierInf.setUsers(savedUser);
        courierInfoRepositories.save(courierInf);
        return savedUser;
    }

    public void duplicateCheck(User user) {
        if (userRepositories.findByUsername(user.getUsername()).isPresent()) {
            throw new UsernameAlreadyExistsException("Username incorrect");
        }

        if (userRepositories.findByEmail(user.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email incorrect");
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
