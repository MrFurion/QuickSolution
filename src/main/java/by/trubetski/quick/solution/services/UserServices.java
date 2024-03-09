package by.trubetski.quick.solution.services;

import by.trubetski.quick.solution.models.Orders;
import by.trubetski.quick.solution.models.User;
import by.trubetski.quick.solution.repositories.UserRepositories;
import by.trubetski.quick.solution.security.AppUsersDetails;
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

    /**
     *This method used for search orders of authenticated user
     * according to the received identifier.
     * Also Hibernate.initialize(user.get().getOrders());
     * is used for the explicit initialization of the collection of orders associated with the user object.
     * @return a list of all orders for the user.
     */
    public List<Orders> userOrder(){
        int userId = getUserId();
        Optional<User> user = userRepositories.findById(userId);
        if (user.isPresent()){
            Hibernate.initialize(user.get().getOrders());
            return user.get().getOrders();

        }else {
            return Collections.emptyList();
        }

    }

    /**
     * This method used to retrieve the identifier of the current authenticated user.
     * Hi used SecurityContext of Spring Security.
     * Also used AppUserDetail which is  a wrapper for the User entity.
     * @return The user ID as an int.
     */
    @Transactional
    public int  getUserId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AppUsersDetails appUsersDetails = (AppUsersDetails) authentication.getPrincipal();
        return appUsersDetails.getUser().getId();
    }
}
