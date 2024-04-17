package by.trubetski.quick.solution.services;

import by.trubetski.quick.solution.models.Orders;
import by.trubetski.quick.solution.models.User;
import java.util.List;

public interface UserServices {
    /**
     *Get all the user's available orders.
     */
    List<Orders> userOrder();

    /**
     * Get the user's registration number.
     */
    int  getUserId();

    /**
     *Find a user by id.
     */
    User findById(int id);

    /**
     *Find all User
     */
    List<User> findAllCourier();
}
