package by.trubetski.quick.solution.services.impl;

import by.trubetski.quick.solution.models.Orders;
import by.trubetski.quick.solution.models.User;
import by.trubetski.quick.solution.repositories.UserRepositories;
import by.trubetski.quick.solution.security.AppUsersDetails;
import by.trubetski.quick.solution.services.UserServices;
import by.trubetski.quick.solution.util.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServicesImpl implements UserServices {
    private final UserRepositories userRepositories;

    @Override
    public List<Orders> userOrder() {
        Optional<User> user = userRepositories.findById(getUserId());

        if (user.isPresent()) {
            return user.get().getOrders();

        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public int getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AppUsersDetails appUsersDetails = (AppUsersDetails) authentication.getPrincipal();
        return appUsersDetails.getUser().getId();
    }

    public User findById(int id) {
        Optional<User> user = userRepositories.findById(id);
        return user.orElse(null);
    }

    public List<User> findAllCourier() {
        return userRepositories.getUserByRole(Role.ROLE_COURIER.getRoleName());
    }

    public void save(User user) {
        userRepositories.save(user);
    }
}
