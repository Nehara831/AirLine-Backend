package com.example.airlinebooking.controllers;

import com.example.airlinebooking.models.Seat;
import com.example.airlinebooking.services.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/seats")

public class SeatController {

    @Autowired
    private SeatService seatService;


}
