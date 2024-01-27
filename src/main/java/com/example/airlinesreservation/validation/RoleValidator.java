package com.example.airlinesreservation.validation;

import com.example.airlinesreservation.domain.Role;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class RoleValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(Role.class);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Role role = (Role) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"roleId", "role.userId.empty", "USERID MUST NOT BE EMPTY");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"roleName", "role.name.empty", "ROLENAME MUST NOT BE EMPTY");

    }
}
