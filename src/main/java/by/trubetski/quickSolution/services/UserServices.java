package by.trubetski.quickSolution.services;

import by.trubetski.quickSolution.models.Orders;
import by.trubetski.quickSolution.models.User;
import by.trubetski.quickSolution.repositories.UserRepositories;
import by.trubetski.quickSolution.security.AppUsersDetails;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserServices {
    private final UserRepositories userRepositories;

    @Autowired
    public UserServices(UserRepositories userRepositories) {
        this.userRepositories = userRepositories;
    }

    public List<Orders> userOrder(){
        int userId = getOrderByUserId();
        Optional<User> user = userRepositories.findById(userId);
        if (user.isPresent()){
            Hibernate.initialize(user.get().getOrders());
            return user.get().getOrders();

        }else {
            return Collections.emptyList();
        }

    }

    @Transactional
    public int  getOrderByUserId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AppUsersDetails appUsersDetails = (AppUsersDetails) authentication.getPrincipal();
        int userId = appUsersDetails.getUser().getId();
        System.out.println(userId);
        return userId;
    }
}
