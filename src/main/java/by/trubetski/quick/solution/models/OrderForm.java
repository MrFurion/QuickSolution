package by.trubetski.quick.solution.models;

import lombok.Data;

@Data
public class OrderForm {
    private String startAddress;
    private String finishAddress;
    private String orderName;

    public OrderForm() {
    }

    public OrderForm(String startAddress, String finishAddress, String orderName) {
        this.startAddress = startAddress;
        this.finishAddress = finishAddress;
        this.orderName = orderName;
    }
}
