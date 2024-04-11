package by.trubetski.quick.solution.dto;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
public class OrderFormDto {

    private String deliveryType;
    private String orderType;
    @Valid
    private ApartmentDto startApartment;
    @Valid
    private ApartmentDto finishApartment;

    public OrderFormDto() {
    }
}
