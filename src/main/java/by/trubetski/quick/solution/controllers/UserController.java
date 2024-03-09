package by.trubetski.quick.solution.controllers;

import by.trubetski.quick.solution.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserServices userServices;

    @Autowired
    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @GetMapping()
    public String userPage(Model model){
        model.addAttribute("orders", userServices.userOrder());
        return "users/userPage";
    }
}
