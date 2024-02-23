package com.example.airlinesreservation.controller.mav;

import com.example.airlinesreservation.domain.Airline;
import com.example.airlinesreservation.domain.Flight;
import com.example.airlinesreservation.service.AirlineService;
import com.example.airlinesreservation.service.FlightService;
import com.example.airlinesreservation.validation.FlightValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class FlightController {

    @Autowired
    FlightService flightService;

    @Autowired
    AirlineService airlineService;

    @Autowired
    FlightValidator flightValidator;

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.addValidators(flightValidator);
    }


    @RequestMapping(value = "flightForm")
    public ModelAndView flightForm(Flight flight){
        ModelAndView mav = new ModelAndView("flightForm");
        List<Airline> airlineList = airlineService.getAll();

        mav.addObject("flights",flightService.getAll());
        mav.addObject("availableAirlines", airlineList);
        return mav;
    }

    @RequestMapping(value = "saveFlight")
    public ModelAndView saveFlight(@Valid @ModelAttribute Flight flight, BindingResult br){
        List<Airline> airlineList = airlineService.getAll();
        ModelAndView mav = new ModelAndView("flightForm");
        StringBuilder sb = new StringBuilder("");
        if(br.hasErrors()){
            System.out.println("error");
            //error while getting flight info from user
            List<FieldError> fieldErrors = br.getFieldErrors();
            for(FieldError fieldError: fieldErrors){
                sb = sb.append("\""+fieldError.getField() +"\":"+fieldError.getDefaultMessage()+"\n");
            }
            System.out.println("sb: " + sb );

            mav.addObject("hasError",true);
            mav.addObject("flights",flightService.getAll());
            mav.addObject("availableAirlines", airlineList);
            return  mav;
        }
        System.out.println("saving flight " + flight.getFlightAirline());

        flightService.saveFlight(flight);
        mav.setViewName("redirect:flightForm");
        mav.addObject("flights",flightService.getAll());
        mav.addObject("availableAirlines", airlineList);
        return mav;
    }

    @RequestMapping(value = "updateFlight")
    public ModelAndView updateFlight(@Valid @ModelAttribute Flight flight, BindingResult br){
        List<Airline> airlineList = airlineService.getAll();
        ModelAndView mav = new ModelAndView("flightForm");
        StringBuilder sb = new StringBuilder("");
        if(br.hasErrors()){
            System.out.println("error");
            //error while getting flight info from user
            List<FieldError> fieldErrors = br.getFieldErrors();
            for(FieldError fieldError: fieldErrors){
                sb = sb.append("\""+fieldError.getField() +"\":"+fieldError.getDefaultMessage()+"\n");
            }
            System.out.println("sb: " + sb );

            mav.addObject("hasError",true);
            mav.addObject("flights",flightService.getAll());
            mav.addObject("availableAirlines", airlineList);
            return  mav;
        }

        Flight retrievedFlight = flightService.findById(flight.getFlightId());

        retrievedFlight.setFlightAirline(flight.getFlightAirline());
        retrievedFlight.setFlightCapacity(flight.getFlightCapacity());
        retrievedFlight.setFlightNumber(flight.getFlightNumber());
        retrievedFlight.setFlightPrice(flight.getFlightPrice());
        retrievedFlight.setFlightSeatsBooked(flight.getFlightSeatsBooked());
        retrievedFlight.setArrivalCity(flight.getArrivalCity());
        retrievedFlight.setArrivalDate(flight.getArrivalDate());
        retrievedFlight.setArrivalTime(flight.getArrivalTime());
        retrievedFlight.setDepartureCity(flight.getDepartureCity());
        retrievedFlight.setDepartureDate(flight.getDepartureDate());
        retrievedFlight.setDepartureTime(flight.getDepartureTime());

        flightService.updateFlight(retrievedFlight);
        /*
        Things to do
        add object for selected airline
         */
        mav.addObject("flight",retrievedFlight);
        mav.addObject("flights", flightService.getAll());
        mav.addObject("availableAirlines", airlineList);

        return mav;
    }

    @RequestMapping(value = "writeFlight")
    public ModelAndView writeFlight(@RequestParam Long flightId){
        ModelAndView mav = new ModelAndView("flightForm");

        Flight retrievedFlight = flightService.findById(flightId);
        List<Airline> airlineList = airlineService.getAll();

        if(retrievedFlight == null){
            //flight does not exist
            return mav;
        }

        /*
        Things to do
        add object for selected airline
         */
        mav.addObject("flight",retrievedFlight);
        mav.addObject("flights", flightService.getAll());
        mav.addObject("availableAirlines", airlineList);

        return mav;
    }
    @RequestMapping(value = "deleteFlight")
    public ModelAndView deleteFlight(Flight flight){
        ModelAndView mav = new ModelAndView("flightForm");

        Flight retrievedFlight = flightService.findById(flight.getFlightId());
        List<Airline> airlineList = airlineService.getAll();
        if(retrievedFlight == null){
            //flight does not exist
            return mav;
        }

        flightService.deleteById(flight.getFlightId());
        mav.addObject("flights", flightService.getAll());
        mav.addObject("availableAirlines", airlineList);
        return mav;
    }
}
