package com.example.airlinesreservation.validation;

import com.example.airlinesreservation.domain.User;
import com.example.airlinesreservation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Autowired
    UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        System.out.println("validator is called");
        User user = (User) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"userId", "user.userId.empty", "USERID MUST NOT BE EMPTY");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"username", "user.username.empty", "USERNAME MUST NOT BE EMPTY");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"password", "user.password.empty", "PASSWORD MUST NOT BE EMPTY");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"email", "user.email.empty", "EMAIL MUST NOT BE EMPTY");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"mobileNo", "user.mobileNo.empty", "MOBILE_NO MUST NOT BE EMPTY");

        if(user.getRoles().isEmpty()){
            errors.rejectValue("roles","user.roles.empty", "ROLE MUST NOT BE EMPTY");
        }
    }
}
