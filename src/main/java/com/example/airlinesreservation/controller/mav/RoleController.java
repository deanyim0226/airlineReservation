package com.example.airlinesreservation.controller.mav;


import com.example.airlinesreservation.domain.Role;
import com.example.airlinesreservation.service.RoleService;
import org.eclipse.tags.shaded.org.apache.xpath.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RoleController {


    @Autowired
    RoleService roleService;

    @RequestMapping(value = "/roleForm")
    public ModelAndView roleForm(Role role){

        ModelAndView mav = new ModelAndView("roleForm");

        mav.addObject("roles", roleService.findAll());
        return mav;
    }

    @RequestMapping(value = "/saveRole")
    public ModelAndView saveRole(@ModelAttribute Role role, BindingResult br){

        ModelAndView mav = new ModelAndView("roleForm");

        if(br.hasErrors()){
            //error while entering data

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

        //roleService.deleteRole(roleId);
        mav.addObject("roles", roleService.findAll());
        return mav;
    }
}
