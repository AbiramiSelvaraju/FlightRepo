package com.demo.controllers;

import java.util.List;

import com.demo.dto.UserDTO;
import com.demo.entities.Airline;
import com.demo.entities.User;
import com.demo.services.AirlineService;
import com.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.entities.Movie;
import com.demo.services.MovieService;

@RestController
@RequestMapping("/v1/api")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/user")
    public List<User> getAllUsers(){
        return service.getAllUsers();
    }

    @PostMapping("/user")
    public ResponseEntity<String> create(@RequestBody UserDTO userDTO) {
        service.createUser(userDTO);
        return new ResponseEntity<>("Created User!", HttpStatus.OK);
    }



}
