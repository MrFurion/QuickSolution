package by.trubetski.quickSolution.services;

import by.trubetski.quickSolution.models.User;
import by.trubetski.quickSolution.repositories.UserRepositories;
import by.trubetski.quickSolution.security.AppUsersDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * This class performs the function of loading user information from the database based on their username,
 * allowing Spring Security to use this information in the authentication process.
 */
@Service
@Slf4j
public class AppUserDetailsServices implements UserDetailsService {
    @Autowired
    private  UserRepositories userRepositories;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepositories.findByUsername(username);
        if (user.isEmpty()){
            throw new UsernameNotFoundException("incorrect login or password");
        }
        return new AppUsersDetails(user.get());
    }
}
