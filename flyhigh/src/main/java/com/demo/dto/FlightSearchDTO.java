package com.demo.dto;

import com.demo.entities.*;
import com.demo.entities.Airline;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightSearchDTO {
        TripType tripType;
        Place fromPlace;
        Place toPlace;
        LocalDateTime departOn;
        List<Flight> flight;
}
