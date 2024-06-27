package com.example.airlinebooking.controllers;


import com.example.airlinebooking.repositories.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/airports")
public class AirportController {
@Autowired
    private  AirportRepository airportRepository;


    @GetMapping("/names")
    public List<String> getAllAirportNames() {
        return airportRepository.getAirportNames();
    }
}
