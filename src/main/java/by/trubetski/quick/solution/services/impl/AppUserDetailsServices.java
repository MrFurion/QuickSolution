package by.trubetski.quick.solution.services.impl;

import by.trubetski.quick.solution.models.User;
import by.trubetski.quick.solution.repositories.UserRepositories;
import by.trubetski.quick.solution.security.AppUsersDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

/**
 * Downloads user information from the database based on their username,
 * allowing Spring Security to use this information in the authentication process.
 */
@Service
@Slf4j
public class AppUserDetailsServices implements UserDetailsService {

    @Autowired
    private UserRepositories userRepositories;

    /**
     * Load user details by username for authentication purposes.
     *
     * @param username the username to load user details for.
     * @return UserDetails object containing user information.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepositories.findByUsername(username);
        if (user.isEmpty()){
            throw new UsernameNotFoundException("incorrect login or password");
        }
        return new AppUsersDetails(user.get());
    }
    private Collection<? extends GrantedAuthority> getAuthorities(User user) {
        return Collections.singletonList(new SimpleGrantedAuthority(user.getRole()));}

}
