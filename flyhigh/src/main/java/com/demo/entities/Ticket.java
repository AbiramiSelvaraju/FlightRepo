package com.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private int totalSeatsBooked;
    private int grandTicketCost;
    private String username;
    private String emailId;
    private String pnrNumber;
    private Boolean isCancelled;
    private LocalDate journeyDate;
    private Timestamp createdOn;

    public Ticket(String username, String emailId, int totalSeatsBooked){
        this.username = username;
        this.emailId = emailId;
        this.totalSeatsBooked = totalSeatsBooked;
    }

    @JsonIgnore
    @ManyToOne
    User user;

    @OneToOne
    private MealType mealType;

    @OneToOne
    private FlightTravelDetails flightTravelDetails;

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL)
    private List<Passenger> passengers;

    @PrePersist
    public void prePersist() {
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        createdOn = currentTimestamp;
        isCancelled = false;
        user = new User();
        user.setId(1);
    }

}
