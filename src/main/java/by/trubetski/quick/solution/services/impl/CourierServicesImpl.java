package by.trubetski.quick.solution.services.impl;

import by.trubetski.quick.solution.models.Orders;
import by.trubetski.quick.solution.repositories.OrderRepositories;
import by.trubetski.quick.solution.services.CourierServices;
import by.trubetski.quick.solution.services.UserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CourierServicesImpl implements CourierServices {
    private final OrderRepositories orderRepositories;
    private final UserServices userServices;

    @Override
    public List<Orders> findDeliveryAssignedCourier() {
        List<Orders> order = orderRepositories.getOrdersByDeliveryCourierId(userServices.getUserId());
        if(order.isEmpty()){
            return Collections.emptyList();
        } else {
            return order;
        }
    }
}
