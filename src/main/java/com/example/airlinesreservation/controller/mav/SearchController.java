package com.example.airlinesreservation.controller.mav;

import com.example.airlinesreservation.domain.*;
import com.example.airlinesreservation.service.FlightService;
import com.example.airlinesreservation.service.ReservationService;
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
    ReservationService reservationService;

    @Autowired
    SearchValidator searchValidator;

    List<Flight> filteredList;
    List<Reservation> foundReservationList;

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.addValidators(searchValidator);
    }

    @RequestMapping("/searchReservationResult")
    public ModelAndView searchReservationResult(Search search, Reservation reservation){
        ModelAndView mav = new ModelAndView("searchReservationResult");


        mav.addObject("reservations", foundReservationList);

        return mav;
    }
    @RequestMapping("/searchReservation")
    public ModelAndView searchReservation(@Valid @ModelAttribute Search search, BindingResult br){
        ModelAndView mav = new ModelAndView("searchReservationForm");
        //admin only to make check in for users
        List<Reservation> reservationList = reservationService.getAll();
        foundReservationList = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        Long reservationNumber = search.getReservationNumber();
        String email = search.getPassengerEmail();

        if(br.hasErrors()){
            List<FieldError> errors = br.getFieldErrors();
            for(FieldError error : errors){
                sb.append(error.getDefaultMessage() + "\n");
            }

            System.out.println(sb.toString());
            mav.addObject("hasError",true);
            return mav;
        }
        for(Reservation rev : reservationList){
            Passenger passenger = rev.getPassenger();
            if(reservationNumber == rev.getReservationNumber()){
                foundReservationList.add(rev);
                break;

            }else if(email.equalsIgnoreCase(passenger.getEmail())){
                foundReservationList.add(rev);
                //based on email
            }

        }

        mav.setViewName("redirect:searchReservationResult");
        return mav;
    }

    @RequestMapping("/searchReservationForm")
    public ModelAndView reservationForm(Search search, Reservation reservation){
        ModelAndView mav = new ModelAndView("searchReservationForm");

        return mav;
    }
    @RequestMapping("/searchFlightResult")
    public ModelAndView searchResult(Search search, Reservation reservation){
        ModelAndView mav = new ModelAndView("searchFlightResult");
        mav.addObject("flights", filteredList);
        mav.addObject("genders", Gender.values());
        return mav;
    }

    @RequestMapping("/searchFlightForm")
    public ModelAndView search(Search search, Reservation reservation){
        ModelAndView mav = new ModelAndView("searchFlightForm");

        return mav;
    }

    @RequestMapping("/searchFlight")
    public ModelAndView searchFlight(@Valid @ModelAttribute Search search, BindingResult br){
        ModelAndView mav = new ModelAndView("searchFlightForm");
        StringBuilder sb = new StringBuilder();

        List<Flight> flightList = flightService.getAll();
        filteredList = new ArrayList<>();

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

            return  mav;
        }


        if(date == null && !from.isEmpty() && to.isEmpty()){

            for(Flight flight : flightList){

                if(from.equalsIgnoreCase(flight.getDepartureCity())){
                    filteredList.add(flight);
                }
            }

        }else if(date == null && !to.isEmpty() && from.isEmpty()){
            for(Flight flight : flightList){
                if(to.equalsIgnoreCase(flight.getArrivalCity())){
                    filteredList.add(flight);
                }
            }
        }else if(date == null && !from.isEmpty() && !to.isEmpty()){
            for(Flight flight : flightList){
                if(to.equalsIgnoreCase(flight.getArrivalCity()) && from.equalsIgnoreCase(flight.getDepartureCity())){
                    filteredList.add(flight);
                }
            }
        }else if(date != null && from.isEmpty() && to.isEmpty()){
            for(Flight flight : flightList){
                if(date.equals(flight.getDepartureDate())){
                    filteredList.add(flight);
                }
            }
        }else if(date != null && !from.isEmpty() && to.isEmpty()){
            for(Flight flight : flightList){
                if(date.equals(flight.getDepartureDate()) && from.equalsIgnoreCase(flight.getDepartureCity())){
                    filteredList.add(flight);
                }
            }
        }else if(date != null && !to.isEmpty() && from.isEmpty()){
            for(Flight flight : flightList){
                if(date.equals(flight.getDepartureDate()) && to.equalsIgnoreCase(flight.getArrivalCity())){
                    filteredList.add(flight);
                }
            }
        }else{
            for(Flight flight : flightList){

                if(from.equalsIgnoreCase(flight.getDepartureCity()) && to.equalsIgnoreCase(flight.getArrivalCity())
                        && date.isEqual(flight.getDepartureDate()))
                {
                    filteredList.add(flight);
                }
            }
        }



        mav = new ModelAndView("redirect:searchFlightResult");
        return mav;
    }
}
