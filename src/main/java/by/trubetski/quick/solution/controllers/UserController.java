package by.trubetski.quick.solution.controllers;

import by.trubetski.quick.solution.services.UserServices;
import by.trubetski.quick.solution.services.impl.UserServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserServices userServices;

    @Autowired
    public UserController(UserServicesImpl userServices) {
        this.userServices = userServices;
    }

    @GetMapping()
    public String userPage(Model model,  @ModelAttribute("successMessage") String successMessage){
        model.addAttribute("orders", userServices.userOrder());
        model.addAttribute("successMessage", successMessage);
        return "users/userPage";
    }
}
