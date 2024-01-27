package com.example.airlinesreservation.controller.mav;


import com.example.airlinesreservation.domain.Role;
import com.example.airlinesreservation.service.RoleService;
import com.example.airlinesreservation.validation.RoleValidator;
import jakarta.validation.Valid;
import org.eclipse.tags.shaded.org.apache.xpath.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
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
public class RoleController {


    @Autowired
    RoleValidator roleValidator;
    @Autowired
    RoleService roleService;

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.addValidators(roleValidator);
    }

    @RequestMapping(value = "/roleForm")
    public ModelAndView roleForm(Role role){

        ModelAndView mav = new ModelAndView("roleForm");

        mav.addObject("roles", roleService.findAll());
        return mav;
    }

    @RequestMapping(value = "/saveRole")
    public ModelAndView saveRole(@Valid  @ModelAttribute Role role, BindingResult br){

        ModelAndView mav = new ModelAndView("roleForm");
        StringBuilder sb = new StringBuilder("");

        if(br.hasErrors()){

            List<FieldError> fieldErrors = br.getFieldErrors();
            for(FieldError fieldError: fieldErrors){
                sb = sb.append("\""+fieldError.getField() +"\":"+fieldError.getDefaultMessage()+"\n");
            }
            System.out.println("sb: " + sb );
            mav.addObject("roles", roleService.findAll());
            mav.addObject("hasError",true);
            return mav;
        }

        roleService.saveRole(role);
        mav.addObject("users",roleService.findAll());
        mav.setViewName("redirect:roleForm");
        return mav;
    }

    @RequestMapping(value = "/updateRole")
    public ModelAndView updateRole(@RequestParam Long roleId){
        ModelAndView mav = new ModelAndView("roleForm");

        Role retrievedRole = roleService.findById(roleId);

        if(retrievedRole == null){
            //ERROR ROLE does not exist

            return mav;
        }

        mav.addObject("role",retrievedRole);
        mav.addObject("roles", roleService.findAll());
        return mav;
    }

    @RequestMapping(value = "/deleteRole")
    public ModelAndView deleteRole(@RequestParam Long roleId){

        ModelAndView mav = new ModelAndView("roleForm");
        Role retrievedRole = roleService.findById(roleId);

        if(retrievedRole == null){
            //ERROR ROLE does not exist

            return mav;
        }

        //roleService.deleteById(roleId);
        mav.addObject("roles", roleService.findAll());
        return mav;
    }
}
