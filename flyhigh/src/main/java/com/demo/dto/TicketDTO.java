package com.demo.dto;

import com.demo.entities.FlightTravelDetails;
import com.demo.entities.MealType;
import com.demo.entities.Passenger;
import com.demo.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketDTO {

     int totalSeatsBooked;
     String username;
     String emailId;
     String pnrNumber;
     Boolean isCancelled;
     LocalDate journeyDate;
     int grandTicketCost;
     Timestamp createdOn;
     User user;
     MealType mealType;
     FlightTravelDetails flightTravelDetails;
     List<Passenger> passengers;

}
