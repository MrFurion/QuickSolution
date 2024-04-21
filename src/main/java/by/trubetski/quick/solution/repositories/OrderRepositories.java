package by.trubetski.quick.solution.repositories;

import by.trubetski.quick.solution.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepositories extends JpaRepository<Orders, Integer> {
    @Query("FROM Orders where status = :status and (:courierId is null or delivery.courierId = :courierId)")
    List<Orders> getOrdersByStatusAndDelivery_CourierId(String status, String courierId);
}
