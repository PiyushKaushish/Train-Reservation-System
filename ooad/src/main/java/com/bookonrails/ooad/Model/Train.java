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

    // enum
    // @ElementCollection
    // private List<DayOfWeek> operatingDays;

    @OneToMany(mappedBy = "train",fetch = FetchType.LAZY)
    private List<OperatingDay> operatingDays;

    @OneToMany(mappedBy = "train",fetch = FetchType.LAZY)
    private List<SeatAvailability> seatAvailability;

    @OneToMany(mappedBy = "train", cascade = CascadeType.ALL)
    private List<Ticket> tickets;

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


    public List<OperatingDay> getOperatingDays() {
        return operatingDays;
    }

    public void setOperatingDays(List<OperatingDay> operatingDays) {
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
