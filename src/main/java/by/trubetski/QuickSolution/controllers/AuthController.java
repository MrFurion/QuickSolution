package by.trubetski.QuickSolution.controllers;

import by.trubetski.QuickSolution.services.RegistrationServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final RegistrationServices userServices;


    @Autowired
    public AuthController(RegistrationServices userServices) {
        this.userServices = userServices;
    }

    @GetMapping("login")
    public String loginPage(){
        return "auth/login";

    }
}
