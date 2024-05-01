package by.trubetski.quick.solution.services;

import by.trubetski.quick.solution.models.Orders;
import by.trubetski.quick.solution.models.User;

import java.util.List;

public interface UserServices {
    /**
     * Get all the user's available orders.
     * Search for orders of an authenticated user by the received identifier.
     *
     * @return a list of all orders for the user.
     */
    List<Orders> userOrder();

    /**
     * Get ID of currently authenticated user.
     * SecurityContext from Spring Security is used.
     * AppUserDetail is also used, which is a wrapper for the User object.
     *
     * @return The user ID as an int.
     */
    int getUserId();

    /**
     * Find a user by id.
     * The received indication number is used.
     *
     * @param id User ID
     * @return User by id
     */
    User findById(int id);

    /**
     * Find all User with role {@Link Role.ROLE_COURIER}.
     *
     * @return a List of user with role courier.
     */
    List<User> findAllCourier();
}
