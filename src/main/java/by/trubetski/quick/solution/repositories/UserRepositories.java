package by.trubetski.quick.solution.repositories;

import by.trubetski.quick.solution.models.User;
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
