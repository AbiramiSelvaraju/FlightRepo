package com.demo.repositories;

import com.demo.entities.Airline;
import com.demo.entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Integer> {


}