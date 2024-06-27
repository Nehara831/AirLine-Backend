package com.example.airlinebooking.services;

import com.example.airlinebooking.dtos.PassengerAddDTO;
import com.example.airlinebooking.dtos.PassengerSeatBookingDTO;
import com.example.airlinebooking.models.Flight;
import com.example.airlinebooking.models.Passenger;
import com.example.airlinebooking.models.Seat;
import com.example.airlinebooking.models.User;
import com.example.airlinebooking.repositories.FlightRepository;
import com.example.airlinebooking.repositories.PassengerRepository;
import com.example.airlinebooking.repositories.SeatRepository;
import com.example.airlinebooking.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PassengerService {
    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private SeatRepository seatRepository;


    public Passenger addPassenger(PassengerAddDTO passengerAddDTO){
        Passenger passenger=new Passenger(
                passengerAddDTO.getDateOfBirth(),
                passengerAddDTO.getFirstName(),
                passengerAddDTO.getContactNumber1(),
                passengerAddDTO.getAddress(),
                passengerAddDTO.getSuffix(),
                passengerAddDTO.getMiddleName(),
                passengerAddDTO.getLastName(),
                passengerAddDTO.getAge(),
                passengerAddDTO.getEmail(),
                passengerAddDTO.getNoOfPassengers());




        Optional<Flight> optionalFlight=flightRepository.findById(passengerAddDTO.getFlightId());
        Optional<User> optionalUser=userRepository.findById(passengerAddDTO.getUserId());
        passengerRepository.save(passenger); // Save the passenger entity

        if (optionalFlight.isPresent()) {
            Flight flight = optionalFlight.get();

            Set<Passenger> passengerFlight = flight.getPassengers();
            passengerFlight.add(passenger);
            flightRepository.save(flight);

        } else {
            System.out.println( "Flight not found");
        }
        if (optionalUser.isPresent()) {
            User user= optionalUser.get();

            Set<Passenger> passengersUser = user.getPassengers();
            passengersUser.add(passenger);
            userRepository.save(user);


        } else {
            System.out.println( "User not found");
        }




        return passenger;
    }


    public void seatAssignment(PassengerSeatBookingDTO passengerSeatBookingDTO){

        Optional<Passenger> optionalPassenger=passengerRepository.findById(passengerSeatBookingDTO.getPassengerId());

        Optional<Flight> optionalFlight=flightRepository.findById(passengerSeatBookingDTO.getFlightId());
        Optional<Seat> optionalSeat=seatRepository.findById(passengerSeatBookingDTO.getSeatId());


        Flight flight=new Flight();
        Passenger passenger=new Passenger();
        Seat seat=new Seat();

        if (optionalFlight.isPresent()) {
             flight=optionalFlight.get();
            System.out.println(flight.getFlightId());

        }
        if (optionalPassenger.isPresent()) {
             passenger=optionalPassenger.get();
            System.out.println(passenger.getPassengerId());

        }
        if (optionalSeat.isPresent()) {
             seat =optionalSeat.get();
            System.out.println(seat.getSeatID());

        }
        Set<Passenger> flightPassengers=flight.getPassengers();
        flightPassengers.add(passenger);
        Set<Seat> flightSeats=flight.getSeats();
        flightSeats.add(seat);
        Set<Flight>passengerFlights=passenger.getFlights();
        passengerFlights.add(flight);
        Set<Seat>passengerSeats=passenger.getSeats();
        passengerSeats.add(seat);
        Set<Passenger>seatPassengers=seat.getPassengers();
        seatPassengers.add(passenger);
        Set<Flight>seatFlights=seat.getFlights();
        seatFlights.add(flight);

        flightRepository.save(flight);
        passengerRepository.save(passenger);
        seatRepository.save(seat);

        List<Passenger> testPassengers=flightRepository.findPassengersByFlightId(passengerSeatBookingDTO.getFlightId());

    }
}
