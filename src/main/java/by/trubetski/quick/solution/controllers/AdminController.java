package by.trubetski.quick.solution.controllers;

import by.trubetski.quick.solution.services.UserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserServices userServices;

    @GetMapping
    public String adminPage(Model model){
        model.addAttribute("users", userServices.findAllCourier());
        return "users/adminPage";
    }
    @GetMapping("/findAllUser")
    public String findAllUser(){
        return null;
    }
}
