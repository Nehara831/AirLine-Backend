package com.example.airlinebooking.repositories;

import com.example.airlinebooking.models.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AirportRepository extends JpaRepository<Airport,String> {


        @Query("SELECT a FROM Airport a WHERE LOWER(a.airportName) LIKE LOWER(CONCAT('%', :airportName, '%'))")
        Airport findByName(@Param("airportName") String airportName);

        @Query("SELECT a.airportName FROM Airport a")
        List<String> getAirportNames();


    }



