package com.example.airlinesreservation.controller.mav;


import com.example.airlinesreservation.domain.Airport;
import com.example.airlinesreservation.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AirportController {

    @Autowired
    AirportService airportService;

    @RequestMapping(value = "/airportForm")
    public ModelAndView airportForm(Airport airport){
        ModelAndView mav = new ModelAndView("airportForm");


        mav.addObject("airports", airportService.getAll());
        return mav;
    }

    @RequestMapping(value = "/saveAirport")
    public ModelAndView saveAirport(@ModelAttribute Airport airport, BindingResult br){

        ModelAndView mav = new ModelAndView("airportForm");

        if(br.hasErrors()){
            //error while entering airport info from user
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
