package com.example.airlinesreservation.controller.rest;

import com.example.airlinesreservation.domain.User;
import com.example.airlinesreservation.service.UserService;
import com.example.airlinesreservation.validation.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("r")
public class UserRestController {

    @Autowired
    UserService userService;

    @Autowired
    UserValidator userValidator;

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.addValidators(userValidator);
    }

    /*
        userService.saveUser();
     */

    @PostMapping("/saveUser")
    public ResponseEntity<User> saveUser(@RequestBody User newUser){

        User savedUser = userService.saveUser(newUser);

        if(savedUser == null){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(savedUser);
        }

        return ResponseEntity.status(HttpStatus.OK).body(savedUser);
    }
    @GetMapping("/getUserById")
    public ResponseEntity<User> getUserById(@RequestParam Long userId){

        User retrievedUser = userService.findById(userId);

        if(retrievedUser == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(retrievedUser);
        }

        return ResponseEntity.status(HttpStatus.OK).body(retrievedUser);
    }
    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getAll(){
        List<User> userList = userService.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(userList);
    }
    @PutMapping("/updateUser")
    public ResponseEntity<User> updateUser(@RequestBody User user){

        User updatedUser = userService.updateUser(user);

        if(updatedUser == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(updatedUser);
        }

        return ResponseEntity.status(HttpStatus.OK).body(updatedUser);

    }


    @DeleteMapping("/deleteUserById")
    public ResponseEntity<User> deleteUser(@RequestParam Long userId){
        User deletedUser = userService.deleteById(userId);

        if(deletedUser == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(deletedUser);
        }
        return ResponseEntity.status(HttpStatus.OK).body(deletedUser);
    }

    @GetMapping("/getUserByUsername")
    public ResponseEntity<?> getUser(@RequestParam String username){
        List<User> retrievedUser = userService.findByUsername(username);
        System.out.println("user name given by request param " + username);

        if(retrievedUser.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(retrievedUser);
        }

        return ResponseEntity.status(HttpStatus.OK).body(retrievedUser);
    }


}
