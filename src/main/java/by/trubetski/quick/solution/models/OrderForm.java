package by.trubetski.quick.solution.models;

import jakarta.validation.Valid;
import lombok.Data;

@Data
public class OrderForm {

    private String deliveryType;
    private String orderType;
    @Valid
    private Apartment startApartment;
    @Valid
    private Apartment finishApartment;

    public OrderForm() {
    }

    public OrderForm(String deliveryType, String orderType, Apartment startApartment, Apartment finishApartment) {
        this.deliveryType = deliveryType;
        this.orderType = orderType;
        this.startApartment = startApartment;
        this.finishApartment = finishApartment;
    }
}
