package com.example.airlinebooking.controllers;
import com.example.airlinebooking.dtos.FlightDTO;
import com.example.airlinebooking.dtos.PassengerDTO;
import com.example.airlinebooking.models.Flight;
import com.example.airlinebooking.models.Passenger;
import com.example.airlinebooking.models.User;
import com.example.airlinebooking.repositories.UserRepository;
import com.example.airlinebooking.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;


    @Autowired
    private UserService userService;




    @PostMapping("/login")
    public ResponseEntity<?> loginuser(@RequestBody User user){
        Optional<User> foundUser = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());

        Map<String, String> response = new HashMap<>();
        if(foundUser.isPresent()) {
            // User found, create a response map containing only the user ID.
            response.put("userId", foundUser.get().getUserID());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            // User not found, return an error message.
            response.put("message", "Invalid credentials");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/{userId}/add-flight/{flightId}")
    public ResponseEntity<String> addFlightToUser(
            @PathVariable("userId") String userId,
            @PathVariable("flightId") String flightId) {

        userService.addUserFlight(userId,flightId);
        return ResponseEntity.ok("Flight added to the user successfully.");
    }

    @GetMapping("/{userId}/flights")
    public List<FlightDTO> getFlightsForUser(@PathVariable String userId) {
        System.out.println(userId);

        return userService.findFlightbyUsrId(userId);
    }

    @GetMapping("/{userId}/passengerIds")
    public List<String> getPassengerIdsForUser(@PathVariable String userId) {
        System.out.println(userId);

        return userService.findPassengersByUserID(userId);
    }

    @GetMapping("/{userId}/{flightId}/passengerIds")
    public List<String> getPassengerIdsForUserFlight(@PathVariable String userId,
                                                     @PathVariable String flightId) {
        System.out.println(userId);
        System.out.println(flightId);

        return userService.findPassengersByUserIDAndFlightId(userId,flightId);
    }

    @GetMapping("/{userId}/{flightId}/passengerList")
    public List<PassengerDTO> getPassengerListForUserFlight(@PathVariable String userId,
                                                     @PathVariable String flightId) {
//        System.out.println(userId);
//        System.out.println(flightId);

        return userService.findPassengerListsByUserIDAndFlightId(userId,flightId);
    }

    @GetMapping("/{userId}/passengersList")
    public List<PassengerDTO> getPassengersForUser(@PathVariable String userId) {
        System.out.println(userId);

        return userService.findPassengersbyUsrId(userId);
    }

    @DeleteMapping("/{userId}/flightsDelete/{flightId}")
    public String deleteFlight(@PathVariable String userId, @PathVariable String flightId) {
        userService.deleteFlightByFlightIdAndUserId(userId, flightId);
        return "backend accessed";
    }
}





