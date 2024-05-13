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
        return "orders/createOrder";
    }

    @PostMapping("/create")
    public String saveOrder(@ModelAttribute("orders") @Valid OrderFormDto orderForm,
                            BindingResult bindingResult, Model model,
                            RedirectAttributes redirectAttributes) {
        try {
            orderServices.save(orderForm);
            redirectAttributes.addFlashAttribute("successMessage", "Order successfully created!");
            return "redirect:/user";
        } catch (ValidationException e) {
            model.addAttribute("error", bindingResult.getAllErrors());
            log.error("Cannot create order: {}", bindingResult.toString());
            return "orders/createOrder";
        }
    }

    @GetMapping("/{id}")
    public String showOrder(@PathVariable("id") int id, Model model) {
        model.addAttribute("order", orderServices.orderById(id));
        return "orders/showOrder";
    }

    @GetMapping("/{id}/edit")
    public String showEditOrderPage(@PathVariable int id, Model model) {
        model.addAttribute("order", orderServices.orderById(id));
        return "orders/editOrder";
    }

    @PutMapping("/{id}")
    public String updateOrder(@ModelAttribute("order") @Valid OrderFormDto orderFormDto,
                              BindingResult bindingResult, Model model,
                              @PathVariable("id") int id,
                              RedirectAttributes redirectAttributes) {
        try {
            orderServices.update(id, orderFormDto);
            redirectAttributes.addFlashAttribute("successMessage", "Order update!");
            return "redirect:/showOrder";
        } catch (ValidationException e) {
            model.addAttribute("error", bindingResult.getAllErrors());
            log.error("Cannot update order: {}", bindingResult);
            return "orders/editOrder";
        }
    }
}
