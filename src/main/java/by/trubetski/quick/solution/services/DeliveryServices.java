package by.trubetski.quick.solution.services;

public interface DeliveryServices {
    /**
     * Update status Delivery by id
     * @param status New status delivery
     * @param id id delivery
     */
    void updateStatus(String status, int id);
}
