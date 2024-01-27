package com.example.airlinesreservation.validation;

import com.example.airlinesreservation.domain.Passenger;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class PassengerValidator implements Validator {


    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(Passenger.class);
    }


    @Override
    public void validate(Object target, Errors errors) {

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"passengerId","passenger.id.empty", "PASSENGER ID MUST BE PRESENT");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"firstName","passenger.firstName.empty", "FIRST NAME MUST BE PRESENT");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"lastName","passenger.lastName.empty", "LAST NAME MUST BE PRESENT");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"email","passenger.email.empty", "EMAIL MUST BE PRESENT");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"gender","passenger.gender.empty", "GENDER MUST BE PRESENT");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"DOB","passenger.DOB.empty", "DOB MUST BE PRESENT");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"address.addressLine1","passenger.firstName.empty", "ADDRESS MUST BE PRESENT");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"address.city","passenger.city.empty", "CITY MUST BE PRESENT");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"address.state","passenger.state.empty", "STATE MUST BE PRESENT");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"address.zipcode","passenger.zipcode.empty", "ZIPCODE MUST BE PRESENT");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"address.country","passenger.country.empty", "COUNTRY MUST BE PRESENT");
    }
}
