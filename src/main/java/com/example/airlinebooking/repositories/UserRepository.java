package com.example.airlinebooking.repositories;

import com.example.airlinebooking.models.Flight;
import com.example.airlinebooking.models.Passenger;
import com.example.airlinebooking.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;
import java.util.Set;


public interface UserRepository extends JpaRepository<User,String> {
    Optional<User> findByUsernameAndPassword(String username, String password);
    Optional<User>findById(String Id);



    @Query("SELECT p.id FROM User u JOIN u.passengers p WHERE u.userID = :userID")
    List<String> findPassengerIdsByUserID(@Param("userID") String userID);




//    @Query("SELECT DISTINCT f FROM User u JOIN u.flights f WHERE u.userID = :userID")
//    List<Flight> findFlightsByUserID(@Param("userID") String userID);

    @Query("SELECT u.flights FROM User u WHERE u.userID = :userID")
    Set<Flight> findFlightsByUserID(@Param("userID") String userID);

    @Query("SELECT p.passengerId FROM Passenger p " +
            "JOIN p.users u " +
            "JOIN p.flights f " +
            "WHERE u.userID = :userId " +
            "AND f.flightId = :flightId")
    List<String> findPassengerIdsByUserIdAndFlightId(
            @Param("userId") String userId,
            @Param("flightId") String flightId
    );

    @Query("SELECT p FROM Passenger p " +
            "JOIN p.users u " +
            "JOIN p.flights f " +
            "WHERE u.userID = :userId " +
            "AND f.flightId = :flightId")
    List<Passenger> findPassengerListByUserIdAndFlightId(
            @Param("userId") String userId,
            @Param("flightId") String flightId
    );

}
