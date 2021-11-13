package com.demo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.engine.internal.Cascade;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Data
public class Flight {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String number;
    private String instrumentUsed;
    private int totalBusinessSeats;
    private int totalNonBusinessSeats;
    private int numberOfRows;
    private Boolean isActive;
    private Timestamp createdOn;

    @ManyToOne
    private Airline airline;

    @OneToMany(mappedBy = "flight")
    @JsonIgnore
    private List<FlightTravelDetails> travelDetails;

    @PrePersist
    public void prePersist() {
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        createdOn = currentTimestamp;
        isActive = true;
    }

}
