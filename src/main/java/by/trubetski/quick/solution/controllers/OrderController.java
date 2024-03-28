package by.trubetski.quick.solution.controllers;

import by.trubetski.quick.solution.models.OrderForm;
import by.trubetski.quick.solution.services.OrderServices;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/order")
@Slf4j
public class OrderController {
    private final OrderServices orderServices;

    @Autowired
    public OrderController(OrderServices orderServices) {
        this.orderServices = orderServices;
    }

    @GetMapping
    public String pageOrder(Model model){
        model.addAttribute("orders", new OrderForm());
        return "orders/pageOrder";
    }
    @PostMapping("/create")
    public String saveOrder(@ModelAttribute("orders") @Valid OrderForm orderForm,
                            BindingResult bindingResult, Model model,
                            RedirectAttributes redirectAttributes){
       boolean save = orderServices.save(orderForm);

        if (!save) {
            model.addAttribute("error", bindingResult.getAllErrors());
            log.info(orderForm.toString());
            return "orders/pageOrder";
        }else {
            redirectAttributes.addFlashAttribute("successMessage", "Order successfully created!");
            return "redirect:/user";
        }
    }
}
