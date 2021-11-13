package com.demo.repositories;

import com.demo.entities.FlightTravelDetails;
import com.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
