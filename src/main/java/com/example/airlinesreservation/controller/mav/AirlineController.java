package com.example.airlinesreservation.controller.mav;

import com.example.airlinesreservation.domain.Airline;
import com.example.airlinesreservation.service.AirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AirlineController {

    @Autowired
    AirlineService airlineService;

    @RequestMapping(value = "/airlineForm")
    public ModelAndView airportForm(Airline airline){
        ModelAndView mav = new ModelAndView("airlineForm");


        mav.addObject("airlines", airlineService.getAll());
        return mav;
    }

    @RequestMapping(value = "/saveAirline")
    public ModelAndView saveAirport(@ModelAttribute Airline airline, BindingResult br){

        ModelAndView mav = new ModelAndView("airlineForm");

        if(br.hasErrors()){
            //error while entering airport info from user
            return mav;
        }

        airlineService.saveAirline(airline);

        mav.setViewName("redirect:airlineForm");
        mav.addObject("airlines",airlineService.getAll());
        return mav;
    }

    @RequestMapping(value = "/updateAirline")
    public ModelAndView updateAirport(@RequestParam Long airlineId){
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
    public ModelAndView deleteAirport(@RequestParam Long airlineId){
        ModelAndView mav = new ModelAndView("airlineForm");

        Airline retrievedAirline = airlineService.findById(airlineId);

        if(retrievedAirline == null){
            //airport does not exist
            return mav;
        }

        airlineService.deleteById(airlineId);
        mav.addObject("airlines",airlineService.getAll());
        return mav;
    }
}
