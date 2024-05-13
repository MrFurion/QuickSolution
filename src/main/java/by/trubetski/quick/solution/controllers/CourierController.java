package by.trubetski.quick.solution.controllers;

import by.trubetski.quick.solution.services.CourierServices;
import by.trubetski.quick.solution.services.DeliveryServices;
import by.trubetski.quick.solution.services.OrderServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/courier")
@RequiredArgsConstructor
public class CourierController {
    private final CourierServices courierServices;
    private final OrderServices orderServices;
    private final DeliveryServices deliveryServices;
    @GetMapping
    public String courierPage(Model model){
        model.addAttribute("courier", courierServices.findDeliveryAssignedCourier());
        return "users/courierPage";
    }
    @GetMapping("/{id}")
    public String infoOrder(@PathVariable("id") int id, Model model){
        model.addAttribute("infOrder", orderServices.orderById(id));
        return "orders/orderInfo";
    }
    @PostMapping("/update")
    public String updateStatusDelivery(@RequestParam("status") String status, @RequestParam("deliveryId") int id, Model model){
        deliveryServices.updateStatus(status, id);
        infoOrder(id, model);
        return "orders/orderInfo";
    }
}
