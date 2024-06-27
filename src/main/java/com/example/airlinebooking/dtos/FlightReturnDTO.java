package com.example.airlinebooking.dtos;

import com.example.airlinebooking.models.Flight;

import java.util.List;

public class FlightReturnDTO {

    private List<Flight> departingFlights;
    private List<Flight> arrivingFlights;

    public List<Flight> getDepartingFlights() {
        return departingFlights;
    }

    public void setDepartingFlights(List<Flight> departingFlights) {
        this.departingFlights = departingFlights;
    }

    public List<Flight> getArrivingFlights() {
        return arrivingFlights;
    }


    public void setArrivingFlights(List<Flight> arrivingFlights) {

        this.arrivingFlights = arrivingFlights;
    }
}
