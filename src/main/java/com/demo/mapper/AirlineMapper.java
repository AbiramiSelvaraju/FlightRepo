package com.demo.mapper;

import com.demo.dto.AirlineDTO;
import com.demo.entities.Airline;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AirlineMapper {

    Airline toAirline(AirlineDTO airlineDTO);
}
