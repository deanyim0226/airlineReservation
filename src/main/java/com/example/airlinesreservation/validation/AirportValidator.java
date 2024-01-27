package com.example.airlinesreservation.validation;

import com.example.airlinesreservation.domain.Airport;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class AirportValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(Airport.class);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"airportId","airport.id.empty","AIRPORT ID MUST BE PRESENT");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"airportCode","airport.code.empty","AIRPORT CODE MUST BE PRESENT");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"airportName","airport.name.empty","AIRPORT NAME MUST BE PRESENT");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"airportCity","airport.city.empty","AIRPORT CITY MUST BE PRESENT");
    }
}
