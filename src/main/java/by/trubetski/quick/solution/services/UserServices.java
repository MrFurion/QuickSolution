package by.trubetski.quick.solution.services;

import by.trubetski.quick.solution.models.Orders;
import by.trubetski.quick.solution.models.User;
import java.util.List;

public interface UserServices {
    /**
     *This method used for search orders of authenticated user
     * according to the received identifier.
     * Also Hibernate.initialize(user.get().getOrders());
     * is used for the explicit initialization of the collection of orders associated with the user object.
     *
     * @return a list of all orders for the user.
     */
    List<Orders> userOrder();

    /**
     * This method used to retrieve the identifier of the current authenticated user.
     * used SecurityContext of Spring Security.
     * Also used AppUserDetail which is  a wrapper for the User entity.
     * @return The user ID as an int.
     */
    public int  getUserId();
    public User findById(int id);
}