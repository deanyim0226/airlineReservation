package com.example.airlinesreservation.controller.mav;

import com.example.airlinesreservation.domain.*;
import com.example.airlinesreservation.service.FlightService;
import com.example.airlinesreservation.validation.SearchValidator;
import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
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

    @Autowired
    SearchValidator searchValidator;

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.addValidators(searchValidator);
    }



    @RequestMapping("/searchForm")
    public ModelAndView search(Search search, Reservation reservation){
        ModelAndView mav = new ModelAndView("searchForm");

        return mav;
    }

    @RequestMapping("/searchFlight")
    public ModelAndView searchFlight(@ModelAttribute Reservation reservation, @Valid @ModelAttribute Search search, BindingResult br){
        ModelAndView mav = new ModelAndView("searchForm");
        StringBuilder sb = new StringBuilder();


        List<Flight> flightList = flightService.getAll();
        List<Flight> filteredList = new ArrayList<>();

        String from = search.getFrom();
        String to = search.getTo();
        LocalDate date = search.getDate();

        if(br.hasErrors()){
            List<FieldError> errors = br.getFieldErrors();
            for(FieldError error : errors){
                sb.append(error.getDefaultMessage() + "\n");
            }

            System.out.println(sb.toString());
            mav.addObject("hasError",true);

        }

        for(Flight flight : flightList){

            if(from.equals(flight.getDepartureCity()) && to.equals(flight.getArrivalCity())
                    && date.isEqual(flight.getDepartureDate()))
            {
                filteredList.add(flight);
            }
        }

        mav.addObject("flights", filteredList);
        mav.addObject("genders", Gender.values());

        return mav;
    }
}
