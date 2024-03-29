package com.example.airlinesreservation.service;

import com.example.airlinesreservation.domain.Role;

import java.util.List;

public interface RoleService {

    public List<Role> findAll();

    public Role findById(Long roleId);

    public Role saveRole(Role role);

    public Role deleteById(Long roleId);

    public Role updateRole(Role roleId);

    public List<Role> findByRoleName(String roleName);
}
