package by.trubetski.quick.solution.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @GetMapping("login")
    public String loginPage(){
        return "auth/login";

    }

    @GetMapping("/testPage")
    public String testPage(){
        return "some another impl done by another dev around the same time";
    }
}
