package by.trubetski.quick.solution.services.impl;


import by.trubetski.quick.solution.models.Delivery;
import by.trubetski.quick.solution.models.Item;
import by.trubetski.quick.solution.models.OrderForm;
import by.trubetski.quick.solution.models.Orders;
import by.trubetski.quick.solution.repositories.DeliveryRepositories;
import by.trubetski.quick.solution.repositories.ItemRepositories;
import by.trubetski.quick.solution.repositories.OrderRepositories;
import by.trubetski.quick.solution.repositories.UserRepositories;
import by.trubetski.quick.solution.services.OrderServices;
import by.trubetski.quick.solution.services.UserServices;
import lombok.extern.slf4j.Slf4j;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;


@Service
@Transactional(readOnly = true)
@Slf4j
public class OrderServicesImpl implements OrderServices {
    private final UserRepositories userRepositories;
    private final OrderRepositories orderRepositories;
    private final UserServices userServices;
    private final DeliveryRepositories deliveryRepositories;
    private final ItemRepositories itemRepositories;


    @Autowired
    public OrderServicesImpl(UserRepositories userRepositories, OrderRepositories orderRepositories,
                             UserServices userServices, DeliveryRepositories deliveryRepositories, ItemRepositories itemRepositories) {
        this.userRepositories = userRepositories;
        this.orderRepositories = orderRepositories;
        this.userServices = userServices;
        this.deliveryRepositories = deliveryRepositories;
        this.itemRepositories = itemRepositories;
    }

    @Transactional
    public void save(OrderForm entity) {
        /**
         * Getting the ID of the current user from the userService
         */
        int id = userServices.getUserId();

        /**
         * Creating an Orders object and setting the necessary properties
         */
        Orders orders = new Orders();
        orders.setDate(new Date());
        orders.setStatus("New");
        orders.setOwner(userServices.findById(id));

        /**
         * Creating a Delivery object and setting properties for the start and
         * end addresses of the delivery
         */
        Delivery delivery = new Delivery();
        String startAddress = (entity.getStartCity() + " "
                + entity.getStartStreet() + " "
                + entity.getStartHouseNumber() + " "
                + entity.getStartEntranceNumber() + " "
                + entity.getStartFlatNumber());
        delivery.setStartAddress(startAddress);
        String finishAddress = (entity.getFinishCity() + " "
                + entity.getFinishStreet() + " "
                + entity.getFinishHouseNumber() + " "
                + entity.getFinishEntranceNumber() +" "
                + entity.getFinishFlatNumber());
        delivery.setFinishAddress(finishAddress);

        /**
         * setting the coordinates for the start and end of the delivery
         */

        Double latitudeStart = entity.getStartLat();
        Double longitudeStart= entity.getStartLng();

        Double latitudeFinish = entity.getEndLat();
        Double longitudeFinish = entity.getEndLng();

        GeometryFactory geometryFactory = new GeometryFactory();
        Point pointStart = geometryFactory.createPoint(new Coordinate(latitudeStart, longitudeStart));
        Point pointFinish = geometryFactory.createPoint(new Coordinate(latitudeFinish, longitudeFinish));
        log.info(pointStart.toString());
        log.info(pointFinish.toString());
        delivery.setCoordinatesStart(pointStart);
        delivery.setCoordinatesFinish(pointFinish);

        /**
         * Establish a connection between the order and delivery and
         * save the Delivery object in the repository
         */
        delivery.setOrders(orders);
        deliveryRepositories.save(delivery);

        /**
         * Establish links between the order and delivery and
         * save the Orders object in the repository
         */
        orders.setDelivery(delivery);
        orders.setDel(delivery);
        orderRepositories.save(orders);

        /**
         * An Item object is created and data about the type of delivery
         * and order is placed in it
         */
        Item item = new Item();
        item.setTypeOrder(entity.getOrderType());
        item.setTypeDelivery(entity.getDeliveryType());
        item.setOrders(orders);
        itemRepositories.save(item);
    }
}
