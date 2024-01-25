package com.example.airlinesreservation.controller.mav;

import com.example.airlinesreservation.domain.Flight;
import com.example.airlinesreservation.domain.Passenger;
import com.example.airlinesreservation.domain.Reservation;
import com.example.airlinesreservation.domain.Search;
import com.example.airlinesreservation.service.FlightService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchController {

    @Autowired
    FlightService flightService;



    @RequestMapping("/searchForm")
    public ModelAndView search(Search search, Reservation reservation){
        ModelAndView mav = new ModelAndView("searchForm");

        return mav;
    }

    @RequestMapping("/searchFlight")
    public ModelAndView searchFlight(@ModelAttribute Reservation reservation, @ModelAttribute Search search ){
        ModelAndView mav = new ModelAndView("searchForm");

        List<Flight> flightList = flightService.getAll();
        List<Flight> filteredList = new ArrayList<>();

        String from = search.getFrom();
        String to = search.getTo();
        LocalDate date = search.getDate();

        for(Flight flight : flightList){

            if(from.equals(flight.getDepartureCity()) && to.equals(flight.getArrivalCity())
                    && date.isEqual(flight.getDepartureDate()))
            {
                filteredList.add(flight);
            }
        }

        mav.addObject("flights", filteredList);

        return mav;
    }
}
