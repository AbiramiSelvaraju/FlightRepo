package com.demo.services;

import com.demo.dto.*;
import com.demo.entities.Airline;
import com.demo.entities.Flight;
import com.demo.entities.FlightTravelDetails;
import com.demo.entities.User;
import com.demo.mapper.FlightMapper;
import com.demo.repositories.FlightRepository;
import com.demo.repositories.FlightTravelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.persistence.Tuple;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepo;

    @Autowired
    private FlightTravelRepository flightTravelRepo;

    @Autowired
    private FlightMapper mapper;

    @Autowired
    EmailService emailService;

    public List<Flight> getAllFlights(){
        return flightRepo.findAll();
    }

    public Flight getFlightById(int id){
        return flightRepo.findById(id).orElseThrow(()-> new EntityNotFoundException());
    }

    public FlightTravelDetails getFlightTravelDetailsById(int id){
        return flightTravelRepo.findById(id).orElseThrow(()-> new EntityNotFoundException());
    }


// Flight travel details
    public List<FlightTravelDetails> getAllFlightTravelDetails() {
        return flightTravelRepo.findAll();
    }

    public void createFlightTravelDetail(FlightTravelDetailsDTO flightTravelDTO)  {

        FlightTravelDetails flightTravelDetails = mapper.toFlightTravelDetail(flightTravelDTO);

//        Based on journey time, calculated estimateJourneyDuration
        Long differenceHours = ChronoUnit.HOURS.between(flightTravelDetails.getFromTime(), flightTravelDetails.getToTime());
        Long differenceMins = ChronoUnit.MINUTES.between(flightTravelDetails.getFromTime(), flightTravelDetails.getToTime())%60;

//        Based on journey time, ticket cost varies
        int ticketCost = differenceHours <= 3 ? ticketCost = 7000 :
                                differenceHours <= 5 ? ticketCost = 5000 :
                                         differenceHours <= 10 ? ticketCost = 4000 :
                                                 differenceHours <= 15 ? ticketCost = 3000 :
                                                         differenceHours <= 20 ? ticketCost = 2000 :
                                                                 1000;

        flightTravelDetails.setEstimateJourneyDuration(""+differenceHours+" Hrs "+ differenceMins+ " Min(s)");
        flightTravelDetails.setTicketCost(ticketCost);
        flightTravelRepo.save(flightTravelDetails);
    }

    public void create(FlightDTO flightDTO) {
        Flight flight = mapper.toFlight(flightDTO);
        flightRepo.save(flight);
    }

    public List<FlightListDTO> getFlights(FlightSearchDTO flightSearchDTO) {
        String journeyDay;
        int from_place_id = flightSearchDTO.getFromPlace().getId();
        int to_place_id = flightSearchDTO.getToPlace().getId();
        List<Integer> scheduled_ids = new ArrayList<>(); // scheduled ids logic impl needed
        int tripTypeId = flightSearchDTO.getTripType().getId();
        LocalDateTime journeyDate  = flightSearchDTO.getDepartOn();
        System.out.println(">>> journeyDate "+journeyDate);
        System.out.println(">>> journeyDay "+journeyDate.getDayOfWeek().toString());
        journeyDay = journeyDate.getDayOfWeek().toString();

        scheduled_ids.add(1);
        switch (journeyDay){
            case "MONDAY":
                scheduled_ids.add(3);
                scheduled_ids.add(4);
                break;
            case "TUESDAY":
                scheduled_ids.add(3);
                scheduled_ids.add(5);
                break;
            case "WEDNESDAY":
                scheduled_ids.add(3);
                scheduled_ids.add(6);
                break;
            case "THURSDAY":
                scheduled_ids.add(3);
                scheduled_ids.add(7);
                break;
            case "FRIDAY":
                scheduled_ids.add(3);
                scheduled_ids.add(8);
                break;
            case "SATURDAY":
                scheduled_ids.add(2);
                scheduled_ids.add(9);
                break;
            case "SUNDAY":
                scheduled_ids.add(2);
                scheduled_ids.add(10);
                break;
        }

        List<Tuple> tuples =  flightRepo.findDesiredFlights(from_place_id, to_place_id, tripTypeId, scheduled_ids);

        List<FlightListDTO> flightListDTO = tuples.stream()
                .map(t -> new FlightListDTO(
                        t.get(0, String.class),
                        t.get(1, String.class),
                        t.get(2, Integer.class),
                        t.get(3, Time.class),
                        t.get(4, Time.class),
                        t.get(5, String.class),
                        t.get(6, Integer.class),
                        t.get(7, Integer.class)
                ))
                .collect(Collectors.toList());

        return flightListDTO;
    }

    public void update(FlightDTO flightDTO) {
        Flight flight = flightRepo.findById(flightDTO.getId()).orElseThrow(()->new EntityNotFoundException("Entity Not Found to Edit"));
        flight.setNumber(flightDTO.getNumber());
        flight.setInstrumentUsed(flightDTO.getInstrumentUsed());
        flight.setNumberOfRows(flightDTO.getNumberOfRows());
        flight.setTotalBusinessSeats(flightDTO.getTotalBusinessSeats());
        flight.setTotalNonBusinessSeats(flightDTO.getTotalNonBusinessSeats());
//        flight.setAirline(flightDTO.getAirline());
        flightRepo.save(flight);
    }

     public void blockFlight(int flightId) {
         Flight flight = flightRepo.findById(flightId).orElseThrow(() -> new EntityNotFoundException("Flight Entity Not Found to Block"));

         List<String> emailIds =  flightRepo.findUserBookedFlight(flightId);

         emailIds.stream().forEach(e-> {
             emailService.send("admin@gmail.com", e, "Flight Block Notification",
                     "Dear customer, \n The flight booked by you (Flight ID: " +flightId +" ) " +
                             "was unfortunately blocked due to technical issues. The ticket cost will be refunded asap. \n Please find an alternative! \n Sorry for inconvinience caused! \n\n By, Team Flyhigh!");
         });

         flight.setIsBlocked(true);
         flightRepo.save(flight);
     }

}
