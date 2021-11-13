package com.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Passenger {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String name;
    private int age;
    private Boolean isActive;
    private Timestamp createdOn;
    private String ticketNumber;
    @OneToOne
    @JoinColumn(name = "gender_id", referencedColumnName = "id")
    private Gender gender;
    @OneToOne
    @JoinColumn(name = "seat_id", referencedColumnName = "id")
    private SeatNumber seatNumber;
    @ManyToOne
    @JsonIgnore
    private Ticket ticket;

    @PrePersist
    public void prePersist() {
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        createdOn = currentTimestamp;
        isActive = true;
    }

}
