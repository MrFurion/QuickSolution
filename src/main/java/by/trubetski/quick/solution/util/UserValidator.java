package by.trubetski.quick.solution.util;

import by.trubetski.quick.solution.models.User;
import by.trubetski.quick.solution.services.RegistrationServices;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    private final RegistrationServices registrationServices;

    public UserValidator(RegistrationServices registrationServices) {
        this.registrationServices = registrationServices;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        if (registrationServices.getPersonByUsername(user.getUsername()).isPresent())
            errors.rejectValue("username", "", "Username already exist");
        if(registrationServices.getPersonByEmail(user.getEmail()).isPresent())
            errors.rejectValue("email", "", "Email already exists");
    }
}
