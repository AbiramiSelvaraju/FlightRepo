package com.demo.services;

import com.demo.dto.AirlineDTO;
import com.demo.entities.Airline;
import com.demo.mapper.AirlineMapper;
import com.demo.repositories.AirlineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class AirlineService {

    @Autowired
    private AirlineRepository repo;

    @Autowired
    private AirlineMapper mapper;

    public List<Airline> getAllAirlines(){
        return repo.findAll();
    }

    public void saveAirline(AirlineDTO airlineDTO) {
        Airline airline = mapper.toAirline(airlineDTO);
        repo.save(airline);
    }
    public void updateAirline(AirlineDTO airlineDTO) {
        Airline airline = repo.findById(airlineDTO.getId()).orElseThrow(()->new EntityNotFoundException("Entity Not Found to Edit"));
        airline.setName(airlineDTO.getName());
        airline.setContactAddress(airlineDTO.getContactAddress());
        airline.setContactNumber(airlineDTO.getContactNumber());
        repo.save(airline);
    }

    public AirlineDTO blockAirline(int airlineId) {
        Airline airline = repo.findById(airlineId).orElseThrow(()->new EntityNotFoundException("Entity Not Found to Block"));
        airline.setIsActive(false);
        airline = repo.save(airline);
        AirlineDTO airlineDTO = mapper.toAirlineDTO(airline);
        return airlineDTO;
    }

    public void unBlockAirline(int airlineId) {
        Airline airline = repo.findById(airlineId).orElseThrow(()->new EntityNotFoundException("Entity Not Found to unBlock"));
        airline.setIsActive(true);
        airline = repo.save(airline);
        AirlineDTO airlineDTO = mapper.toAirlineDTO(airline);
//        return airlineDTO;
    }

    public Airline getAirlineById(int id) {
        return repo.findById(id).orElseThrow(()-> new EntityNotFoundException());
    }
}
