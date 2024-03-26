package by.trubetski.quick.solution.repositories;

import by.trubetski.quick.solution.models.User;

import jakarta.annotation.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepositories extends JpaRepository<User, Integer> {

    /**
     * Mock javadoc - let's assume it was written by another dev around the same time
     * @param username username
     * @return User is found in the database or empty Optional
     */
    @Nullable
    Optional<User> findByUsername( String username);

    @Nullable
    Optional<User> findByEmail(String email);


}
