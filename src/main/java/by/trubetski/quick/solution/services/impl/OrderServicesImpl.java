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
    public void save(OrderFormDto entity) {
        BindingResult bindingResult = validationServices.validate(entity);
        if (bindingResult.hasErrors()){
            throw new ValidationException("error of validation");
        }

        int id = userServices.getUserId();

        Orders orders = new Orders();
        orders.setDate(new Date());
        orders.setStatus("New");
        orders.setOwner(userServices.findById(id));

        Delivery delivery = new Delivery();
        String startAddress = ("City name: " + entity.getStartApartment().getCity() +
                ", Street name: " + entity.getStartApartment().getStreet() +
                ", House number: " + entity.getStartApartment().getHouseNumber() +
                ", Entrance number: " + entity.getStartApartment().getEntranceNumber() +
                ", Flat number: " + entity.getStartApartment().getFlatNumber());
        delivery.setStartAddress(startAddress);
        String finishAddress = ("City name: " + entity.getFinishApartment().getCity() +
                ", Street name: " + entity.getFinishApartment().getStreet() +
                ", House number: " + entity.getFinishApartment().getHouseNumber() +
                ", Entrance number: " + entity.getFinishApartment().getEntranceNumber() +
                ", Flat number: " + entity.getFinishApartment().getFlatNumber());
        delivery.setFinishAddress(finishAddress);

        Double latitudeStart = entity.getStartApartment().getLat();
        Double longitudeStart= entity.getStartApartment().getLng();

        Double latitudeFinish = entity.getFinishApartment().getLat();
        Double longitudeFinish = entity.getFinishApartment().getLng();

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
        item.setTypeOrder(entity.getOrderType());
        item.setTypeDelivery(entity.getDeliveryType());
        item.setOrders(orders);
        itemRepositories.save(item);
    }
}
