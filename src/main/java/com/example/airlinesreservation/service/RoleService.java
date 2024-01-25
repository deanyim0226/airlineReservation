package com.example.airlinesreservation.service;

import com.example.airlinesreservation.domain.Role;

import java.util.List;

public interface RoleService {

    public List<Role> findAll();

    public Role findById(Long roleId);

    public Role saveRole(Role role);

    public void deleteById(Long roleId);

    public Role findByRoleName(String roleName);
}
