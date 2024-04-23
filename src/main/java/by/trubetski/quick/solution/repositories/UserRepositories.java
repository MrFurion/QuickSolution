package by.trubetski.quick.solution.repositories;

import by.trubetski.quick.solution.models.User;
import jakarta.annotation.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepositories extends JpaRepository<User, Integer> {
    @Query("FROM User where role = :role")
    List<User> getUserByRoleCourier(@Param("role") String role);

    @Nullable
    Optional<User> findByUsername(String username);

    @Nullable
    Optional<User> findByEmail(String email);
}
