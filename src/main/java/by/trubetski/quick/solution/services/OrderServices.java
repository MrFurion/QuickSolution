package by.trubetski.quick.solution.services;

import by.trubetski.quick.solution.interfaces.EntityServices;
import by.trubetski.quick.solution.models.Delivery;
import by.trubetski.quick.solution.models.OrderForm;
import by.trubetski.quick.solution.models.Orders;
import by.trubetski.quick.solution.repositories.DeliveryRepositories;
import by.trubetski.quick.solution.repositories.OrderRepositories;
import by.trubetski.quick.solution.repositories.UserRepositories;
import org.postgresql.geometric.PGpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class OrderServices implements EntityServices<OrderForm> {
    private final UserRepositories userRepositories;
    private final OrderRepositories orderRepositories;
    private final UserServices userServices;
    private final DeliveryRepositories deliveryRepositories;


    @Autowired
    public OrderServices(UserRepositories userRepositories, OrderRepositories orderRepositories,
                         UserServices userServices, DeliveryRepositories deliveryRepositories) {
        this.userRepositories = userRepositories;
        this.orderRepositories = orderRepositories;
        this.userServices = userServices;
        this.deliveryRepositories = deliveryRepositories;
    }


    @Override
    public OrderForm findById(int id) {
        return null;
    }

    @Override
    public List<OrderForm> findAll() {
        return null;
    }

    @Override
    @Transactional
    public void save(OrderForm entity) {
        System.out.println("Action 3");
        // Получаем идентификатор текущего пользователя из сервиса пользователей
        int id = userServices.getUserId();

        // Создаем объект Orders и устанавливаем необходимые свойства
        Orders orders = new Orders();
        orders.setDate(new Date());
        orders.setStatus("New");
        orders.setName(entity.getOrderName());
        orders.setOwner(userServices.findById(id));

        // Создаем объект Delivery и устанавливаем свойства для адресов начала и конца доставки
        Delivery delivery = new Delivery();
        delivery.setStartAddress(entity.getStartAddress());
        delivery.setFinishAddress(entity.getFinishAddress());

        Double latitude = entity.getStartLat();
        Double longitude = entity.getStartLng();

        Point point = new Point(latitude, longitude);
        System.out.println(point);
        delivery.setCoordinates(point);






        // Устанавливаем связь между заказом и доставкой и сохраняем объект Delivery в репозитории
        delivery.setOrders(orders);
        deliveryRepositories.save(delivery);

        // Устанавливаем связи между заказом и доставкой и сохраняем объект Orders в репозитории
        orders.setDelivery(delivery);
        orders.setDel(delivery);
        orderRepositories.save(orders);


        System.out.println("Action 4");
    }

    @Override
    public void update(OrderForm entity) {

    }

    @Override
    public void delete(int id) {

    }
}
