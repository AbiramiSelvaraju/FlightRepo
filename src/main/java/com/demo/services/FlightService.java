package com.demo.services;

import com.demo.dto.FlightDTO;
import com.demo.dto.FlightTravelDetailsDTO;
import com.demo.entities.Flight;
import com.demo.entities.FlightTravelDetails;
import com.demo.mapper.FlightMapper;
import com.demo.repositories.FlightRepository;
import com.demo.repositories.FlightTravelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;

import static java.util.concurrent.TimeUnit.HOURS;

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

//    Flight
    public void create(FlightDTO flightDTO) {
        Flight flight = mapper.toFlight(flightDTO);
        flightRepo.save(flight);
    }
}
