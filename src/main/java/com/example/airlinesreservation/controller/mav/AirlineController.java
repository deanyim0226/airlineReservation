package com.example.airlinesreservation.controller.mav;

import com.example.airlinesreservation.domain.Airline;
import com.example.airlinesreservation.domain.Flight;
import com.example.airlinesreservation.service.AirlineService;
import com.example.airlinesreservation.service.FlightService;
import com.example.airlinesreservation.validation.AirlineValidator;
import jakarta.validation.Valid;
import org.eclipse.tags.shaded.org.apache.bcel.verifier.structurals.ControlFlowGraph;
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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class AirlineController {

    @Autowired
    AirlineService airlineService;

    @Autowired
    FlightService flightService;

    @Autowired
    AirlineValidator airlineValidator;

    @InitBinder
    public void initBinder(WebDataBinder binder){

        binder.addValidators(airlineValidator);
    }

    @RequestMapping(value = "/airlineForm")
    public ModelAndView airlineForm(Airline airline){
        ModelAndView mav = new ModelAndView("airlineForm");

        List<Flight> flightList = flightService.getAll();

        List<Airline> airlineList = airlineService.getAll();
        Set<Flight> matchedFlightList = new HashSet<>();


        mav.addObject("airlines", airlineService.getAll());
        return mav;
    }

    @RequestMapping(value = "/saveAirline")
    public ModelAndView saveAirline(@Valid @ModelAttribute Airline airline, BindingResult br){

        ModelAndView mav = new ModelAndView("airlineForm");
        StringBuilder sb = new StringBuilder();

        if(br.hasErrors()){
            //error while entering airport info from user
            List<FieldError> errors =  br.getFieldErrors();

            for(FieldError error : errors){
                sb.append(error.getDefaultMessage() + "\n");
            }

            System.out.println(sb.toString());
            mav.addObject("hasError",true);
            return mav;
        }

        airlineService.saveAirline(airline);

        mav.setViewName("redirect:airlineForm");
        mav.addObject("airlines",airlineService.getAll());
        return mav;
    }

    @RequestMapping(value = "/updateAirline")
    public ModelAndView updateAirline(@Valid @ModelAttribute Airline airline, BindingResult br){

        ModelAndView mav = new ModelAndView("airlineForm");
        StringBuilder sb = new StringBuilder();

        if(br.hasErrors()){
            //error while entering airport info from user
            List<FieldError> errors =  br.getFieldErrors();

            for(FieldError error : errors){
                sb.append(error.getDefaultMessage() + "\n");
            }

            System.out.println(sb.toString());
            mav.addObject("hasError",true);
            return mav;
        }

        Airline retrievedAirline = airlineService.findById(airline.getAirlineId());
        retrievedAirline.setAirlineName(airline.getAirlineName());
        retrievedAirline.setAirlineCode(airline.getAirlineCode());
        retrievedAirline.setAirlineFlight(airline.getAirlineFlight());
        airlineService.updateAirline(retrievedAirline);

        mav.setViewName("redirect:airlineForm");
        mav.addObject("airlines",airlineService.getAll());
        return mav;
    }

    @RequestMapping(value = "/generateAirline")
    public ModelAndView generateAirline(@RequestParam Long airlineId){
        ModelAndView mav = new ModelAndView("airlineForm");

        Airline retrievedAirline = airlineService.findById(airlineId);

        if(retrievedAirline == null){
            //airport does not exist
            return mav;
        }

        mav.addObject("airline",retrievedAirline);
        mav.addObject("airlines",airlineService.getAll());
        return mav;
    }

    @RequestMapping(value = "/deleteAirline")
    public ModelAndView deleteAirline(Airline airline){
        ModelAndView mav = new ModelAndView("airlineForm");

        Airline retrievedAirline = airlineService.findById(airline.getAirlineId());

        if(retrievedAirline == null){
            //airport does not exist
            return mav;
        }

        airlineService.deleteById(airline.getAirlineId());
        mav.addObject("airlines",airlineService.getAll());
        return mav;
    }
}
