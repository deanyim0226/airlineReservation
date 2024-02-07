package com.example.airlinesreservation.service;


import com.example.airlinesreservation.dao.RoleRepository;
import com.example.airlinesreservation.domain.Role;
import com.example.airlinesreservation.domain.User;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.server.ExportException;
import java.util.List;

@Service
public class RoleServiceImplementation implements RoleService {

    @Autowired
    SessionFactory sessionFactory;
    @Override
    public List<Role> findAll() {
        List<Role> roles = null;

        try(Session session = sessionFactory.openSession();){

            session.beginTransaction();
            roles = session.createQuery("from Role").list();
            session.getTransaction().commit();

        }catch (Exception e){
            e.printStackTrace();
        }

        return roles;
    }

    /*
    A Session is used to get a physical connection with a database.
    The Session object is lightweight and designed to be instantiated each time an interaction is needed with the database.
    Persistent objects are saved and retrieved through a Session object.
     */
    @Override
    public Role findById(Long roleId) {
        Role role = null;

        try(Session session = sessionFactory.openSession();){

            session.beginTransaction();
            role = session.get(Role.class, roleId);
            session.getTransaction().commit();

        }catch (Exception e){
            e.printStackTrace();
        }
        return role;
    }

    @Override
    public Role saveRole(Role role) {


        if(role == null){
            return null;
        }else{

            try(Session session = sessionFactory.openSession();){
                session.beginTransaction();
                Long roleId = (Long)session.save(role);
                Role savedRole = session.get(Role.class,roleId);
                session.getTransaction().commit();

                return savedRole;
            }catch (Exception e){
                e.printStackTrace();
            }

            return null;
        }

    }

    @Override
    public Role deleteById(Long roleId) {


        Role retrievedRole = null;
        String hql = "From Role where roldId = :param";

        try(Session session = sessionFactory.openSession();){
            session.beginTransaction();

            retrievedRole = session.get(Role.class, roleId);

            if(retrievedRole != null){
                session.delete(retrievedRole);
                System.out.println(retrievedRole.getRoleName() + " is deleted");
            }
            session.getTransaction().commit();

            return retrievedRole;
        }catch (Exception e){
            e.printStackTrace();
        }

        return retrievedRole;
    }

    @Override
    public Role updateRole(Role role) {

        if(role == null){
            return null;
        }else{

            try(Session session = sessionFactory.openSession();){

                session.beginTransaction();
                Role retrievedRole = session.get(Role.class, role.getRoleId());
                retrievedRole.setRoleName(role.getRoleName());
                retrievedRole.setDescription(role.getDescription());
                retrievedRole.setUsers(role.getUsers());

                session.update(retrievedRole);
                session.getTransaction().commit();

                return retrievedRole;
            }catch (Exception e){
                e.printStackTrace();
            }

            return null;
        }
    }

    //save  return Long
    //get return Object
    //createQuery return query
    @Override
    public List<Role> findByRoleName(String roleName) {

        String hql = "From Role where roleName = :param" ;

        try(Session session = sessionFactory.openSession();){

            session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("param", roleName);
            List<Role> retrievedRoles = query.getResultList();
            session.getTransaction().commit();

            return retrievedRoles;

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    /*
    @Autowired
    RoleRepository roleRepository;


    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findById(Long roleId) {
        return roleRepository.findById(roleId).orElse(null);
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void deleteById(Long roleId) {

        roleRepository.deleteById(roleId);
    }

    @Override
    public Role findByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }

     */
}
