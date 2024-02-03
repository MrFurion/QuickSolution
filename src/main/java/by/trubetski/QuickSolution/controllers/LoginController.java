package by.trubetski.QuickSolution.controllers;

import by.trubetski.QuickSolution.services.UserServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping()
public class LoginController {
    private final UserServices usersServices;

    public LoginController(UserServices usersServices) {
        this.usersServices = usersServices;
    }
    @GetMapping("/hello")
    public String login(){
        return "users/userPage";
    }

}
