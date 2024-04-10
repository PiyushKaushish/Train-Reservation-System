package com.bookonrails.ooad.Model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Station {
    @Id
    private String stationCode;
    private String stationName;

    @OneToMany(mappedBy = "SRC", cascade = CascadeType.ALL)
    private List<Ticket> sourceTickets;

    @OneToMany(mappedBy = "DEST", cascade = CascadeType.ALL)
    private List<Ticket> destinationTickets;

    @OneToMany(mappedBy = "station",cascade = CascadeType.ALL)
    private List<StationTimings> stationTimings;

    public Station() {
        super();
    }

    public Station(String stationcode,String stationname) {
        this.stationCode = stationcode;
        this.stationName = stationname;
    }

    public List<StationTimings> getStationTimings() { // List of Route that pass this station
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

    public List<Train> getTrains() { // returns list of trains that pass through this station
        List<Train> trains = new ArrayList<>();
        for (StationTimings st : stationTimings) {
            trains.add(st.getRoute().getTrain());
        }
        return trains;
    }

    public void addStationTimings(StationTimings st) {
        this.stationTimings.add(st);
    }

    public void addSourceTicket(Ticket t) {
        this.sourceTickets.add(t);
    }

    public void addDestinationTicket(Ticket t) {
        this.destinationTickets.add(t);
    }

    public void removeStationTimings(StationTimings st) {
        this.stationTimings.remove(st);
    }

    public void removeSourceTicket(Ticket t) {
        this.sourceTickets.remove(t);
    }

    public void removeDestinationTicket(Ticket t) {
        this.destinationTickets.remove(t);
    }

    @Override
    public String toString(){
        return stationCode+": "+ stationName;
    }

}
