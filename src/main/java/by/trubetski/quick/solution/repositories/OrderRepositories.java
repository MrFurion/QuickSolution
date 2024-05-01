package by.trubetski.quick.solution.repositories;

import by.trubetski.quick.solution.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepositories extends JpaRepository<Orders, Integer> {
    List<Orders> getOrdersByStatusOrDeliveryCourierId(String status, Integer courierId);
    List<Orders> getOrdersByStatusAndDeliveryCourierId(String status, Integer courierId);
    List<Orders> getOrdersByStatus(String status);
    List<Orders> getOrdersByDeliveryCourierId(Integer courierId);
}
