package by.trubetski.QuickSolution.services;

import by.trubetski.QuickSolution.models.Orders;
import by.trubetski.QuickSolution.repositories.OrderRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class OrderServices {
    private final OrderRepositories orderRepositories;

    public OrderServices(OrderRepositories orderRepositories) {
        this.orderRepositories = orderRepositories;
    }
    @Transactional
    public void save(Orders orders){
        orderRepositories.save(orders);
    }
}
