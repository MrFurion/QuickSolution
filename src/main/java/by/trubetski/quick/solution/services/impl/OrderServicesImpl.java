package by.trubetski.quick.solution.services.impl;

import by.trubetski.quick.solution.exception.OrderNotFoundException;
import by.trubetski.quick.solution.exception.ValidationException;
import by.trubetski.quick.solution.models.Delivery;
import by.trubetski.quick.solution.models.Item;
import by.trubetski.quick.solution.dto.OrderFormDto;
import by.trubetski.quick.solution.models.Orders;
import by.trubetski.quick.solution.repositories.DeliveryRepositories;
import by.trubetski.quick.solution.repositories.ItemRepositories;
import by.trubetski.quick.solution.repositories.OrderRepositories;
import by.trubetski.quick.solution.services.OrderServices;
import by.trubetski.quick.solution.services.UserServices;
import by.trubetski.quick.solution.services.ValidationServices;
import by.trubetski.quick.solution.util.enums.OrderStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class OrderServicesImpl implements OrderServices {

    public static final String NOT_STATUS = "not status";
    private final OrderRepositories orderRepositories;
    private final UserServices userServices;
    private final DeliveryRepositories deliveryRepositories;
    private final ItemRepositories itemRepositories;
    private final ValidationServices validationServices;

    @Transactional
    public void save(OrderFormDto orderFormDto) {
        BindingResult bindingResult = validationServices.validate(orderFormDto);
        if (bindingResult.hasErrors()) {
            throw new ValidationException("error of validation");
        }

        int id = userServices.getUserId();

        Orders orders = new Orders();
        orders.setDate(new Date());
        orders.setStatus(OrderStatus.NEW.getStatusName());
        orders.setOwner(userServices.findById(id));

        Delivery delivery = new Delivery();

        delivery.setStartAddress(orderFormDto.getStartApartment().toString());
        delivery.setFinishAddress(orderFormDto.getFinishApartment().toString());

        Double latitudeStart = orderFormDto.getStartApartment().getLat();
        Double longitudeStart = orderFormDto.getStartApartment().getLng();

        Double latitudeFinish = orderFormDto.getFinishApartment().getLat();
        Double longitudeFinish = orderFormDto.getFinishApartment().getLng();

        GeometryFactory geometryFactory = new GeometryFactory();
        Point pointStart = geometryFactory.createPoint(new Coordinate(latitudeStart, longitudeStart));
        Point pointFinish = geometryFactory.createPoint(new Coordinate(latitudeFinish, longitudeFinish));

        delivery.setCoordinatesStart(pointStart);
        delivery.setCoordinatesFinish(pointFinish);

        delivery.setOrders(orders);
        deliveryRepositories.save(delivery);

        orders.setDelivery(delivery);
        orders.setDel(delivery);
        orderRepositories.save(orders);

        Item item = new Item();
        item.setTypeOrder(orderFormDto.getOrderType());
        item.setTypeDelivery(orderFormDto.getDeliveryType());
        item.setOrders(orders);
        itemRepositories.save(item);
    }

    public Optional<Orders> orderById(int id) {
        return orderRepositories.findById(id);
    }

    public void update(int id, OrderFormDto orderFormDto) {
        Orders order = new Orders();
        orderRepositories.save(order);
    }

    @Transactional
    public void update(int id, String orderStatus, int courierId){
        Optional<Orders> optionalOrder = orderRepositories.findById(id);
        if (optionalOrder.isPresent()) {
            Orders order = optionalOrder.get();
            order.getDelivery().setCourierId(courierId);
            order.setStatus(orderStatus);
            orderRepositories.save(order);
        } else {
            log.error("order not found by id " + id);
            throw new OrderNotFoundException("order not found " + id);

        }
    }

    public List<Orders> findOrdersByStatus(String statusDelivery, Integer courierId) {
        if (courierId == 0 && !statusDelivery.equals(NOT_STATUS)) {
            return orderRepositories.getOrdersByStatus(statusDelivery);
        } else if ((courierId != 0) && statusDelivery.equals(NOT_STATUS)) {
            return orderRepositories.getOrdersByDeliveryCourierId(courierId);
        } else if (courierId != 0) {
            return orderRepositories.getOrdersByStatusAndDeliveryCourierId(statusDelivery, courierId);
        } else {
            return orderRepositories.getOrdersByStatusOrDeliveryCourierId(statusDelivery, courierId);
        }
    }
}
