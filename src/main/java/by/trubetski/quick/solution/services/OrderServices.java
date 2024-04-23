package by.trubetski.quick.solution.services;

import by.trubetski.quick.solution.dto.OrderFormDto;
import by.trubetski.quick.solution.models.Orders;

import java.util.List;
import java.util.Optional;

public interface OrderServices {
    /**
     * Save order.
     * Use and extract data from OrderFormDto. It also validates the received data.
     *
     * @param orderFormDto
     */
    void save(OrderFormDto orderFormDto);

    /**
     * Find order by id.
     * Allows retrieving an order by its ID from the order repository,
     * ensuring safe handling in case the order with the specified ID is not present.
     *
     * @param id
     * @return Optional<Orders>
     */
    Optional<Orders> orderById(int id);

    /**
     * Update order by id.
     * Allows you to change an order by its ID. It also validates the received data.
     *
     * @param id
     * @param orderFormDto
     */
    void update(int id, OrderFormDto orderFormDto);

    /**
     * Find all orders with present parameters.
     *
     * @param statusDelivery
     * @param courierPresence
     * @return delivery with parameters.
     */
    List<Orders> findOrdersByStatus(String statusDelivery, String courierPresence);
}
