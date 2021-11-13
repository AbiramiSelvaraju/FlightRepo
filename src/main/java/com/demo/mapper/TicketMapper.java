package com.demo.mapper;

import com.demo.dto.TicketDTO;
import com.demo.entities.Ticket;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TicketMapper {
    Ticket toTicket(TicketDTO ticketDTO);
}
