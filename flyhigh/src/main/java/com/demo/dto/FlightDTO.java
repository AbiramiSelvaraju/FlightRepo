package com.demo.dto;

import com.demo.entities.Airline;
import com.demo.entities.FlightTravelDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightDTO {

      int id;
      String number;
      String instrumentUsed;
      int totalBusinessSeats;
      int totalNonBusinessSeats;
      int numberOfRows;
      Boolean isBlocked;
      Timestamp createdOn;
      Airline airline;
      List<FlightTravelDetails> travelDetails;
    
}
