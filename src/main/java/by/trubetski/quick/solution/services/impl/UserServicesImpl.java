package by.trubetski.quick.solution.services.impl;

import by.trubetski.quick.solution.models.Orders;
import by.trubetski.quick.solution.models.User;
import by.trubetski.quick.solution.repositories.UserRepositories;
import by.trubetski.quick.solution.security.AppUsersDetails;
import by.trubetski.quick.solution.services.UserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServicesImpl implements UserServices{
    private final UserRepositories userRepositories;

    /**
     * {@inheritDoc}
     * Search for orders of an authenticated user by the received identifier.
     *
     * @return a list of all orders for the user.
     */
    @Override
    public List<Orders> userOrder(){
        Optional<User> user = userRepositories.findById(getUserId());

        if (user.isPresent()){
            return user.get().getOrders();

        }else {
            return Collections.emptyList();
        }
    }

    /**
     * {@inheritDoc}
     * SecurityContext from Spring Security is used.
     * AppUserDetail is also used, which is a wrapper for the User object.
     *
     * @return The user ID as an int.
     */
    @Override
    public int  getUserId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AppUsersDetails appUsersDetails = (AppUsersDetails) authentication.getPrincipal();
        return appUsersDetails.getUser().getId();
    }

    /**
     * {@inheritDoc}
     * The received indication number is used.
     *
     * @param id
     * @return User by id
     */
    public User findById(int id) {
        Optional<User> user = userRepositories.findById(id);
        return user.orElse(null);
    }

    public List<User> findAllCourier() {
        return StreamSupport.stream(userRepositories.findAll().spliterator(), false)
                .filter(user -> "ROLE_COURIER".equals(user.getRole()))
                .toList();
    }

    public void save(User user) {
        userRepositories.save(user);
    }

}
