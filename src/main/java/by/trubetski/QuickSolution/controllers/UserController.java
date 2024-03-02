package by.trubetski.QuickSolution.controllers;

import by.trubetski.QuickSolution.services.RegistrationServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping()
public class UserController {
    private final RegistrationServices usersServices;

    public UserController(RegistrationServices usersServices) {
        this.usersServices = usersServices;
    }
    @GetMapping("/hello")
    public String userPage(){
        return "users/userPage";
    }


}
