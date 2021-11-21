package com.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserTicketListDTO {
    int totalSeatsBooked;
    String username;
    String emailId;
    String pnrNumber;
    Boolean isCancelled;
    LocalDate journeyDate;
    int grandTicketCost;
    Timestamp createdOn;
}
