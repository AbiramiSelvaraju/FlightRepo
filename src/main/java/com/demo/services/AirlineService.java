package com.demo.services;

import com.demo.dto.AirlineDTO;
import com.demo.entities.Airline;
import com.demo.mapper.AirlineMapper;
import com.demo.repositories.AirlineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirlineService {

    @Autowired
    private AirlineRepository repo;

    @Autowired
    private AirlineMapper mapper;

    public List<Airline> getAllAirlines(){
        return repo.findAll();
    }

    public Airline createAirline(Airline m) {
        return repo.save(m);
    }

    public void saveAirline(AirlineDTO airlineDTO) {
        Airline airline = mapper.toAirline(airlineDTO);
        repo.save(airline);
    }
}
