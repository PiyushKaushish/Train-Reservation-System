package com.bookonrails.ooad.Model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Train {
    @Id
    private String trainNo;
    private String trainName;
    private String traintype;
    private int totalSeats;
    private double basePrice;
    private double farePerKM;
    private List<DayOfWeek> operatingDays;
    private SeatAvailibility seatAvailability;
    private InTrainMenu inTrainMenu;
    private Route route;

    public String getTrainNo() {
        return this.trainNo;
    }

    public void setTrainNo(String trainNo) {
        this.trainNo = trainNo;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public String getTraintype() {
        return traintype;
    }

    public void setTraintype(String traintype) {
        this.traintype = traintype;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public double getFarePerKM() {
        return farePerKM;
    }

    public void setFarePerKM(double farePerKM) {
        this.farePerKM = farePerKM;
    }

    public List<DayOfWeek> getOperatingDays() {
        return operatingDays;
    }

    public void setOperatingDays(List<DayOfWeek> operatingDays) {
        this.operatingDays = operatingDays;
    }

    public SeatAvailibility getSeatAvailability() {
        return seatAvailability;
    }

    public void setSeatAvailability(SeatAvailibility seatAvailability) {
        this.seatAvailability = seatAvailability;
    }

    public InTrainMenu getInTrainMenu() {
        return inTrainMenu;
    }

    public void setInTrainMenu(InTrainMenu inTrainMenu) {
        this.inTrainMenu = inTrainMenu;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

}
