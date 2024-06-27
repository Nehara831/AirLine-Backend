package com.example.airlinebooking.repositories;

import com.example.airlinebooking.models.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SeatRepository extends JpaRepository<Seat,String> {
    Optional<Seat> findById(String Id);

    List<Seat>findAll();

}
