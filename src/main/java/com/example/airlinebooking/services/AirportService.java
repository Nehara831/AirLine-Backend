package com.example.airlinebooking.services;

import com.example.airlinebooking.models.Airport;
import com.example.airlinebooking.repositories.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AirportService {
    @Autowired
    private AirportRepository airportRepository;



    public Airport getAirportByName(String airportName) {
        return airportRepository.findByName(airportName);
    }
}
