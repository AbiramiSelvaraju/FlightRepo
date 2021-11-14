package com.demo.services;

import com.demo.dto.FlightDTO;
import com.demo.dto.FlightListDTO;
import com.demo.dto.FlightSearchDTO;
import com.demo.dto.FlightTravelDetailsDTO;
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
import java.time.LocalDate;
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

    public List<Flight> getAllFlights(){
        return flightRepo.findAll();
    }

    public Flight createFlight(Flight m) {
        return flightRepo.save(m);
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

        flightTravelDetails.setEstimateJourneyDuration(""+differenceHours+" Hrs "+ differenceMins+ "Min(s)");
        flightTravelDetails.setTicketCost(ticketCost);
        flightTravelRepo.save(flightTravelDetails);
    }

    public void create(FlightDTO flightDTO) {
        Flight flight = mapper.toFlight(flightDTO);
        flightRepo.save(flight);
    }

    public List<FlightListDTO> getFlights(FlightSearchDTO flightSearchDTO) {

        int from_place_id = flightSearchDTO.getTravelpref().getFromPlace().getId();
        int to_place_id = flightSearchDTO.getTravelpref().getToPlace().getId();
        List<Integer> scheduled_ids = new ArrayList<>(); // scheduled ids logic impl needed
        int tripTypeId = flightSearchDTO.getTravelpref().getTripType().getId();
        LocalDate journeyDate = flightSearchDTO.getDepartOn();

        String journeyDay = journeyDate.getDayOfWeek().toString();
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
                        t.get(1, String.class),
                        t.get(2, Integer.class)
                ))
                .collect(Collectors.toList());

        return flightListDTO;
    }

}
