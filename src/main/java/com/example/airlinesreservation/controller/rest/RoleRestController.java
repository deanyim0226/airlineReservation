package com.example.airlinesreservation.controller.rest;


import com.example.airlinesreservation.domain.Role;
import com.example.airlinesreservation.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("r")
@RestController
public class RoleRestController {

    @Autowired
    RoleService roleService;

    @GetMapping("getRoles")
    public ResponseEntity<List<Role>> getRoles(){

        List<Role> retrievedRoles = roleService.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(retrievedRoles);
    }

    @GetMapping("getRoleById")
    public ResponseEntity<Role> getRole(@RequestParam Long roleId){

        Role retrievedRole = roleService.findById(roleId);
        if(retrievedRole == null){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(retrievedRole);
        }
        return ResponseEntity.status(HttpStatus.OK).body(retrievedRole);
    }

    @PostMapping("saveRole")
    public ResponseEntity<Role> saveRole(@RequestBody Role role){
        Role savedRole = roleService.saveRole(role);

        if(savedRole == null){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(savedRole);
        }

        return ResponseEntity.status(HttpStatus.OK).body(savedRole);
    }

    @GetMapping("getRoleByName")
    public ResponseEntity<List<Role>> getRoleByName(@RequestParam String roleName){
        List<Role> retrievedRoles = roleService.findByRoleName(roleName);

        return ResponseEntity.status(HttpStatus.OK).body(retrievedRoles);
    }

    @DeleteMapping("deleteRoleById")
    public ResponseEntity<Role> deleteRole(@RequestParam Long roleId){
        Role deletedRole = roleService.deleteById(roleId);

        if(deletedRole == null){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(deletedRole);
        }

        return ResponseEntity.status(HttpStatus.OK).body(deletedRole);
    }

    @PutMapping("updateRole")
    public ResponseEntity<Role> updateRole(@RequestBody Role role){
        Role updatedRole = roleService.updateRole(role);

        if(updatedRole == null){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(updatedRole);
        }

        return ResponseEntity.status(HttpStatus.OK).body(updatedRole);
    }

}
