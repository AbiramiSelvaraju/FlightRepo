package com.demo.dto;

import com.demo.entities.Flight;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AirlineDTO {
      int id;
      String name;
      String contactNumber;
      String contactAddress;
      Boolean isActive;
      Timestamp createdOn;
      List<Flight> flights;
}
