package by.trubetski.quick.solution.services;

import by.trubetski.quick.solution.models.User;

import java.util.Optional;

public interface RegistrationServices {


    /**
     * User registration.
     *
     * @param user The User entity to be created.
     */
    void createUser(User user);

    /**
     * User registration with role
     *
     * @param user The User entity to be created.
     * @param role Role users
     */
    void createUserByRole(User user, String role);

    /**
     * Registering a user by role
     * Hashes the password and saves it when all user conditions are met for storing in the database.
     * Also add roles use
     *
     * @param user The User entity to be created.
     * @param role Role users
     */
    User save(User user, String role);

    /**
     * Check for duplicate email and name in the database.
     *
     * @param user The User entity to be created.
     */
    void duplicateCheck(User user);

    /**
     * Check for duplicate name in the database.
     *
     * @param username name of user
     * @return Optional<User></>
     */
    Optional<User> getPersonByUsername(String username);

    /**
     * Check for duplicate email in the database.
     *
     * @param email email of user
     * @return Optional<User></>
     */
    Optional<User> getPersonByEmail(String email);
}
