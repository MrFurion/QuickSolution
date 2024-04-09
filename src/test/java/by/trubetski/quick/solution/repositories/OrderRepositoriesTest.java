package by.trubetski.quick.solution.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import by.trubetski.quick.solution.models.Orders;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.PropertySource;

@DataJpaTest
@PropertySource("classpath:test.properties")
public class OrderRepositoriesTest {

    @Autowired
    private OrderRepositories orderRepositories;

    @Test
    public void testSaveOrder() {
        Orders order = new Orders();
        order.setStatus("New");
        Orders savedOrder = orderRepositories.save(order);
        assertNotNull(savedOrder.getId());
        assertEquals("New", savedOrder.getStatus());
    }
}
