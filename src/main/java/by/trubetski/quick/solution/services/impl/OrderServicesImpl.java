package by.trubetski.quick.solution.services.impl;

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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import java.util.Date;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class OrderServicesImpl implements OrderServices {

    private final OrderRepositories orderRepositories;
    private final UserServices userServices;
    private final DeliveryRepositories deliveryRepositories;
    private final ItemRepositories itemRepositories;
    private final ValidationServices validationServices;

    @Transactional
    public void save(OrderFormDto orderFormDto) {
        BindingResult bindingResult = validationServices.validate(orderFormDto);
        if (bindingResult.hasErrors()){
            throw new ValidationException("error of validation");
        }

        int id = userServices.getUserId();

        Orders orders = new Orders();
        orders.setDate(new Date());
        orders.setStatus("New");
        orders.setOwner(userServices.findById(id));

        Delivery delivery = new Delivery();
        StringBuffer startAddressBuffer = new StringBuffer();
        startAddressBuffer.append(orderFormDto.getStartApartment().getCity())
                .append(" ").append(orderFormDto.getStartApartment().getStreet())
                .append(" ").append(orderFormDto.getStartApartment().getHouseNumber())
                .append(" ").append(orderFormDto.getStartApartment().getEntranceNumber())
                .append(" ").append(orderFormDto.getStartApartment().getFlatNumber());
        delivery.setStartAddress(startAddressBuffer.toString());
        StringBuffer finishAddressBuffer = new StringBuffer();
        finishAddressBuffer.append(orderFormDto.getFinishApartment().getCity())
                .append(" ").append(orderFormDto.getFinishApartment().getStreet())
                .append(" ").append(orderFormDto.getFinishApartment().getHouseNumber())
                .append(" ").append(orderFormDto.getFinishApartment().getEntranceNumber())
                .append(" ").append(orderFormDto.getFinishApartment().getFlatNumber());
        delivery.setFinishAddress(finishAddressBuffer.toString());


        Double latitudeStart = orderFormDto.getStartApartment().getLat();
        Double longitudeStart= orderFormDto.getStartApartment().getLng();

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
}