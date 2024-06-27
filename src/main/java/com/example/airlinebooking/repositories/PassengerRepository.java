package com.example.airlinebooking.repositories;

import com.example.airlinebooking.models.Passenger;
import com.example.airlinebooking.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PassengerRepository extends JpaRepository <Passenger,String>{

    Optional<Passenger> findById(String Id);
}
