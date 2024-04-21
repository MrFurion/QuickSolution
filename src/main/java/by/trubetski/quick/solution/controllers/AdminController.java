package by.trubetski.quick.solution.controllers;

import by.trubetski.quick.solution.services.OrderServices;
import by.trubetski.quick.solution.services.UserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
        return "users/adminPage";
    }

    @GetMapping("/findAllOrders")
    public String findAllUser(Model model, @RequestParam(name = "status", required = false) String status,
                              @RequestParam(name = "cuorier", required = false) String courier) {
        model.addAttribute("orders", orderServices.findOrdersByStatus(status, courier));
        adminPage(model);
        return "/users/adminPage";
    }
}
