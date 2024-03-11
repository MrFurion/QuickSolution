package by.trubetski.quick.solution.controllers;

import by.trubetski.quick.solution.models.OrderForm;
import by.trubetski.quick.solution.models.Orders;
import by.trubetski.quick.solution.models.User;
import by.trubetski.quick.solution.services.OrderServices;
import by.trubetski.quick.solution.services.UserServices;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/order")
public class OrderController {
    private final OrderServices orderServices;
    private final UserServices userServices;

    @Autowired
    public OrderController(OrderServices orderServices, UserServices userServices) {
        this.orderServices = orderServices;
        this.userServices = userServices;
    }

    @GetMapping
    public String pageOrder(Model model){
        System.out.println("Action 1");
        model.addAttribute("orders", new OrderForm());
        return "orders/pageOrder";
    }
    @PostMapping("/create")
    public String saveOrder(@ModelAttribute("orders") OrderForm orderForm){
        System.out.println("Action 2");
        orderServices.save(orderForm);

        return "orders/pageOrder";
    }
}
