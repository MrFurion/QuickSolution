package by.trubetski.quick.solution.util.enums;

public enum DeliveryStatus {
    FREE("Free"),
    IN_TRANSIT("In transit"),
    DELIVERY_STATUS("Delivered");
    private final String statusName;
    DeliveryStatus(String statusName) {
        this.statusName = statusName;
    }
    public String getStatusName(){
        return statusName;
    }
}
