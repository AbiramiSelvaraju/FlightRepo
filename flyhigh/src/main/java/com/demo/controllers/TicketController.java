package com.demo.controllers;

import com.demo.dto.TicketDTO;
import com.demo.entities.Ticket;
import com.demo.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api")
public class TicketController {

    @Autowired
    private TicketService service;

    @GetMapping("/ticket")
    public List<Ticket> getAllTickets(){
        return service.getAllTickets();
    }

    @PostMapping("/ticket")
    public ResponseEntity<HttpStatus> create(@RequestBody TicketDTO ticketDTO) {
        String PNRnumber = service.createTicket(ticketDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
