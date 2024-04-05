package com.bookonrails.ooad.Model;

import java.util.List;

import jakarta.persistence.*;


@Entity
public class Train {
    @Id
    private String trainNo;
    private String trainName;
    private String traintype;
    private int totalSeats;
    private double basePrice;
    private double farePerKM;

    // enum
    @ElementCollection
    private List<DayOfWeek> operatingDays;

    @OneToMany(mappedBy = "trainNo")
    private List<SeatAvailability> seatAvailability;

    // private InTrainMenu inTrainMenu;

    @OneToOne(
        cascade= CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinColumn(name ="route_id", referencedColumnName = "Id")
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

    public List<SeatAvailability> getSeatAvailability() {
        return seatAvailability;
    }

    public void setSeatAvailability(List<SeatAvailability> seatAvailability) {
        this.seatAvailability = seatAvailability;
    }

    // public InTrainMenu getInTrainMenu() {
    //     return inTrainMenu;
    // }

    // public void setInTrainMenu(InTrainMenu inTrainMenu) {
    //     this.inTrainMenu = inTrainMenu;
    // }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

}
