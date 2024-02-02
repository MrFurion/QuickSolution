package by.trubetski.QuickSolution.services;

import by.trubetski.QuickSolution.models.Orders;
import by.trubetski.QuickSolution.models.Users;
import by.trubetski.QuickSolution.repositories.OrderRepositories;
import by.trubetski.QuickSolution.repositories.UsersRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.context.WebEngineContext;

import java.util.Date;
import java.util.Optional;

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
        Users users = new Users();
        System.out.println(users.getId());
        orders.setDate(new Date());
        orderRepositories.save(orders);
    }

//    @Transactional
//    public void createOrder(int userId, Orders orders) {
//        Users users = new Users();
//        users.setId(userId);
//        orders.setOwner(users);
//        orders.setDate(new Date());
//        orderRepositories.save(orders);
//    }
}
