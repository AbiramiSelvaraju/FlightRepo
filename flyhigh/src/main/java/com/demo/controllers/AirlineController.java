package com.demo.controllers;

import com.demo.dto.AirlineDTO;
import com.demo.entities.Airline;
import com.demo.services.AirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/v1/api")
public class AirlineController {

    @Autowired
    private AirlineService service;

//    @Autowired
    private KafkaTemplate kafkaTemplate;

    public AirlineController(KafkaTemplate kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    private static final String TOPIC = "kafka_topic";

    @GetMapping("/airline/{id}")
    public Airline getAllAirlines(@PathVariable int id){
        return service.getAirlineById(id);
    }

    @GetMapping("/airline")
    public List<Airline> getAllAirlines(){
        return service.getAllAirlines();
    }

    @PostMapping("/airline")
    public ResponseEntity<HttpStatus> create(@RequestBody AirlineDTO airlineDTO) throws Exception {
        service.saveAirline(airlineDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/airline")
    public ResponseEntity<HttpStatus> update(@RequestBody AirlineDTO airlineDTO){
        service.updateAirline(airlineDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PutMapping("/airline/block")
    public ResponseEntity<HttpStatus> blockAirline(@RequestBody int airlineId ) {
        AirlineDTO airlineDTO = service.blockAirline(airlineId);
        com.demo.dto.Airline a = new com.demo.dto.Airline(airlineDTO.getName(), airlineDTO.getContactNumber(), airlineDTO.getContactAddress(), airlineDTO.getIsActive());

//        kafkaTemplate.send(TOPIC, a);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
        @PutMapping("/airline/unblock")
        public ResponseEntity<HttpStatus> unBlockAirline(@RequestBody int airlineId ) {
            service.unBlockAirline(airlineId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
