package by.trubetski.quick.solution.controllers;

import by.trubetski.quick.solution.services.OrderServices;
import by.trubetski.quick.solution.services.UserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserServices userServices;
    private final OrderServices orderServices;

    @GetMapping
    public String adminPage(Model model) {
        model.addAttribute("users", userServices.findAllCourier());
        model.addAttribute("couriers", userServices.findAllCourier());
        return "users/adminPage";
    }

    @GetMapping("/findAllOrders")
    public String findAllUser(Model model, @RequestParam(name = "status", required = false) String status,
                              @RequestParam(name = "courier", required = false) Integer courierId) {
        model.addAttribute("orders", orderServices.findOrdersByStatus(status, courierId));
        model.addAttribute("couriers", userServices.findAllCourier());
        adminPage(model);
        return "/users/adminPage";
    }
    @PostMapping("/updateOrderStatus")
    public String updateOrder(@RequestParam("orderId") int orderId,@RequestParam("newStatus") String status,
                                    @RequestParam("courierId") int courierId) {
        orderServices.update(orderId, status, courierId);
        return "/users/adminPage";
    }
}
