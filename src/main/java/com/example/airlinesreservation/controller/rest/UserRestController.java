package com.example.airlinesreservation.controller.rest;

import com.example.airlinesreservation.domain.User;
import com.example.airlinesreservation.service.UserService;
import com.example.airlinesreservation.validation.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {

    @Autowired
    UserService userService;

    @Autowired
    UserValidator userValidator;

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.addValidators(userValidator);
    }

    @GetMapping("/getUser")
    public ResponseEntity<User> getUser(){
        return null;
    }
}
