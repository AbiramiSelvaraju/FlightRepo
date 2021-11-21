package com.demo.repositories;

import com.demo.entities.Ticket;
import com.demo.entities.User;
import com.demo.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    public List<Ticket> findByUserId(int id);

    public List<Ticket> getById(int id);
}
