package by.trubetski.quick.solution.util.enums;

public enum CourierStatus {
    Free("Free"),
    Busy("Busy");

    private final String statusName;

    CourierStatus(String statusName) {
        this.statusName = statusName;
    }
    public String getStatusName() {
        return statusName;
    }
}
