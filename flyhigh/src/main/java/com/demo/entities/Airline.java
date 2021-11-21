package com.demo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;


import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Airline {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	private String contactNumber;
	private String contactAddress;
	private Boolean isActive;
	private Timestamp createdOn;
    @JsonIgnore
	@OneToMany(mappedBy = "airline")
	private List<Flight> flights;

    @PrePersist
	public void prePersist() {
		Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
		createdOn = currentTimestamp;
		isActive = true;
	}

}
