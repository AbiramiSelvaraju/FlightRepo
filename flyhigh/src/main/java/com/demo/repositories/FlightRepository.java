package com.demo.repositories;

import com.demo.dto.FlightListDTO;
import com.demo.dto.FlightSearchDTO;
import com.demo.entities.Airline;
import com.demo.entities.Flight;
import com.demo.entities.Place;
import com.demo.entities.TripType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.Tuple;
import java.time.LocalDate;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Integer> {

    @Query(
            value = "select a.name,f.number,ftd.flight_id, ftd.from_time, ftd.to_time,\n" +
                    "ftd.estimate_journey_duration, ftd.ticket_cost, ftd.id \n"+
                    "from flight.flight f\n" +
                    "inner join flight.flight_travel_details ftd on f.id = ftd.flight_id\n" +
                    "left join flight.airline a on a.id = f.airline_id\n" +
                    "where ftd.from_place_id = :f_id and ftd.to_place_id = :t_id\n" +
                    "and ftd.trip_type_id= :tt_id and ftd.schedule_id in (:s_ids) and a.is_active= true",
            nativeQuery = true)
    List<Tuple> findDesiredFlights(int f_id,
                                   int t_id,
                                   int tt_id,
                                   List<Integer> s_ids);

}