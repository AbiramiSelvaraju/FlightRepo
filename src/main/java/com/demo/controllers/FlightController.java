package com.demo.controllers;

import com.demo.dto.FlightDTO;
import com.demo.dto.FlightListDTO;
import com.demo.dto.FlightSearchDTO;
import com.demo.dto.FlightTravelDetailsDTO;
import com.demo.entities.*;
import com.demo.services.AirlineService;
import com.demo.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/api")
public class FlightController {

    @Autowired
    private FlightService service;
//  Flight details
    @GetMapping("/flight")
    public List<Flight> getAllFlights(){
        return service.getAllFlights();
    }

//    @PostMapping("/flight")
//    public Flight createAirline(@RequestBody Flight flight) {
//        return service.createFlight(flight);
//    }

//    Flight travel details

    @GetMapping("/flightTravelDetail")
    public List<FlightTravelDetails> getAllFlightTravelDetails(){
        return service.getAllFlightTravelDetails();
    }

//    @PostMapping("/flightTravelDetail")
//    public FlightTravelDetails createAirline(@RequestBody FlightTravelDetails flight) {
//        return service.createFlightTravelDetail(flight);
//    }

    @PostMapping("/flight")
    public ResponseEntity<String> create(@RequestBody FlightDTO flightDTO) {
        service.create(flightDTO);
        return new ResponseEntity<>("Created Flight!", HttpStatus.OK);
    }

    @PostMapping("/flightTravelDetail")
    public ResponseEntity<String> createFTD(@RequestBody FlightTravelDetailsDTO flightTravelDTO) {
        service.createFlightTravelDetail(flightTravelDTO);
        return new ResponseEntity<>("Created FlightTravelDetails!", HttpStatus.OK);
    }

//    Get flights
    @PostMapping("/flightsearch")
    public List<FlightListDTO> getFlights(@RequestBody FlightSearchDTO flightSearchDTO) {
        return service.getFlights(flightSearchDTO);
    }
}