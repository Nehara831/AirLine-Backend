package com.example.airlinebooking.services;

import com.example.airlinebooking.dtos.FlightDTO;
import com.example.airlinebooking.dtos.PassengerDTO;
import com.example.airlinebooking.models.Flight;
import com.example.airlinebooking.models.Passenger;
import com.example.airlinebooking.models.User;
import com.example.airlinebooking.repositories.FlightRepository;
import com.example.airlinebooking.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FlightRepository flightRepository;

    public List<String> findPassengersByUserID(String userID) {
        // Use the custom query method to find passengers associated with the user
        return userRepository.findPassengerIdsByUserID(userID);
    }

    public List<String> findPassengersByUserIDAndFlightId(String userID,String flightId) {
        // Use the custom query method to find passengers associated with the user
        return userRepository.findPassengerIdsByUserIdAndFlightId(userID,flightId);
    }
    public List<PassengerDTO> findPassengerListsByUserIDAndFlightId(String userID,String flightId) {
        // Use the custom query method to find passengers associated with the user
        List<Passenger>passengers= userRepository.findPassengerListByUserIdAndFlightId(userID,flightId);
        List<PassengerDTO>passengerDTOS=new ArrayList<>();

        for (Passenger passenger:passengers){
            PassengerDTO passengerDTO=new PassengerDTO(passenger.getPassengerId(),
                    passenger.getFirstName(),
                    passenger.getMiddleName(),
                    passenger.getLastName(),
                    passenger.getSuffix(),
                    passenger.getDateOfBirth(),
                    passenger.getEmail(),
                    passenger.getContactNumber(),
                    passenger.getAge(),
                    passenger.getAddress(),
                    passenger.getNoOfBags());
            passengerDTOS.add(passengerDTO);
        }



        return passengerDTOS;
    }

    public void addUserFlight(String userID, String flightId) {
        Optional<User> userOptional = userRepository.findById(userID);
        Optional<Flight> flightOptional = flightRepository.findById(flightId);
        if (userOptional.isPresent() && flightOptional.isPresent()) {
            User user = userOptional.get();
            Flight flight = flightOptional.get();
            System.out.println(user.getFlights());
            // Add the flight to the user
            System.out.println("after");
            user.getFlights().add(flight);
            Set<Flight> flightsList = user.getFlights();

// Iterate through the list and print flight details
            for (Flight flight1 : flightsList) {
//                System.out.println("Adding flight Flight ID: " + flight1.getFlightId());
//                System.out.println("Airline Name: " + flight1.getAirlineName());
                flight1.setUser(user);
                // Print other flight details as needed
                System.out.println();
                flightRepository.save(flight1);
            }
            userRepository.save(user);

            for (Flight flight1 : flightsList) {
           System.out.println("User has Flight ID: " + flight1.getFlightId());
//                System.out.println("Airline Name: " + flight1.getAirlineName());
                //flight1.setUser(user);
                // Print other flight details as needed
                System.out.println();
                flightRepository.save(flight1);
            }

        }
    }
    public List<FlightDTO> findFlightbyUsrId(String userId){
        Set<Flight> flightsList=userRepository.findFlightsByUserID(userId);



//        Optional<User> userOptional = userRepository.findById(userId);
//        Set<Flight> flightsList=new HashSet<>();
//        if (userOptional.isPresent() ) {
//            User user = userOptional.get();
//
//             flightsList = user.getFlights();
//            for (Flight flight1 : flightsList) {
//                System.out.println("Flight ID: " + flight1.getFlightId());
//                System.out.println("Airline Name: " + flight1.getAirlineName());
//                // Print other flight details as needed
//                System.out.println();
//            }
//
//        }
        List<FlightDTO> flightDeparttDTOs = new ArrayList<>();
        for (Flight flight : flightsList) {
            FlightDTO flightDTO = new FlightDTO();
            flightDTO.setFlightId(flight.getFlightId());
            flightDTO.setFlightType(flight.getFlightType());
            flightDTO.setDepartureDate(flight.getDepartureDate());
            flightDTO.setDepartureTime(flight.getDepartureTime());
            flightDTO.setArrivalDate(flight.getArrivalDate());
            flightDTO.setArrivalTime(flight.getArrivalTime());
            flightDTO.setDuration(flight.getDuration());
            flightDTO.setAirlineName(flight.getAirlineName());
            flightDTO.setPrice(flight.getPrice());
            flightDTO.setStops(flight.getStops());
            flightDeparttDTOs.add(flightDTO);
        }

            return flightDeparttDTOs;
        }

    public List<PassengerDTO> findPassengersbyUsrId(String userId){

        Optional<User> userOptional = userRepository.findById(userId);
        Set<Passenger> passengers=new HashSet<>();

        if (userOptional.isPresent() ) {
            User user =userOptional.get();
            passengers=user.getPassengers();

        }
        List<PassengerDTO>passengerDTOS=new ArrayList<>();

        for (Passenger passenger:passengers){
            PassengerDTO passengerDTO=new PassengerDTO(passenger.getPassengerId(),
                    passenger.getFirstName(),
                    passenger.getMiddleName(),
                    passenger.getLastName(),
                    passenger.getSuffix(),
                    passenger.getDateOfBirth(),
                    passenger.getEmail(),
                    passenger.getContactNumber(),
                    passenger.getAge(),
                    passenger.getAddress(),
                    passenger.getNoOfBags());
            passengerDTOS.add(passengerDTO);
        }



        return passengerDTOS;
    }
    public void deleteFlightByFlightIdAndUserId(String userId, String flightId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            Set<Flight> flights = user.getFlights();
            Optional<Flight> flightOptional=flightRepository.findById(flightId);
            Flight flightUser=flightOptional.get();
            flightUser.setUser(null); // Remove the user reference from the flight
            flightRepository.save(flightUser);

            // Find and remove the flight by ID
            flights.removeIf(flight -> flight.getFlightId().equals(flightId));
            // Update the user entity to reflect the change
            userRepository.save(user);
            Set<Flight> flights2 = user.getFlights();
            for(Flight fly:flights2){
                System.out.println(fly.getFlightId());

            }
        }

    }
}
