package by.trubetski.quick.solution.repositories;

import by.trubetski.quick.solution.models.CourierInf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourierInfoRepositories extends JpaRepository<CourierInf, Integer> {
}
