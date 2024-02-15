package com.example.airlinesreservation.validation;

import com.example.airlinesreservation.domain.Search;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.time.LocalDate;


@Component
public class SearchValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(Search.class);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Search search = (Search) target;

        String email = search.getPassengerEmail();
        Long reservationNumber = search.getReservationNumber();

        String departure = search.getFrom();
        String arrival = search.getTo();
        LocalDate date = search.getDate();

        if(departure == null && arrival == null && date == null){
            //search reservation

            if(reservationNumber == null && email == ""){
                errors.rejectValue("reservationNumber","search.reservationNumber", "EITHER RESERVATION NUMBER OR EMAIL SHOULD BE PRESENT");
                errors.rejectValue("passengerEmail","search.email", "EITHER EMAIL OR RESERVATION NUMBER SHOULD BE PRESENT");

            }

        }else if(email == null && reservationNumber == null){
            //search flight

            if(departure == "" && arrival == ""){
                if(date == null){
                    errors.rejectValue("date","search.date","DATE SHOULD BE PRESENT");
                }
            }

            if(departure.length() > 0 && arrival.length() > 0 && departure.equals(arrival)){
                errors.rejectValue("from","search.from", "DEPARTURE AND ARRIVAL CAN NOT BE SAME");
                errors.rejectValue("to","search.to", "DEPARTURE AND ARRIVAL CAN NOT BE SAME");
            }
        }








    }
}
