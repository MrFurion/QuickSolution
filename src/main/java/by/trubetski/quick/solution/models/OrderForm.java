package by.trubetski.quick.solution.models;

import lombok.Data;

@Data
public class OrderForm {
    private String startAddress;
    private String finishAddress;
    private String orderName;
    private Double StartLat;
    private Double StartLng;
    private Double EndLat;
    private Double EndLng;

    public OrderForm() {
    }

    public OrderForm(String startAddress, String finishAddress, String orderName) {
        this.startAddress = startAddress;
        this.finishAddress = finishAddress;
        this.orderName = orderName;
    }
}
