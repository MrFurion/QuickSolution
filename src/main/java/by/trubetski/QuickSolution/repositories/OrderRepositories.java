package by.trubetski.QuickSolution.repositories;

import by.trubetski.QuickSolution.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepositories extends JpaRepository<Orders,Integer> {
}
