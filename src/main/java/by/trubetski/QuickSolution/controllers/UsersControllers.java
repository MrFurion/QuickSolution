package by.trubetski.QuickSolution.controllers;

import by.trubetski.QuickSolution.models.Orders;
import by.trubetski.QuickSolution.models.Users;
import by.trubetski.QuickSolution.services.UsersServices;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        model.addAttribute("orders", usersServices.getOrdersOfPerson(id));
        return "users/show";
    }
    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Users users, Model model) {
        return "users/new";
    }
    @PostMapping
    public String createPerson(@ModelAttribute("person")Users users){
        usersServices.save(users);
        return "redirect:users";
    }

}