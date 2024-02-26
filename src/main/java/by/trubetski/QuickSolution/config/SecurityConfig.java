package by.trubetski.QuickSolution.config;


import by.trubetski.QuickSolution.services.AppUserDetailsServices;
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


/**
 *
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    /**
     * This method provides the UserDetailsService bean,
     * which can be used by Spring Security to process authentication
     * and authorization requests based information obout user.
     * return your "UserDetailsService".
     */
    @Bean
    public UserDetailsService userDetailsService(){
        return new AppUserDetailsServices();
    }


    /**
     * -This method basic settings configuration Spring Security for input form entries,
     * determination the rules authorization and indicate URL for successful and fail authentication.
     * -Method accept and return param HttpSecurity object
     * which provides opportunity to configure settings security.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .formLogin(form-> form
                        .loginPage("/auth/login").permitAll()
                        .loginProcessingUrl("/process_login")
                        .defaultSuccessUrl("/hello", true)
                        .failureUrl("/auth/login?error=true"))
                .authorizeHttpRequests(auth-> auth
                        .requestMatchers("/auth/login", "/error", "/register").permitAll()
                        .anyRequest().authenticated())
                .build();
    }

    /**
     *This method create and settings "AuthenticationManager" which used "DaoAuthenticationProvider"
     * for authentication users where:
     * appUserDetailsServices provides information about users
     * getPasswordEncoder used for verification password on database.
     */
    @Bean
    public AuthenticationManager authenticationManager(AppUserDetailsServices appUserDetailsServices){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(appUserDetailsServices);
        daoAuthenticationProvider.setPasswordEncoder(getPasswordEncoder());
        return new ProviderManager(daoAuthenticationProvider);
    }

    /**
     *This method provides "PasswordEncoder" with used algorithm BCrypt for is hashing password.
     */
    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
