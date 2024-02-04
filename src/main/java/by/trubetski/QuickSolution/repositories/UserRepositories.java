package by.trubetski.QuickSolution.repositories;

import by.trubetski.QuickSolution.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepositories extends JpaRepository<User, Integer> {
    Optional<User> findByUsername( String username);
}
