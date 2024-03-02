package by.trubetski.QuickSolution.controllers;

import by.trubetski.QuickSolution.models.User;
import by.trubetski.QuickSolution.services.RegistrationServices;
import by.trubetski.QuickSolution.util.UserValidator;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
@Slf4j
public class RegisterController {
    private final RegistrationServices registrationServices;
    private final UserValidator userValidator;

    @Autowired
    public RegisterController(RegistrationServices registrationServices, UserValidator userValidator) {
        this.registrationServices = registrationServices;
        this.userValidator = userValidator;
    }

    @GetMapping()
    public String newPerson(Model model) {
        model.addAttribute("user", new User());
        return "auth/registration";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult, Model model) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()){
            return "auth/registration";
        }
        try {
            registrationServices.createUser(user);
            log.info("User registration successful: " + user.getUsername());
            return "redirect:/auth/login?registrationSuccess";
        } catch (Exception e) {
            log.error("Mistake during user registration", e);
            model.addAttribute("error", "Registration failed. Please try again.");
            return "auth/registration";

        }
    }
}
