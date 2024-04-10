package by.trubetski.quick.solution.repositories;

import by.trubetski.quick.solution.models.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRepositories extends JpaRepository<Delivery, Integer> {
}
