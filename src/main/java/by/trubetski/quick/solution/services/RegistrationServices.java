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
    public void createUser(User user);

    public Optional<User> getPersonByUsername(String username);

    public Optional<User> getPersonByEmail(String email);
}
