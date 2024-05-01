package by.trubetski.quick.solution.services;

import by.trubetski.quick.solution.models.User;

import java.util.Optional;

public interface RegistrationServices {


    /**
     * User registration.
     * Check for duplicate email and name in the database. Additionally,
     * it hashes the password and saves it when all user conditions are met for storing in the database.
     * Also add roles user.
     *
     * @param user The User entity to be created.
     */
    void createUser(User user);
    void createCourier(User user, String role);

    Optional<User> getPersonByUsername(String username);

    Optional<User> getPersonByEmail(String email);
}
