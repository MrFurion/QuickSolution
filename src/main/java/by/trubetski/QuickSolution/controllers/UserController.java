package by.trubetski.QuickSolution.controllers;

import by.trubetski.QuickSolution.services.UserServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping()
public class UserController {
    private final UserServices usersServices;

    public UserController(UserServices usersServices) {
        this.usersServices = usersServices;
    }
    @GetMapping("/hello")
    public String userPage(){
        return "users/userPage";
    }

}
