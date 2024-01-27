package com.example.airlinesreservation.controller.mav;

import com.example.airlinesreservation.domain.Gender;
import com.example.airlinesreservation.domain.Passenger;
import com.example.airlinesreservation.service.PassengerService;
import com.example.airlinesreservation.validation.PassengerValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
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
public class PassengerController {

    @Autowired
    PassengerService passengerService;

    @Autowired
    PassengerValidator passengerValidator;

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.addValidators(passengerValidator);
    }
    @RequestMapping(value = "/passengerForm")
    public ModelAndView passengerForm(Passenger passenger){

        ModelAndView mav = new ModelAndView("passengerForm");

        mav.addObject("genders", Gender.values());
        mav.addObject("passengers", passengerService.findAll());
        return mav;
    }

    @RequestMapping(value = "/savePassengerByAdmin")
    public ModelAndView savePassengerByAdmin(@Valid  @ModelAttribute Passenger passenger, BindingResult br){

        ModelAndView mav = new ModelAndView("passengerForm");
        StringBuilder sb = new StringBuilder();


        if(br.hasErrors()){
            //error while entering passenger info from user
            List<FieldError> errors = br.getFieldErrors();

            for(FieldError error : errors){
                sb.append(error.getDefaultMessage() + "\n") ;
            }

            System.out.println(sb);
            mav.addObject("hasError",true);
            mav.addObject("genders", Gender.values());
            mav.addObject("passengers", passengerService.findAll());
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

            mav.addObject("hasError",true);
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

            mav.addObject("genders", Gender.values());
            mav.addObject("passengers", passengerService.findAll());
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

            mav.addObject("genders", Gender.values());
            mav.addObject("passengers", passengerService.findAll());
            return mav;
        }

        //passengerService.deleteById(passengerId);
        mav.addObject("passengers",passengerService.findAll());
        return mav;

    }
}
