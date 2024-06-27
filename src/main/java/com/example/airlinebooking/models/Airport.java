package com.example.airlinebooking.models;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Airport")
public class Airport {

    @Id
    private String airportId;
    private String airportName;
    private String city;
    private String country;


    @OneToMany(mappedBy = "arrival_airport",fetch = FetchType.LAZY)
    private Set<Flight> arrival_flights= new HashSet<>();


    @OneToMany(mappedBy = "departure_airport",fetch = FetchType.LAZY)
    private Set<Flight> departure_flights= new HashSet<>();

    public String getAirportId() {
        return airportId;
    }


    public void setAirportId(String airportId) {
        this.airportId = airportId;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}


