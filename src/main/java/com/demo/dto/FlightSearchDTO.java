package com.demo.dto;

import com.demo.entities.Airline;
import com.demo.entities.Flight;
import com.demo.entities.FlightTravelDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightSearchDTO {
        FlightTravelDetailsDTO travelpref;
        LocalDate departOn;
        List<Flight> flight;
}
