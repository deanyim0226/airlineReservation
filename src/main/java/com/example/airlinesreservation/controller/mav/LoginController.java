package com.example.airlinesreservation.controller.mav;

import com.example.airlinesreservation.domain.Role;
import com.example.airlinesreservation.domain.User;
import com.example.airlinesreservation.service.RoleService;
import com.example.airlinesreservation.service.UserService;
import com.example.airlinesreservation.validation.RegisterValidator;
import com.example.airlinesreservation.validation.UserValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    private final static String CUSTOMER_ROLE = "Customer";

    @Autowired
    RegisterValidator registerValidator;

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.addValidators(registerValidator);
    }

    @RequestMapping({"/home","/"})
    public ModelAndView home(){

        ModelAndView mav = new ModelAndView("home");


        return mav;
    }

    @RequestMapping({"/login"})
    public String loginToApp(@RequestParam(value = "logout", required = false) String logout,
                             @RequestParam(value = "error", required = false) String error,
                             HttpServletRequest req, HttpServletResponse res, Model model
    ){

        String message = null;

        if(logout != null){
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();

            if(auth != null){
                new SecurityContextLogoutHandler().logout(req,res,auth);
                message ="you are logged out";
            }
        }
        System.out.println("---login---");

        if(error != null){
            message =  "either username or password is incorrect";
        }

        model.addAttribute("message",message);

        return "login";
    }

    @RequestMapping("/registerUser")
    public ModelAndView registerUser(@Valid  @ModelAttribute User user, BindingResult br){
        ModelAndView mav = new ModelAndView("registerForm");
        StringBuilder sb = new StringBuilder("");
        if(br.hasErrors()){

            List<FieldError> fieldErrors = br.getFieldErrors();
            for(FieldError fieldError: fieldErrors){
                sb = sb.append("\""+fieldError.getField() +"\":"+fieldError.getDefaultMessage()+"\n");
            }
            System.out.println("sb: " + sb );

            System.out.println("error while saving users");
            mav.addObject("users",userService.findAll());
            mav.addObject("hasError",true);
            return mav;
        }

        List<User> users = userService.findAll();
        Long currentId = Long.valueOf(users.size());
        List<Role> customerRole = roleService.findByRoleName(CUSTOMER_ROLE);

        user.setRoles(customerRole);
        user.setUserId(++currentId);
        System.out.println("user is " + user.getUsername());
        userService.registerUser(user);
        mav.setViewName("redirect:login");
        return mav;
    }


    @RequestMapping("/registerForm")
    public ModelAndView registerForm(User user){
        ModelAndView mav = new ModelAndView("registerForm");

        return mav;
    }
}
