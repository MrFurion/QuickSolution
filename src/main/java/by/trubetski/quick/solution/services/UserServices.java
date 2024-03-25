package by.trubetski.quick.solution.services;

import by.trubetski.quick.solution.models.Orders;
import by.trubetski.quick.solution.models.User;

import java.util.List;

public interface UserServices {
    List<Orders> userOrder();
    public int  getUserId();
    public User findById(int id);
}
