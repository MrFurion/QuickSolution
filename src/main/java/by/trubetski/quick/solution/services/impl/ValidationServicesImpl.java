package by.trubetski.quick.solution.services.impl;

import by.trubetski.quick.solution.services.ValidationServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

@Service
@Slf4j
public class ValidationServicesImpl implements ValidationServices {


    private final Validator validator;
    @Autowired
    public ValidationServicesImpl(@Qualifier(value = "defaultValidator") Validator validator) {
        this.validator = validator;
    }

    @Override
    public <T> BindingResult validate(T object) {
        BindingResult bindingResult = new BeanPropertyBindingResult(object, "object");
        validator.validate(object, bindingResult);
        log.info(validator.toString());
        return bindingResult;
    }
}
