package com.example.airlinesreservation.controller.mav;

import com.example.airlinesreservation.domain.Gender;
import com.example.airlinesreservation.domain.Passenger;
import com.example.airlinesreservation.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class PassengerController {

    @Autowired
    PassengerService passengerService;

    @Autowired
    LocalContainerEntityManagerFactoryBean entityManager;
    @RequestMapping(value = "/passengerForm")
    public ModelAndView passengerForm(Passenger passenger){

        ModelAndView mav = new ModelAndView("passengerForm");

        mav.addObject("genders", Gender.values());
        mav.addObject("passengers", passengerService.findAll());
        return mav;
    }

    @RequestMapping(value = "/savePassengerByAdmin")
    public ModelAndView savePassengerByAdmin(@ModelAttribute Passenger passenger, BindingResult br){

        ModelAndView mav = new ModelAndView("passengerForm");

        if(br.hasErrors()){
            //error while entering passenger info from user
            System.out.println(passenger.getPassengerId());
            return mav;
        }

        passengerService.savePassenger(passenger);
        mav.setViewName("redirect:passengerForm");
        return mav;
    }

    @RequestMapping(value = "/savePassenger")
    public ModelAndView savePassenger(@ModelAttribute Passenger passenger, BindingResult br){

        ModelAndView mav = new ModelAndView("passengerForm");
        List<Passenger> passengerList = passengerService.findAll();
        passenger.setPassengerId(Long.valueOf(passengerList.size()+1));

        if(br.hasErrors()){
            //error while entering passenger info from user
            System.out.println(passenger.getPassengerId());
            return mav;
        }

        passengerService.savePassenger(passenger);
        mav.setViewName("redirect:passengerForm");
        return mav;
    }

    @RequestMapping(value = "/updatePassenger")
    public ModelAndView updatePassenger(@RequestParam Long passengerId){

        ModelAndView mav = new ModelAndView("passengerForm");

        Passenger retrievedPassenger = passengerService.findById(passengerId);

        if(retrievedPassenger == null){
            //passenger does not exist

            return mav;
        }

        mav.addObject("passenger",retrievedPassenger);
        mav.addObject("genders",Gender.values());
        mav.addObject("passengers",passengerService.findAll());

        return mav;
    }

    @RequestMapping(value = "/deletePassenger")
    public ModelAndView deletePassenger(@RequestParam Long passengerId){

        ModelAndView mav = new ModelAndView("passengerForm");
        Passenger retrievedPassenger = passengerService.findById(passengerId);

        if(retrievedPassenger == null){
            //passenger does not exist

            return mav;
        }

        //passengerService.deleteById(passengerId);
        mav.addObject("passengers",passengerService.findAll());
        return mav;

    }
}
