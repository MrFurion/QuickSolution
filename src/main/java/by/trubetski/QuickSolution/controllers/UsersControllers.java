package by.trubetski.QuickSolution.controllers;

import by.trubetski.QuickSolution.services.UsersServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UsersControllers {
    private final UsersServices usersServices;

    public UsersControllers(UsersServices usersServices) {
        this.usersServices = usersServices;
    }
    @GetMapping
    public String index(Model model){
        model.addAttribute("users", usersServices.findAll());
        return "users/index";
    }
    @GetMapping("/basket")
    public String basket(Model model){
        model.addAttribute("basket", usersServices.findAll());
        return "users/basket";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("person", usersServices.findOne(id));
        return "users/show";
    }
}
