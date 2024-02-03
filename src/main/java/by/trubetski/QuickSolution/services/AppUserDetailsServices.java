package by.trubetski.QuickSolution.services;

import by.trubetski.QuickSolution.models.User;
import by.trubetski.QuickSolution.repositories.UserRepositories;
import by.trubetski.QuickSolution.security.AppUsersDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class AppUserDetailsServices implements UserDetailsService {
    private final UserRepositories userRepositories;


    public AppUserDetailsServices(UserRepositories userRepositories) {
        this.userRepositories = userRepositories;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepositories.findByUsername(username);
        if (user.isEmpty()){
            throw new UsernameNotFoundException("Users not found!");
        }
        return new AppUsersDetails(user.get());
    }
}
