package com.example.airlinebooking.controllers;


import com.example.airlinebooking.dtos.FlightDTO;
import com.example.airlinebooking.dtos.FlightResponseDTO;
import com.example.airlinebooking.dtos.FlightReturnDTO;
import com.example.airlinebooking.dtos.FlightSearchDTO;
import com.example.airlinebooking.models.Flight;
import com.example.airlinebooking.models.User;
import com.example.airlinebooking.repositories.FlightRepository;
import com.example.airlinebooking.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/flights")
public class FlightController{
    @Autowired
    private FlightService flightService;

    @Autowired
    private FlightRepository flightRepository;



    @PostMapping("/search")
    public  ResponseEntity<FlightResponseDTO> flightSearch(@RequestBody FlightSearchDTO flightSearchDTO){

        FlightReturnDTO flightList= flightService.searchFlights(flightSearchDTO);
        List<Flight>flightsDepart=flightList.getDepartingFlights();
        List<Flight>flightsArrival=flightList.getArrivingFlights();
        FlightResponseDTO flightResponseDTO=new FlightResponseDTO();
        for (Flight flight : flightsDepart) {
            System.out.println(flight.getFlightId()); // Assuming Flight has a meaningful `toString()` method
        }



        List<FlightDTO> flightDeparttDTOs = new ArrayList<>();
        for (Flight flight : flightsDepart) {
            FlightDTO flightDTO = new FlightDTO();
            flightDTO.setFlightId(flight.getFlightId());
            flightDTO.setFlightType(flight.getFlightType());
            System.out.println(flight.getFlightType());
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
        List<FlightDTO> flightArrivalDTOs = new ArrayList<>();
        for (Flight flight : flightsArrival) {
            FlightDTO flightDTO = new FlightDTO();

            flightDTO.setFlightId(flight.getFlightId());
            flightDTO.setFlightType(flightDTO.getFlightType());
            flightDTO.setDepartureDate(flight.getDepartureDate());
            flightDTO.setDepartureTime(flight.getDepartureTime());
            flightDTO.setArrivalDate(flight.getArrivalDate());
            flightDTO.setArrivalTime(flight.getArrivalTime());
            flightDTO.setDuration(flight.getDuration());
            flightDTO.setAirlineName(flight.getAirlineName());
            flightDTO.setPrice(flight.getPrice());
            flightDTO.setStops(flight.getStops());
            flightArrivalDTOs.add(flightDTO);
        }
        flightResponseDTO.setArrivingFlights(flightArrivalDTOs);
        flightResponseDTO.setDepartingFlights(flightDeparttDTOs);

        return ResponseEntity.ok(flightResponseDTO);



    }

    @GetMapping("/{flightId}/user")
    public User getUserbyFlightId(@PathVariable("flightId") String flightId){
        return flightRepository.findUserByFlightId(flightId);
    }

    @GetMapping("/{flightId}/getSeatIds")
    public List<String> getSeatsByFlightId(@PathVariable("flightId") String flightId){
        return flightService.getSeatsByFlightId(flightId);

    }
}
