package by.trubetski.quick.solution.services;

import by.trubetski.quick.solution.dto.ApartmentDto;
import by.trubetski.quick.solution.dto.OrderFormDto;
import by.trubetski.quick.solution.models.Delivery;
import by.trubetski.quick.solution.models.Item;
import by.trubetski.quick.solution.models.Orders;
import by.trubetski.quick.solution.repositories.DeliveryRepositories;
import by.trubetski.quick.solution.repositories.ItemRepositories;
import by.trubetski.quick.solution.repositories.OrderRepositories;
import by.trubetski.quick.solution.services.impl.OrderServicesImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.times;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.validation.BindingResult;

import static org.mockito.Mockito.*;

public class OrderServicesTest {
    @Mock
    private DeliveryRepositories deliveryRepository;

    @Mock
    private OrderRepositories orderRepository;

    @Mock
    private ItemRepositories itemRepository;

    @Mock
    private UserServices userServices;
    @Mock
    private ValidationServices validationServices;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private OrderServicesImpl orderServices;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    public OrderFormDto orderFormDto() {

        return new OrderFormDto("express", "food",
                new ApartmentDto("Start City", "Start Street", "123", "1", "1", 55.7558, 37.6176),
                new ApartmentDto("Finish City", "Finish Street", "456", "2", "2", 53.9023, 27.5619));
    }

    @Test
    void saveTest() {
        OrderFormDto orderFormDto = orderFormDto();
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);
        when(validationServices.validate(any(OrderFormDto.class))).thenReturn(bindingResult);
        orderServices.save(orderFormDto);
        verify(deliveryRepository, times(1)).save(any(Delivery.class));
        verify(orderRepository, times(1)).save(any(Orders.class));
        verify(itemRepository, times(1)).save(any(Item.class));
    }
}
