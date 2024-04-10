package com.bookonrails.ooad.Model;

import java.sql.Time;

import jakarta.persistence.*;

@Entity
public class StationTimings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "route_id", nullable = false)
    private Route route;

    @ManyToOne
    @JoinColumn(name = "stationCode", nullable = false)
    private Station station;

    private Time arrivalTime;
    private Time departureTime;
    private double distanceFromNextStation;

    public double getDistanceFromNextStation() {
        return distanceFromNextStation;
    }
    public void setDistanceFromNextStation(double distanceFromNextStation) {
        this.distanceFromNextStation = distanceFromNextStation;
    }
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
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Route getRoute() {
        return route;
    }
    public void setRoute(Route route) {
        this.route = route;
    }

    @Override
    public String toString() {
        return "Station Code:"+station.getStationCode()+" Station:"+station.getStationName()+" Arrival:"+arrivalTime.toString()+" Departure:"+departureTime.toString();
    }
    

}
