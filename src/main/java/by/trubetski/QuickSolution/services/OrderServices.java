package by.trubetski.QuickSolution.services;

import by.trubetski.QuickSolution.models.Orders;
import by.trubetski.QuickSolution.models.Users;
import by.trubetski.QuickSolution.repositories.OrderRepositories;
import by.trubetski.QuickSolution.repositories.UsersRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional(readOnly = true)
public class OrderServices {
    private final OrderRepositories orderRepositories;
    private final UsersRepositories usersRepositories;

    @Autowired
    public OrderServices(OrderRepositories orderRepositories, UsersRepositories usersRepositories) {
        this.orderRepositories = orderRepositories;
        this.usersRepositories = usersRepositories;
    }
    @Transactional
    public void createOrder(Orders orders) {
        orders.setDate(new Date());
        orderRepositories.save(orders);
    }
}
