package by.trubetski.QuickSolution.controllers;

import by.trubetski.QuickSolution.models.Users;

import by.trubetski.QuickSolution.services.UsersServices;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UsersControllers {
    private final UsersServices usersServices;

    @Autowired
    public UsersControllers(UsersServices usersServices) {
        this.usersServices = usersServices;

    }
    @GetMapping
    public String index(Model model){
        return "users/login";
    }
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        Users users = usersServices.login(username, password);
        if (users != null) {
            model.addAttribute("user", users);
            return "users/userPage"; // перенаправление на успешную страницу
        } else {
            model.addAttribute("error", "Name or password not found");
            return "users/login"; // перенаправление на страницу ошибки
        }
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Users users) {
        return "users/register";
    }

    @PostMapping("/addUser")
    public String create(@ModelAttribute("person") Users users, Model model) {
        try {
            usersServices.createUser(users);
            return "users/login";
        }catch (Exception e){
            model.addAttribute("error", "Username already exists ");
            return "users/register";
        }
    }

}