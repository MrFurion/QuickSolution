package by.trubetski.quick.solution.models;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;




@Data
public class OrderForm {

    private String deliveryType;
    private String orderType;
    @NotEmpty
    @Size(min = 2, max = 15, message = "City should be between 2 and 15 character")
    private String startCity;
    @NotEmpty
    @Size(min = 2, max = 15, message = "Street should be between 2 and 15 character")
    private String startStreet;
    @NotEmpty
    @Size(min = 1, max = 10, message = " House number should be between 1 and 10character")
    private String startHouseNumber;
    @NotEmpty
    @Size(min = 1, max = 10, message = "Entrance should be between 1 and 10 character")
    private String startEntranceNumber;
    @NotEmpty
    @Size(min = 1, max = 10, message = "Flat number should be between 1 and 10 character")
    private String startFlatNumber;
    @NotNull(message = "Please indicate the starting point of delivery on the map")
    private Double startLat;
    @NotNull(message = "The map is located below")
    private Double startLng;
    @NotEmpty
    @Size(min = 2, max = 15, message = "City should be between 2 and 15 character")
    private String finishCity;
    @NotEmpty
    @Size(min = 2, max = 15, message = "Street should be between 2 and 15 character")
    private String finishStreet;
    @NotEmpty
    @Size(min = 1, max = 10, message = " House number should be between 1 and 10character")
    private String finishHouseNumber;
    @NotEmpty
    @Size(min = 1, max = 10, message = "Entrance should be between 1 and 10 character!")
    private String finishEntranceNumber;
    @NotEmpty
    @Size(min = 1, max = 10, message = "Flat number should be between 1 and 10 character!")
    private String finishFlatNumber;
    @NotNull(message = "Please indicate the final delivery point on the map!")
    private Double endLat;
    @NotNull(message = "The map is located below!")
    private Double endLng;

    public OrderForm() {
    }

    public OrderForm(String startCity, String finishCity) {
        this.startCity = startCity;
        this.finishCity = finishCity;
    }
}
