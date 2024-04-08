package com.bookonrails.ooad.Model;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class Station {
    @Id
    private String stationCode;
    private String stationName;

    @OneToMany(mappedBy = "station", cascade = CascadeType.ALL)
    private List<Ticket> sourceTickets;

    @OneToMany(mappedBy = "station", cascade = CascadeType.ALL)
    private List<Ticket> destinationTickets;

    @OneToMany(mappedBy = "station",cascade = CascadeType.ALL)
    private List<StationTimings> stationTimings;

    public List<StationTimings> getStationTimings() {
        return stationTimings;
    }

    public void setStationTimings(List<StationTimings> stationTimings) {
        this.stationTimings = stationTimings;
    }

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }


    public List<Ticket> getSourceTickets() {
        return sourceTickets;
    }

    public void setSourceTickets(List<Ticket> sourceTickets) {
        this.sourceTickets = sourceTickets;
    }

    public List<Ticket> getDestinationTickets() {
        return destinationTickets;
    }

    public void setDestinationTickets(List<Ticket> destinationTickets) {
        this.destinationTickets = destinationTickets;
    }
}
