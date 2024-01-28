package by.trubetski.QuickSolution.controllers;

import by.trubetski.QuickSolution.models.Orders;
import by.trubetski.QuickSolution.models.Users;
import by.trubetski.QuickSolution.repositories.UsersRepositories;
import by.trubetski.QuickSolution.services.OrderServices;
import by.trubetski.QuickSolution.services.UsersServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/orders")

public class OrderController {
    private final OrderServices orderServices;
    private final UsersServices usersServices;

    public OrderController(OrderServices orderServices, UsersServices usersServices) {
        this.orderServices = orderServices;
        this.usersServices = usersServices;
    }

    @GetMapping("/createOrder")
    public String person (@ModelAttribute("orders") Orders orders){
        return "orders/createOrder";
    }
    @PostMapping
    public String createOrder(@ModelAttribute("orders") Orders orders){
        orderServices.createOrder(orders);
        return "/users/index";
    }
    @PostMapping("/{userId}")
    public void createOrderForUser(@PathVariable int userId, Model model) {
        model.addAttribute(usersServices.findOne(userId));
    }


}
