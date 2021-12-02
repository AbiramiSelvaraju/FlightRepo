package com.demo.services;

import com.demo.dto.TicketDTO;
import com.demo.entities.Airline;
import com.demo.entities.Ticket;
import com.demo.exception.ModelNotFoundException;
import com.demo.mapper.TicketMapper;
import com.demo.repositories.FlightTravelRepository;
import com.demo.repositories.PassengerRepository;
import com.demo.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepo;

    @Autowired
    private TicketMapper mapper;

    @Autowired
    private FlightTravelRepository travelDetailsRepo;

    @Autowired
    PassengerRepository passengerRepo;

    public List<Ticket> getAllTickets() {
        return ticketRepo.findAll();
    }

    public Ticket getTicketById(int id) throws ModelNotFoundException {
        Ticket ticket =  ticketRepo.findById(id).orElseThrow(()-> new ModelNotFoundException("Ticket not found with id: "+id));
        return ticket;
    }

    public String createTicket(TicketDTO ticketDTO) {
        Ticket ticket = mapper.toTicket(ticketDTO);

        int initialJourneyFare = travelDetailsRepo.findInitialCostOfTheTicketByFlightDetailId(ticketDTO.getFlightTravelDetails().getId());

//        Determine grand ticket cost
        int grandTicketCost = initialJourneyFare * ticketDTO.getTotalSeatsBooked();
        ticket.setGrandTicketCost(grandTicketCost);

//        Generation of PNR number
        SecureRandom random = new SecureRandom();
        String randomUniqueNumber = String.format("%05d", random.nextInt(100000));

        ticket.setPnrNumber(randomUniqueNumber);

//        iterate and add passenger if passenger is not set  in mapper
        Ticket t = ticketRepo.save(ticket);
        if (ticket.getPassengers().size() != 0) {
            ticket.getPassengers().forEach(passengerEntry -> {
                passengerEntry.setTicketNumber(randomUniqueNumber);
                passengerEntry.setTicket(t);
                passengerRepo.save(passengerEntry);
            });
        }

        return randomUniqueNumber;
    }

    public Ticket cancelTicket(int ticketId) throws  ModelNotFoundException{
            Ticket ticket = ticketRepo.findById(ticketId).orElseThrow(()->new ModelNotFoundException("Entity Not Found to Cancel"));
            ticket.setIsCancelled(true);
            return ticketRepo.save(ticket);
    }

    public List<Ticket> getAllTicketsByUserId() {
        return ticketRepo.findByUserId(1);
    }
}