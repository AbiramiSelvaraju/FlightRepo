package com.demo.dto;

import com.demo.entities.Flight;
import com.demo.entities.Place;
import com.demo.entities.Schedule;
import com.demo.entities.TripType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightTravelDetailsDTO {
       LocalTime toTime;
       LocalTime fromTime;
       String estimateJourneyDuration;
       int ticketCost;
       Boolean isActive;
       Timestamp createdOn;
       Timestamp updatedOn;

       Flight flight;
       TripType tripType;
       Place fromPlace;
       Place toPlace;
       Schedule schedule;
}
