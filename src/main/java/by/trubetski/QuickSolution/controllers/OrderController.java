package by.trubetski.QuickSolution.controllers;

import by.trubetski.QuickSolution.models.Orders;
import by.trubetski.QuickSolution.services.OrderServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/orders")
public class OrderController {
    private final OrderServices orderServices;

    public OrderController(OrderServices orderServices) {
        this.orderServices = orderServices;
    }
    @GetMapping("/createOrder")
    public String person (@ModelAttribute("orders") Orders orders){
        return "orders/createOrder";
    }

//    @PostMapping()
//    public String createOrder(@RequestParam int userId, @ModelAttribute("orders") @RequestBody Orders orders ){
//       orderServices.createOrder(userId, orders);
//        return "/users/userPage";
//    }
    @PostMapping()
    public String createOrder(@ModelAttribute("orders") Orders orders){
        orderServices.createOrder(orders);
        return "/users/userPage";
    }
}
