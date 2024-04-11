package by.trubetski.quick.solution.controllers;

import by.trubetski.quick.solution.dto.OrderFormDto;
import by.trubetski.quick.solution.exception.ValidationException;
import by.trubetski.quick.solution.services.OrderServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(OrderController.class)
@AutoConfigureMockMvc
public class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderServices orderServices;

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new OrderController(orderServices)).build();
    }

    @Test
    public void testSaveOrderSuccess() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/order/create")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("startApartment.city", "Start City")
                        .param("startApartment.street", "Start Street")
                        .param("startApartment.houseNumber", "123")
                        .param("startApartment.entranceNumber", "1")
                        .param("startApartment.flatNumber", "1")
                        .param("finishApartment.city", "Finish City")
                        .param("finishApartment.street", "Finish Street")
                        .param("finishApartment.houseNumber", "456")
                        .param("finishApartment.entranceNumber", "2")
                        .param("finishApartment.flatNumber", "2")
                        .param("deliveryType", "express")
                        .param("orderType", "food")
                        .param("startApartment.lat", "55.7558")
                        .param("startApartment.lng", "37.6176")
                        .param("finishApartment.lat", "53.9023")
                        .param("finishApartment.lng", "27.5619"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/user"))
                .andExpect(MockMvcResultMatchers.flash().attributeExists("successMessage"));
    }

    @Test
    public void testSaveOrderValidationError() throws Exception {

        doThrow(new ValidationException("Validation error")).when(orderServices).save(any(OrderFormDto.class));
        mockMvc.perform(MockMvcRequestBuilders.post("/order/create")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("startApartment.city", "G")
                        .param("startApartment.street", "Start Street")
                        .param("startApartment.houseNumber", "123")
                        .param("startApartment.entranceNumber", "1")
                        .param("startApartment.flatNumber", "1")
                        .param("finishApartment.city", "Finish City")
                        .param("finishApartment.street", "Finish Street")
                        .param("finishApartment.houseNumber", "456")
                        .param("finishApartment.entranceNumber", "2")
                        .param("finishApartment.flatNumber", "2")
                        .param("deliveryType", "express")
                        .param("orderType", "food")
                        .param("startApartment.lat", "null")
                        .param("startApartment.lng", "37.6176")
                        .param("finishApartment.lat", "53.9023")
                        .param("finishApartment.lng", "27.5619"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("orders/pageOrder"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("error"));
    }
}
