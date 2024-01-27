package com.example.airlinesreservation.validation;

import com.example.airlinesreservation.domain.Airline;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class AirlineValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(Airline.class);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Airline airline = (Airline) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "airlineId", "airline.id.empty","AIRLINE ID MUST BE PRESENT");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "airlineName", "airline.name.empty" ,"AIRLINE NAME MUST BE PRESENT");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "airlineCode", "airline.code.empty", "AIRLINE CODE MUST BE PRESENT");

    }
}
