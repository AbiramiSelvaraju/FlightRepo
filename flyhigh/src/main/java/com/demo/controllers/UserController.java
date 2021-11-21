package com.demo.controllers;

import com.demo.dto.FlightListDTO;
import com.demo.dto.FlightSearchDTO;
import com.demo.dto.UserDTO;
import com.demo.entities.Airline;
import com.demo.entities.Ticket;
import com.demo.entities.User;
import com.demo.exception.ModelNotFoundException;
import com.demo.repositories.TicketRepository;
import com.demo.services.TicketService;
import com.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private TicketService ticketService;

    @Autowired
    TicketRepository tRepo;

    @GetMapping("/user")
    public List<User> getAllUsers(){
        return service.getAllUsers();
    }

    @PostMapping("/user")
    public ResponseEntity<String> create(@RequestBody UserDTO userDTO) {
        service.createUser(userDTO);
        return new ResponseEntity<>("Created User!", HttpStatus.CREATED);
    }

//    USER TICKET SUMMARY

    @GetMapping("user/{userId}/ticketSummary")
    public List<Ticket> get(@PathVariable int userId ) {
        return tRepo.findByUserId(userId);
    }

    @PutMapping("/ticket/{ticketId}/cancel")
    public ResponseEntity<HttpStatus> cancelTicket(@PathVariable int ticketId ) throws ModelNotFoundException {
        ticketService.cancelTicket(ticketId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
