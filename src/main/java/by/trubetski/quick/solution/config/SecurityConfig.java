package by.trubetski.quick.solution.config;

import by.trubetski.quick.solution.services.impl.AppUserDetailsServices;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    /**
     * Provides the UserDetailsService bean,
     * which can be used by Spring Security to process authentication
     * and authorization requests based information obout user.
     * return your "UserDetailsService".
     *
     * @return appUserDetailsServices new object your custom class
     */
    @Bean
    public UserDetailsService userDetailsService(){
        return new AppUserDetailsServices();
    }


    /**
     * Configuration Spring Security for input form entries,
     * determination the rules authorization and indicate URL for successful and fail authentication.
     * Method accept and return param HttpSecurity object
     * which provides opportunity to configure settings security.
     *
     * @param http the HttpSecurity object for configuring security settings.
     * @return the configured HttpSecurity object.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .formLogin(form-> form
                        .loginPage("/auth/login").permitAll()
                        .loginProcessingUrl("/process_login")
                        .defaultSuccessUrl("/user", true)
                        .failureUrl("/auth/login?error=true"))
                .authorizeHttpRequests(auth-> auth
                        .requestMatchers("/auth/login", "/error", "/register","/logout",
                                "/static/css/**").permitAll()
                        .anyRequest().authenticated())
                .logout((logout)-> logout
                        .logoutSuccessUrl("/auth/login"))
                .build();
    }

    /**
     * Create and settings "AuthenticationManager" which used "DaoAuthenticationProvider"
     * for authentication users where:
     * appUserDetailsServices provides information about users
     * getPasswordEncoder used for verification password on database.
     *
     * @param appUserDetailsServices the instance of {@link AppUserDetailsServices} that provides information about users
     * @return the {@link PasswordEncoder} used for password verification in the database
     */
    @Bean
    public AuthenticationManager authenticationManager(AppUserDetailsServices appUserDetailsServices){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(appUserDetailsServices);
        daoAuthenticationProvider.setPasswordEncoder(getPasswordEncoder());
        return new ProviderManager(daoAuthenticationProvider);
    }

    /**
     * Provides "PasswordEncoder" with used algorithm BCrypt for is hashing password.
     * @return the {@link BCryptPasswordEncoder} instance for password hashing
     */
    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
