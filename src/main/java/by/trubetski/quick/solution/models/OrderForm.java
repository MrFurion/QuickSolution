package by.trubetski.quick.solution.models;

import lombok.Data;




@Data
public class OrderForm {

    private String deliveryType;
    private String orderType;
    private String startCity;
    private String startStreet;
    private int startHouseNumber;
    private int startEntranceNumber;
    private int startFlatNumber;
    private Double startLat;
    private Double startLng;
    private String finishCity;
    private String finishStreet;
    private int finishHouseNumber;
    private int finishEntranceNumber;
    private int finishFlatNumber;
    private Double endLat;
    private Double endLng;

    public OrderForm() {
    }

    public OrderForm(String startCity, String finishCity) {
        this.startCity = startCity;
        this.finishCity = finishCity;
    }
}
