package com.demo.repositories;

import com.demo.entities.Flight;
import com.demo.entities.FlightTravelDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FlightTravelRepository  extends JpaRepository<FlightTravelDetails, Integer> {

    @Query(value = "select ticketCost from FlightTravelDetails where id=:flightTravelDetailId")
    int findInitialCostOfTheTicketByFlightDetailId(@Param("flightTravelDetailId") int flightTravelDetailId);
}