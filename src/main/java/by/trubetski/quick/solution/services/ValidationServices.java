package by.trubetski.quick.solution.services;

import by.trubetski.quick.solution.models.OrderForm;
import org.springframework.validation.BindingResult;

public interface ValidationServices {
    public <T> BindingResult validate(T object);
}
