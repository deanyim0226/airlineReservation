package com.example.airlinesreservation.controller.mav;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

@Controller
public class LoginController {

    @RequestMapping({"/home","/"})
    public ModelAndView home(){

        ModelAndView mav = new ModelAndView("home");


        return mav;
    }
}
