package com.demo.config;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

       if ("user".equals(username)) {
           List roles = new ArrayList<String>();
           roles.add("USER");
            return new User("user", "$2a$10$OS9G3A/3HiPC5akBx7g8fuRtGPvl8g/JSHXKGmX6pjelqeW3cLpQa", new ArrayList<>());
        } else if("admin".equals(username)) {
           List roles = new ArrayList<String>();
           roles.add("ADMIN");
           return new User("admin", "$2a$10$OS9G3A/3HiPC5akBx7g8fuRtGPvl8g/JSHXKGmX6pjelqeW3cLpQa",  new ArrayList<>());
       }
       else{
               throw new UsernameNotFoundException("User not found with username: " + username);
           }
       }
    }