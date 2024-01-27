package com.example.airlinesreservation.controller.mav;


import com.example.airlinesreservation.domain.Airport;
import com.example.airlinesreservation.service.AirportService;
import com.example.airlinesreservation.validation.AirportValidator;
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

import java.lang.reflect.Field;
import java.util.List;

@Controller
public class AirportController {

    @Autowired
    AirportService airportService;

    @Autowired
    AirportValidator airportValidator;

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.addValidators(airportValidator);
    }

    @RequestMapping(value = "/airportForm")
    public ModelAndView airportForm(Airport airport){
        ModelAndView mav = new ModelAndView("airportForm");


        mav.addObject("airports", airportService.getAll());
        return mav;
    }

    @RequestMapping(value = "/saveAirport")
    public ModelAndView saveAirport(@Valid @ModelAttribute Airport airport, BindingResult br){

        ModelAndView mav = new ModelAndView("airportForm");
        StringBuilder sb = new StringBuilder();

        if(br.hasErrors()){
            //error while entering airport info from user
            List<FieldError> errors = br.getFieldErrors();

            for(FieldError error : errors){
                sb.append(error.getDefaultMessage() + "\n");
            }

            System.out.println(sb.toString());

            mav.addObject("hasError",true);
            mav.addObject("airports", airportService.getAll());
            return mav;
        }

        airportService.saveAirport(airport);

        mav.setViewName("redirect:airportForm");
        mav.addObject("airports",airportService.getAll());
        return mav;
    }

    @RequestMapping(value = "/updateAirport")
    public ModelAndView updateAirport(@RequestParam Long airportId){
        ModelAndView mav = new ModelAndView("airportForm");

        Airport retrievedAirport = airportService.findById(airportId);

        if(retrievedAirport == null){
            //airport does not exist
            return mav;
        }

        mav.addObject("airport",retrievedAirport);
        mav.addObject("airports",airportService.getAll());
        return mav;
    }

    @RequestMapping(value = "/deleteAirport")
    public ModelAndView deleteAirport(@RequestParam Long airportId){
        ModelAndView mav = new ModelAndView("airportForm");

        Airport retrievedAirport = airportService.findById(airportId);

        if(retrievedAirport == null){
            //airport does not exist
            return mav;
        }

        airportService.deleteById(airportId);
        mav.addObject("airports",airportService.getAll());
        return mav;
    }
}
