package com.example.airlinebooking.services;

import com.example.airlinebooking.models.Seat;
import com.example.airlinebooking.repositories.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@Service
public class SeatService {
    @Autowired
    private SeatRepository seatRepository;


}
