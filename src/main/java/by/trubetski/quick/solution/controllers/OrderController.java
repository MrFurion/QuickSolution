package by.trubetski.quick.solution.controllers;

import by.trubetski.quick.solution.exception.ValidationException;
import by.trubetski.quick.solution.dto.OrderFormDto;
import by.trubetski.quick.solution.services.OrderServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/order")
@Slf4j
@RequiredArgsConstructor
public class OrderController {

    private final OrderServices orderServices;

    @GetMapping
    public String pageOrder(Model model) {
        model.addAttribute("orders", new OrderFormDto());
        return "orders/pageOrder";
    }

    @PostMapping("/create")
    public String saveOrder(@ModelAttribute("orders") @Valid OrderFormDto orderForm,
                            BindingResult bindingResult, Model model,
                            RedirectAttributes redirectAttributes) {
         try {
             orderServices.save(orderForm);
             redirectAttributes.addFlashAttribute("successMessage", "Order successfully created!");
             return "redirect:/user";
         } catch (ValidationException e){
             model.addAttribute("error", bindingResult.getAllErrors());
             log.error(bindingResult.toString());
             return "orders/pageOrder";
         }
    }
}