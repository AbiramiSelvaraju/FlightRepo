package com.demo.dto;

import com.demo.entities.Role;
import com.demo.entities.Ticket;
import com.demo.entities.Ticket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
      String name;
      Boolean isActive;
      Timestamp createdOn;
      Role role;
      List<Ticket> ticketDetails;
}
