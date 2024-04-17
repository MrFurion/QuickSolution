package by.trubetski.quick.solution.util.enums;

public enum OrderStatus {
    STATUS_NEW("New"),
    STATUS_PROCESSING("Processing"),
    STATUS_CONFIRMED("Confirmed"),
    STATUS_CANCELLED("Cancelled"),
    STATUS_IN_TRANSIT("In transit"),
    STATUS_DELIVERED("Delivered"),
    STATUS_COMPLETED("Completed")
    ;
    private final String statusName;

    OrderStatus(String statusName) {
        this.statusName = statusName;
    }
    public String getStatusName(){
        return statusName;
    }
}
