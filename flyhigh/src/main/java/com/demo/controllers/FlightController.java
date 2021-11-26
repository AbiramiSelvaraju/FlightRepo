package com.demo.controllers;

import com.demo.dto.FlightDTO;
import com.demo.dto.FlightListDTO;
import com.demo.dto.FlightSearchDTO;
import com.demo.dto.FlightTravelDetailsDTO;
import com.demo.entities.Flight;
import com.demo.entities.FlightTravelDetails;
import com.demo.entities.User;
import com.demo.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/v1/api")
public class FlightController {

    @Autowired
    private FlightService service;

//  FLIGHT CONTROLLERS

    @PostMapping("/flight")
    public ResponseEntity<HttpStatus> create(@RequestBody FlightDTO flightDTO) {
        service.create(flightDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/flight")
    public ResponseEntity<HttpStatus> update(@RequestBody FlightDTO flightDTO) {
        service.update(flightDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/flight/{id}")
    public Flight getFlightById(@PathVariable int id){
        return service.getFlightById(id);
    }

    @GetMapping("/flight")
    public List<Flight> getAllFlights(){
        return service.getAllFlights();
    }


//    FLIGHT TRAVEL CONTROLLERS
    @GetMapping("/flightTravelDetail")
    public List<FlightTravelDetails> getAllFlightTravelDetails(){
        return service.getAllFlightTravelDetails();
    }


    @GetMapping("/flightTravelDetail/{id}")
    public FlightTravelDetails getAllFlightTravelDetailsById(@PathVariable int id){
        return service.getFlightTravelDetailsById(id);
    }

    @PostMapping("/flightTravelDetail")
    public ResponseEntity<HttpStatus> createFTD(@RequestBody FlightTravelDetailsDTO flightTravelDTO) {
        service.createFlightTravelDetail(flightTravelDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

//    FLIGHT SEARCH CONTROLLERS
    @PostMapping("/flightSearch")
    public List<FlightListDTO> getFlights(@RequestBody FlightSearchDTO flightSearchDTO) {
        return service.getFlights(flightSearchDTO);
    }
}