package com.example.airlinebooking.repositories;

import com.example.airlinebooking.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FlightRepository extends JpaRepository<Flight, String> {

    @Query("SELECT f FROM Flight f WHERE " +
            "f.departure_airport = :departureAirport AND " +
            "f.arrival_airport = :arrivalAirport AND " +
            "f.departureDate = :departureDate AND " +
            "f.arrivalDate = :arrivalDate")
    List<Flight> findDepartureFlights(
            @Param("departureAirport") Airport departureAirport,
            @Param("arrivalAirport") Airport arrivalAirport,
            @Param("departureDate") String departureDate);



    @Query("SELECT f FROM Flight f WHERE " +
            "f.departure_airport = :departureAirport AND " +
            "f.arrival_airport = :arrivalAirport AND " +
            "f.arrivalDate = :arrivalDate")
    List<Flight> findArrivalFlights(
            @Param("departureAirport") Airport departureAirport,
            @Param("arrivalAirport") Airport arrivalAirport,
            @Param("arrivalDate") String arrivalDate);


    Optional<Flight> findById(String id);

     // Define a custom query to retrieve passengers for a given flight ID
    @Query("SELECT p FROM Passenger p JOIN p.flights f WHERE f.flightId = :flightId")
    List<Passenger> findPassengersByFlightId(@Param("flightId") String flightId);



    User findUserByFlightId(String flightId);

    @Query("SELECT s FROM Seat s JOIN s.flights f WHERE f.flightId = :flightId")
    List<Seat> findSeatsByFlightId(@Param("flightId") String flightId);
}

