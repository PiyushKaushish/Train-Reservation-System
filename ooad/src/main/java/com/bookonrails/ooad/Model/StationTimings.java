package com.bookonrails.ooad.Model;

import java.sql.Time;

import jakarta.persistence.*;

@Entity
public class StationTimings {

    @ManyToOne
    @JoinColumn(name = "route_id", nullable = false)
    private Route route;

    @ManyToOne
    @JoinColumn(name = "stationCode", nullable = false)
    private Station station;

    private Time arrivalTime;
    private Time departureTime;

    public Station getStation() {
        return station;
    }
    public void setStation(Station station) {
        this.station = station;
    }
    public Time getArrivalTime() {
        return arrivalTime;
    }
    public void setArrivalTime(Time arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
    public Time getDepartureTime() {
        return departureTime;
    }
    public void setDepartureTime(Time departureTime) {
        this.departureTime = departureTime;
    }
    

}
