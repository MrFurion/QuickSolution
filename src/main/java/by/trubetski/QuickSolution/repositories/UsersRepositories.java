package by.trubetski.QuickSolution.repositories;

import by.trubetski.QuickSolution.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepositories extends JpaRepository<Users, Integer> {
    Users findByUsernameAndPassword(String username, String password);
    Optional<Users> findByUsername(String username);
    Optional<Users> findByEmail(String email);

}
