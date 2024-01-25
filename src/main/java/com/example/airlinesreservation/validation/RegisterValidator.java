package com.example.airlinesreservation.validation;

import com.example.airlinesreservation.domain.User;
import com.example.airlinesreservation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class RegisterValidator implements Validator {
    @Autowired
    UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        System.out.println("register validator is called");
        User user = (User) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"username", "user.username.empty", "USERNAME MUST NOT BE EMPTY");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"password", "user.password.empty", "PASSWORD MUST NOT BE EMPTY");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"email", "user.email.empty", "EMAIL MUST NOT BE EMPTY");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"mobileNo", "user.mobileNo.empty", "MOBILE_NO MUST NOT BE EMPTY");

    }
}
