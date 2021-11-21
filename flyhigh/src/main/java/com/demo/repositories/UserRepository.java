package com.demo.repositories;

import com.demo.entities.FlightTravelDetails;
import com.demo.entities.Ticket;
import com.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.Tuple;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(
            value = "select t.name,f.number,ftd.flight_id, ftd.from_time, ftd.to_time,\n" +
                    "ftd.estimate_journey_duration, ftd.ticket_cost\n" +
                    "from flight.ticket t\n" +
                    "inner join flight.user u on t.user_id = u.id" +
                    "where u.id = :userId",
            nativeQuery = true)
    List<Tuple> getByUserId(int userId);
}
