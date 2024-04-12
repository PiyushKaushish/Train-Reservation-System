package com.bookonrails.ooad.Model;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;


@Entity
public class Train {
    @Id
    private String trainNo;
    private String trainName;
    public String trainType;

    @OneToMany(mappedBy = "train",fetch = FetchType.LAZY)
    private List<OperatingDay> operatingDays;

    @OneToMany(mappedBy = "train",fetch = FetchType.LAZY)
    private List<SeatAvailability> seatAvailability;

    @OneToMany(mappedBy = "train", cascade = CascadeType.ALL)
    private List<Ticket> tickets;

    @OneToOne(
        cascade= CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinColumn(name ="route_id", referencedColumnName = "Id")
    private Route route;

    public void addTicket(Ticket ticket){ 
        this.tickets.add(ticket); 
    }

    public void deleteTicket(Ticket ticket){ 
        this.tickets.remove(ticket);
    }

    public void addOperatingDay(OperatingDay day){
        operatingDays.add(day);
    }

    public void deleteOperatingDay(OperatingDay day){
        operatingDays.remove(day);
    }

    public void addSeatAvailabitity(SeatAvailability s){
        seatAvailability.add(s);
    }

    public void removeSeatAvailability(SeatAvailability s){
        seatAvailability.remove(s);
    }

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
        return trainType;
    }

    public void setTraintype(String trainType) {
        this.trainType = trainType;
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

   
    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }
    
    // returns a list of stations that the train passes through with arrival time and departure time
    public List<String> getRouteList(){
        if (this.getRoute().getStations() == null){
            return new ArrayList<>();
        }
        List<StationTimings> stationList = this.getRoute().getStationTimings();
        List<String> result = new ArrayList<>();
        for (int i=0;i < stationList.size()-1;i++){
            StationTimings currentStation = stationList.get(i);
            String entry = currentStation.toString();
            result.add(entry);
        }
        return result;
    }

    @Override
    public String toString(){
        return trainNo+": "+trainName;
    }

    public SeatAvailability getSeatAvailabilityClasswise(ClassType classes){
        List<SeatAvailability> seats= getSeatAvailability();
        for (SeatAvailability s:seats){
            if (s.getClasses().equals(classes)){
                return s;
            }
        }
        return null;
    }

    // returns number of available seats in all classes 
    public int getTotalAvailableSeats(){
        int availableSeats =0;
        for (SeatAvailability s:seatAvailability){
            availableSeats += s.getAvailableSeats();
        }
        return availableSeats;
    }

    public int getAvailableSeats(ClassType ct) {
        return getSeatAvailabilityClasswise(ct).getAvailableSeats();
    }

    public List<String> getSchedule(){
        return this.route.getAllStops();
    }

    public boolean doesPathExist(Station SRC, Station DEST){
        return route.isRoutePresent(SRC,DEST);
    }

    public Time getArrivalTime(Station station){
        return route.getStationTimingsByStationCode(station.getStationCode()).getArrivalTime();
    }

    public Time getDepartureTime(Station station){
        return route.getStationTimingsByStationCode(station.getStationCode()).getDepartureTime();
    }

    public List<StationTimings> getStationTimings(){
        return route.getStationTimings();
    }

    public boolean willTrainRunOnDayOfWeek(DayOfWeek d){
        for (OperatingDay od:operatingDays){
            if (od.getDayOfWeek().equals(d)){
                return true;
            }
        }
        return false;
    }

    public double getFare(Station SRC, Station DEST, ClassType classes){
        return getSeatAvailabilityClasswise(classes).getFare(SRC, DEST);
    }

    public double getDistanceBetweenStations(Station SRC, Station DEST){
        return route.getDistanceBetweenStations(SRC.getStationCode(), DEST.getStationCode());
    }

    public void getDurationBetweenStations(Station SRC, Station DEST){
        route.calculateJourneyTime(SRC,DEST);
    }

    

}
