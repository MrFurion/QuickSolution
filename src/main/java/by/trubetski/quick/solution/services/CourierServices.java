package by.trubetski.quick.solution.services;

import by.trubetski.quick.solution.models.Orders;

import java.util.List;

public interface CourierServices {
    /**
     * Find all orders assigned courier
     * @return orders
     */
    List<Orders> findDeliveryAssignedCourier();
}
