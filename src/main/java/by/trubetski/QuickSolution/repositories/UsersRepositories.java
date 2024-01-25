package by.trubetski.QuickSolution.repositories;

import by.trubetski.QuickSolution.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepositories extends JpaRepository<Users, Integer> {
}
