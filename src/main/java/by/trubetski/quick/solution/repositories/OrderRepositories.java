package by.trubetski.quick.solution.repositories;

import by.trubetski.quick.solution.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepositories extends JpaRepository<Orders, Integer> {
}