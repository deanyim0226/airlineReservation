package com.example.airlinesreservation.service;

import com.example.airlinesreservation.domain.Role;
import com.example.airlinesreservation.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserDetailServiceImplementation implements UserDetailsService {


    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("username: " + username);
        User user = userService.findByUsername(username);

        if(user == null){
            //error
            throw new UsernameNotFoundException(username);
        }

        List<Role> roles = user.getRoles();
        Collection<GrantedAuthority> grantedAuthority = new HashSet<>();

        for(Role role : roles){
            grantedAuthority.add(new SimpleGrantedAuthority(role.getRoleName()));
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthority);
    }
}
