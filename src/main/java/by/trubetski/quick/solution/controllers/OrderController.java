package by.trubetski.quick.solution.controllers;

import by.trubetski.quick.solution.models.OrderForm;
import by.trubetski.quick.solution.services.OrderServices;
import by.trubetski.quick.solution.services.UserServices;
import by.trubetski.quick.solution.services.impl.OrderServicesImpl;
import by.trubetski.quick.solution.services.impl.UserServicesImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/order")
@Slf4j
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
        model.addAttribute("orders", new OrderForm());
        return "orders/pageOrder";
    }
    @PostMapping("/create")
    public String saveOrder(@ModelAttribute("orders") OrderForm orderForm){
        log.info(orderForm.toString());
        orderServices.save(orderForm);
        return "orders/pageOrder";
    }
}
