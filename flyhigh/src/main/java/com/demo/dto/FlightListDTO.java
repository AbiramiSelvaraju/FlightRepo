package com.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalTime;

@Data
public class FlightListDTO {

//    String airlineName;
//    String flightNumber;
//    int flightId;
//    LocalTime fromTime;
//    LocalTime toTime;
//    String journeyTime;
//    int ticketCost;

    String name;
    String number;
    int id;
    LocalTime fromTime;
    LocalTime toTime;
    String estimateJourneyDuration;
    int ticketCost;
    int flightScheduleId;

    public FlightListDTO(String name, String number, int id, Time fromTime, Time toTime, String estimateJourneyDuration, int ticketCost, int flightScheduleId) {
        this.name = name;
        this.number = number;
        this.id = id;
        this.fromTime = fromTime.toLocalTime();
        this.toTime = toTime.toLocalTime();
        this.estimateJourneyDuration = estimateJourneyDuration;
        this.ticketCost = ticketCost;
        this.flightScheduleId = flightScheduleId;

    }
}
