package by.trubetski.quick.solution.services;

import by.trubetski.quick.solution.dto.OrderFormDto;
import by.trubetski.quick.solution.models.Orders;

import java.util.Optional;

public interface OrderServices {
    public void save(OrderFormDto entity);
    public Optional<Orders> orderById(int id);
}
