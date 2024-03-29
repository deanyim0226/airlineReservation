package com.example.airlinesreservation.controller.mav;

import com.example.airlinesreservation.domain.Gender;
import com.example.airlinesreservation.domain.Passenger;
import com.example.airlinesreservation.service.PassengerService;
import com.example.airlinesreservation.validation.PassengerValidator;
import jakarta.validation.Valid;
import org.eclipse.tags.shaded.org.apache.xpath.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

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
        mav.addObject("sortedBy", List.of("FirstName", "LastName","Email","Gender","DOB"));
        return mav;
    }

    @RequestMapping(value = "/pagedPassengers")
    public ModelAndView findPassengers(@RequestParam int pageNo, @RequestParam int pageSize, @RequestParam(required=false) String sortedBy){

        ModelAndView mav = new ModelAndView("pagedPassengers");
        Pageable pageable = null;

        pageable = PageRequest.of(pageNo,pageSize, Sort.by(sortedBy));
        Page<Passenger> pagedPassengers = passengerService.findPassengers(pageable);
        List<Passenger> passengerList = pagedPassengers.getContent();

        mav.addObject("passengers", passengerList);
        mav.addObject("totalPages", pagedPassengers.getTotalPages());
        mav.addObject("pageSize", pageSize);
        mav.addObject("sortedBy", sortedBy);

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
    public ModelAndView updatePassenger(@ModelAttribute Passenger passenger, BindingResult br){

        ModelAndView mav = new ModelAndView("passengerForm");
        List<Passenger> passengerList = passengerService.findAll();

        if(br.hasErrors()){
            //error while entering passenger info from user
            System.out.println(passenger.getPassengerId());

            mav.addObject("hasError",true);
            return mav;
        }

        Passenger retrievedPassenger = passengerService.findById(passenger.getPassengerId());

        retrievedPassenger.setFirstName(passenger.getFirstName());
        retrievedPassenger.setLastName(passenger.getLastName());
        retrievedPassenger.setGender(passenger.getGender());
        retrievedPassenger.setDOB(passenger.getDOB());
        retrievedPassenger.setEmail(passenger.getEmail());
        retrievedPassenger.setAddress(passenger.getAddress());
        passengerService.updatePassenger(retrievedPassenger);
        mav.setViewName("redirect:passengerForm");
        return mav;
    }

    @RequestMapping(value = "/generatePassenger")
    public ModelAndView generatePassenger(@RequestParam Long passengerId){

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
        mav.addObject("sortedBy", List.of("FirstName", "LastName","Email","Gender","DOB"));
        return mav;
    }

    @RequestMapping(value = "/deletePassenger")
    public ModelAndView deletePassenger(Passenger passenger){

        ModelAndView mav = new ModelAndView("passengerForm");
        Passenger retrievedPassenger = passengerService.findById(passenger.getPassengerId());

        if(retrievedPassenger == null){
            //passenger does not exist

            mav.addObject("genders", Gender.values());
            mav.addObject("passengers", passengerService.findAll());
            return mav;
        }

        passengerService.deleteById(passenger.getPassengerId());
        mav.addObject("passengers",passengerService.findAll());
        return mav;

    }
}
