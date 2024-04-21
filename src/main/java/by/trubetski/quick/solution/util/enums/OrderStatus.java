package by.trubetski.quick.solution.util.enums;

public enum OrderStatus {
    NEW("New"),
    PROCESSING("Processing"),
    CONFIRMED("Confirmed"),
    CANCELLED("Cancelled"),
    IN_TRANSIT("In transit"),
    DELIVERED("Delivered"),
    COMPLETED("Completed");
    private final String statusName;

    OrderStatus(String statusName) {
        this.statusName = statusName;
    }

    public String getStatusName() {
        return statusName;
    }
}
