package by.trubetski.quick.solution.services;

import by.trubetski.quick.solution.models.OrderForm;
import org.springframework.validation.BindingResult;

public interface OrderServices {
    public boolean save(OrderForm entity);
}
