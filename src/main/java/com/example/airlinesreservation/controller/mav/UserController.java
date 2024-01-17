package com.example.airlinesreservation.controller.mav;

import com.example.airlinesreservation.domain.User;
import com.example.airlinesreservation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/userForm")
    public ModelAndView userForm(User user){

        ModelAndView mav = new ModelAndView("userForm");

        mav.addObject("users", userService.findAll());
        return mav;
    }


    @RequestMapping(value = "/saveUser")
    public ModelAndView saveUser(@ModelAttribute User user, BindingResult br){

        ModelAndView mav = new ModelAndView("userForm");

        if(br.hasErrors()){

            //ERROR while entering user info
            return mav;
        }

        userService.saveUser(user);
        mav.setViewName("redirect:userForm");
        return mav;
    }

    @RequestMapping(value = "/updateUser")
    public ModelAndView updateUser(@RequestParam Long userId){
        ModelAndView mav = new ModelAndView("userForm");
        User retrievedUser = userService.findById(userId);

        if(retrievedUser == null){

            //ERROR user does not exist
            return mav;
        }

        mav.addObject("user",retrievedUser);
        mav.addObject("users", userService.findAll());
        return mav;
    }

    @RequestMapping(value = "/deleteUser")
    public ModelAndView deleteUser(@RequestParam Long userId){
        ModelAndView mav = new ModelAndView("userForm");
        User retrievedUser = userService.findById(userId);

        if(retrievedUser == null){
            //ERROR user does not exist
            return mav;
        }

        //userService.deleteById(userId);

        mav.addObject("users", userService.findAll());
        return mav;
    }
}
