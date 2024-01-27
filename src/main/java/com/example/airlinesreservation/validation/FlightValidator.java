package com.example.airlinesreservation.validation;

import com.example.airlinesreservation.domain.Flight;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.time.LocalDate;

@Component
public class FlightValidator implements Validator {


    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(Flight.class);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Flight flight = (Flight) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"flightId","flight.id.empty", "FLIGHT ID MUST BE PRESENT");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"flightNumber","flight.number.empty", "FLIGHT NUMBER MUST BE PRESENT");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"arrivalCity","flight.arrivalCity.empty", "ARRIVAL MUST BE PRESENT");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"departureCity","flight.departureCity.empty", "DEPARTURE MUST BE PRESENT");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"departureDate","flight.departureDate.empty", "DEPARTURE Date MUST BE PRESENT");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"departureTime","flight.departureTime.empty", "DEPARTURE Time MUST BE PRESENT");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"arrivalDate","flight.arrivalDate.empty", "ARRIVAL Date MUST BE PRESENT");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"arrivalTime","flight.arrivalTime.empty", "ARRIVAL Time MUST BE PRESENT");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"flightCapacity","flight.flightCapacity.empty", "CAPACITY MUST BE PRESENT");


        String departure = flight.getDepartureCity();;
        String arrival = flight.getArrivalCity();

        if(departure.length() > 0 && arrival.length() > 0 && departure.equals(arrival)){
            errors.rejectValue("departureCity","flight.departureCity.duplicate", "ARRIVAL AND DEPARTURE CITIES SHOULD BE DIFFERENT");
            errors.rejectValue("arrivalCity","flight.arrivalCity.duplicate", "ARRIVAL AND DEPARTURE CITIES SHOULD BE DIFFERENT");
        }

        LocalDate departureDate = flight.getDepartureDate();

        LocalDate arrivalDate = flight.getArrivalDate();

        if(arrivalDate != null && departureDate != null && arrivalDate.isBefore(departureDate)){
            errors.rejectValue("arrivalDate", "flight.arrivalDate.after", "ARRIVAL DATE SHOULD BE AFTER DEPARTURE DATE");
        }

        if(arrivalDate != null && departureDate != null && departureDate.isAfter(arrivalDate)){
            errors.rejectValue("departureDate", "flight.departureDate.after", "DEPARTURE DATE SHOULD BE BEFORE ARRIVAL DATE");
        }

    }
}
