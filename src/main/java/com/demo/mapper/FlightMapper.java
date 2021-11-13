package com.demo.mapper;

import com.demo.dto.FlightDTO;
import com.demo.dto.FlightTravelDetailsDTO;
import com.demo.entities.Flight;
import com.demo.entities.FlightTravelDetails;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FlightMapper {
    Flight toFlight(FlightDTO flightDTO);

    FlightTravelDetails toFlightTravelDetail(FlightTravelDetailsDTO  flightTravelDTO);
}
