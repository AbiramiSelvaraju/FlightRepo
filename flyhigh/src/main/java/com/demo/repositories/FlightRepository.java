package com.demo.repositories;

import com.demo.entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.Tuple;
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
    List<Tuple> findDesiredFlights(@Param("f_id") int f_id,
                                   @Param("t_id") int t_id,
                                   @Param("tt_id") int tt_id,
                                   @Param("s_ids") List<Integer> s_ids);


    @Query(value = "select t.email_id\n" +
                    "from ticket t \n" +
                    "join flight_travel_details ftd on t.flight_travel_details_id = ftd.id\n" +
                    "join flight f on f.id = ftd.flight_id\n" +
                    "where f.id = :flightID", nativeQuery = true)
    List<String> findUserBookedFlight(@Param("flightID") int fid);

}