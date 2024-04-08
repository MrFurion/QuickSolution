package by.trubetski.quick.solution.dto;

import jakarta.validation.Valid;
import lombok.Data;

@Data
public class OrderFormDto {

    private String deliveryType;
    private String orderType;
    @Valid
    private ApartmentDto startApartment;
    @Valid
    private ApartmentDto finishApartment;

    public OrderFormDto() {
    }

    public OrderFormDto(String deliveryType, String orderType, ApartmentDto startApartment, ApartmentDto finishApartment) {
        this.deliveryType = deliveryType;
        this.orderType = orderType;
        this.startApartment = startApartment;
        this.finishApartment = finishApartment;
    }
}
