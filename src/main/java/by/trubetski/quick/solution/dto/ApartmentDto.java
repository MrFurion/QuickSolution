package by.trubetski.quick.solution.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class ApartmentDto {

    @NotEmpty
    @Size(min = 2, max = 15, message = "City should be between 2 and 15 characters")
    private String city;
    @NotEmpty
    @Size(min = 2, max = 15, message = "Street should be between 2 and 15 characters")
    private String street;
    @NotEmpty
    @Size(min = 1, max = 10, message = " House number should be between 1 and 10characters")
    private String houseNumber;
    @NotEmpty
    @Size(min = 1, max = 15, message = "Entrance should be between 1 and 15 characters")
    private String entranceNumber;
    @NotEmpty
    @Size(min = 1, max = 15, message = "Flat number should be between 1 and 15 characters")
    private String flatNumber;
    @NotNull(message = "Please indicate the starting point of delivery on the map")
    private Double lat;
    @NotNull(message = "The map is located below")
    private Double lng;

    public ApartmentDto() {
    }
}
