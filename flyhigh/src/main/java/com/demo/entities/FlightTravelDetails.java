package com.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightTravelDetails {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private LocalTime toTime;
    private LocalTime fromTime;
    private String estimateJourneyDuration;
    private int ticketCost;
    private Boolean isActive;
    private Timestamp createdOn;
    @ManyToOne
    private Flight flight;
    @OneToOne
    @JoinColumn(name = "trip_type_id", referencedColumnName = "id")
    private TripType tripType;
    @OneToOne
    @JoinColumn(name = "from_place_id", referencedColumnName = "id")
    private Place fromPlace;
    @OneToOne
    @JoinColumn(name = "to_place_id", referencedColumnName = "id")
    private Place toPlace;
    @OneToOne
    @JoinColumn(name = "schedule_id", referencedColumnName = "id")
    private Schedule schedule;

    @PrePersist
    public void prePersist() {
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        createdOn = currentTimestamp;
        isActive = true;
    }
}
