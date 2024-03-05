package by.trubetski.quickSolution.repositories;

import by.trubetski.quickSolution.models.User;

import jakarta.annotation.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepositories extends JpaRepository<User, Integer> {


    @Nullable
    Optional<User> findByUsername( String username);

    @Nullable
    Optional<User> findByEmail(String email);


}
