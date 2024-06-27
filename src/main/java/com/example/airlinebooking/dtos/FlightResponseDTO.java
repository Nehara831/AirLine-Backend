package com.example.airlinebooking.dtos;

import com.example.airlinebooking.models.Flight;

import java.util.List;

public class FlightResponseDTO {

    private List<FlightDTO> departingFlights;
    private List<FlightDTO> arrivingFlights;

    public List<FlightDTO> getDepartingFlights() {
        return departingFlights;
    }

    public void setDepartingFlights(List<FlightDTO> departingFlights) {
        this.departingFlights = departingFlights;
    }

    public List<FlightDTO> getArrivingFlights() {
        return arrivingFlights;
    }

    public void setArrivingFlights(List<FlightDTO> arrivingFlights) {
        this.arrivingFlights = arrivingFlights;
    }
}
