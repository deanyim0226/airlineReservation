package com.example.airlinesreservation.controller.mav;

import com.example.airlinesreservation.domain.User;
import com.example.airlinesreservation.service.RoleService;
import com.example.airlinesreservation.service.UserService;
import com.example.airlinesreservation.validation.UserValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    UserValidator userValidator;

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.addValidators(userValidator);
    }
    @RequestMapping(value = "/userForm")
    public ModelAndView userForm(User user){

        ModelAndView mav = new ModelAndView("userForm");

        mav.addObject("roles", roleService.findAll());
        mav.addObject("users", userService.findAll());
        mav.addObject("sortedBy", List.of("UserId","Username","Email","MobileNo"));
        return mav;
    }

    @RequestMapping(value = "pagedUser")
    public ModelAndView pagedUser(@RequestParam int pageNo, @RequestParam int pageSize, @RequestParam(required = false) String sortedBy){
        ModelAndView mav = new ModelAndView("pagedUser");

        Pageable pageable = null;
        pageable = PageRequest.of(pageNo,pageSize, Sort.by(sortedBy));
        Page<User> pagedUser = userService.findUsers(pageable);
        List<User> userList = pagedUser.getContent();

        mav.addObject("users",userList);
        mav.addObject("sortedBy",sortedBy);
        mav.addObject("totalPages",pagedUser.getTotalPages());
        mav.addObject("pageSize" , pageSize);

        return mav;
    }

    @RequestMapping(value = "/saveUser")
    public ModelAndView saveUser(@Valid @ModelAttribute User user, BindingResult br){

        ModelAndView mav = new ModelAndView("userForm");
        StringBuilder sb = new StringBuilder("");

        if(br.hasErrors()){

            List<FieldError> fieldErrors = br.getFieldErrors();
            for(FieldError fieldError: fieldErrors){
                sb = sb.append("\""+fieldError.getField() +"\":"+fieldError.getDefaultMessage()+"\n");
            }
            System.out.println("sb: " + sb );

            mav.addObject("users",userService.findAll());
            mav.addObject("hasError",true);
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

        mav.addObject("roles",roleService.findAll());
        mav.addObject("retrievedRole",retrievedUser.getRoles());
        mav.addObject("user",retrievedUser);
        mav.addObject("users", userService.findAll());
        return mav;
    }

    @RequestMapping(value = "/deleteUser")
    public ModelAndView deleteUser(User user){
        ModelAndView mav = new ModelAndView("userForm");
        Long userid = user.getUserId();
        User retrievedUser = userService.findById(userid);

        if(retrievedUser == null){
            //ERROR user does not exist
            return mav;
        }

        userService.deleteById(userid);

        mav.addObject("users", userService.findAll());
        return mav;
    }
}
