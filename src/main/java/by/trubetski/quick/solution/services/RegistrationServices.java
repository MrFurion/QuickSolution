package by.trubetski.quick.solution.services;

import by.trubetski.quick.solution.models.User;
import java.util.Optional;

public interface RegistrationServices {


    /**
     * User registration.
     */
    public void createUser(User user);

    public Optional<User> getPersonByUsername(String username);

    public Optional<User> getPersonByEmail(String email);
}
