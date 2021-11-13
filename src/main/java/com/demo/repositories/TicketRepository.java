package com.demo.repositories;

import com.demo.entities.Ticket;
import com.demo.entities.User;
import com.demo.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
}
