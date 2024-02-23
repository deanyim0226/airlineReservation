package com.example.airlinesreservation.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    UserDetailsService userDetailsService;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http.httpBasic().and().csrf().disable().authorizeRequests()
                .requestMatchers(AntPathRequestMatcher.antMatcher("/WEB-INF/jsp/**")).permitAll()
                .and()
                .authorizeRequests().requestMatchers("/userForm", "/roleForm", "/airportForm", "/airlineForm", "/flightForm" , "/passengerForm").hasAnyAuthority("Admin")
                .and()
                .authorizeRequests().requestMatchers("/").authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login");

        /*
        admin has
         */
        http.userDetailsService(userDetailsService);

        return http.build();
    }
}
