package by.trubetski.quick.solution.services;

import by.trubetski.quick.solution.dto.OrderFormDto;
import by.trubetski.quick.solution.models.Orders;
import java.util.Optional;

public interface OrderServices {
    /**
     * Save order.
     */
    void save(OrderFormDto orderFormDto);

    /**
     * Find order by id.
     */
    Optional<Orders> orderById(int id);

    /**
     * Update order by id.
     */
    void update(int id, OrderFormDto orderFormDto);
}
