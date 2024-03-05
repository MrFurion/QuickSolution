package by.trubetski.quickSolution.controllers;

import by.trubetski.quickSolution.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping()
public class UserController {
    private final UserServices userServices;

    @Autowired
    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @GetMapping("/hello")
    public String userPage(Model model){
        model.addAttribute("orders", userServices.userOrder());
        return "users/userPage";
    }
}
