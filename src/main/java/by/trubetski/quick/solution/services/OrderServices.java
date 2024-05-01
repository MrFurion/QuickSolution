package by.trubetski.quick.solution.services;

import by.trubetski.quick.solution.dto.OrderFormDto;
import by.trubetski.quick.solution.models.Orders;
import by.trubetski.quick.solution.util.enums.OrderStatus;

import java.util.List;
import java.util.Optional;

public interface OrderServices {
    /**
     * Save order and validate data.
     *
     * @param orderFormDto OrderFormDto
     */
    void save(OrderFormDto orderFormDto);

    /**
     * Find order by id.
     * Allows retrieving an order by its ID from the order repository,
     * ensuring safe handling in case the order with the specified ID is not present.
     *
     * @param id Order ID
     * @return Optional<Orders>
     */
    Optional<Orders> orderById(int id);

    /**
     * Update order by id.
     * Allows you to change an order by its ID. It also validates the received data.
     *
     * @param id Order ID
     * @param orderFormDto OrderFormDto
     */
    void update(int id, OrderFormDto orderFormDto);

    /**
     * Update order by id.
     * Allows you to change an order by its ID.
     * @param id Order ID
     * @param orderStatus {@link OrderStatus}
     * @param courierId Courier ID
     */
    void update(int id, String orderStatus, int courierId);

    /**
     * Find all orders with present parameters.
     *
     * @param statusDelivery {@link OrderStatus}
     * @param courierId Courier ID
     * @return delivery with parameters.
     */
    List<Orders> findOrdersByStatus(String statusDelivery, Integer courierId);
}
