package by.trubetski.quick.solution.controllers;

import by.trubetski.quick.solution.models.User;
import by.trubetski.quick.solution.services.OrderServices;
import by.trubetski.quick.solution.services.RegistrationServices;
import by.trubetski.quick.solution.services.UserServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@Slf4j
public class AdminController {

    private static final String AUTH_CREATE_COURIER = "auth/createCourier";
    private final UserServices userServices;
    private final OrderServices orderServices;
    private final RegistrationServices registrationServices;

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
    public String updateOrder(@RequestParam("orderId") int orderId, @RequestParam("newStatus") String status,
                              @RequestParam("courierId") int courierId) {
        orderServices.update(orderId, status, courierId);
        return "/users/adminPage";
    }

    @GetMapping("/pageCreateCourier")
    public String pageCrateCourier(Model model) {
        model.addAttribute("user", new User());
        return AUTH_CREATE_COURIER;
    }

    @PostMapping("/createNewCourier")
    public String createNewCourier(@ModelAttribute("user") @Valid User user,
                                   BindingResult bindingResult, Model model,
                                   @RequestParam("role") String role) {
        try {
            registrationServices.createUserByRole(user, role);
            model.addAttribute("successMessage", user.getUsername() + " successful create");
            return AUTH_CREATE_COURIER;
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            log.error("Cannot create courier role: {}", bindingResult.toString());
            return AUTH_CREATE_COURIER;
        }
    }
}
