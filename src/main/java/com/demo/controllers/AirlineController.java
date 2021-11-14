package com.demo.controllers;

import com.demo.dto.AirlineDTO;
import com.demo.entities.Airline;
import com.demo.services.AirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/v1/api")
public class AirlineController {

    @Autowired
    private AirlineService service;

    @GetMapping("/airline")
    public List<Airline> getAllAirlines(){
        return service.getAllAirlines();
    }

    @PostMapping("/airline")
    public ResponseEntity<String> create(@RequestBody AirlineDTO airlineDTO) throws Exception {
        service.saveAirline(airlineDTO);
        return new ResponseEntity<>("Created Airline", HttpStatus.CREATED);
    }

    @PutMapping("/airline/{airlineId}/block")
    public ResponseEntity<HttpStatus> blockAirline(@PathVariable int airlineId ) {
        service.blockAirline(airlineId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
