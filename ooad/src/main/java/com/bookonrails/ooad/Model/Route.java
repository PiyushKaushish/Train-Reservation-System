package com.bookonrails.ooad.Model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @OneToOne(mappedBy = "route", fetch=FetchType.LAZY)
    private Train train;
    
    @OneToMany(mappedBy = "route")
    private List<StationTimings> stationTimings;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public List<StationTimings> getStationTimings() {
        return stationTimings;
    }

    public void setStationTimings(List<StationTimings> stationTimings) {
        this.stationTimings = stationTimings;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public void addStationTimings(StationTimings st){
        this.stationTimings.add(st);
    }

    public void deleteStationTimings(StationTimings st){
        this.stationTimings.remove(st);
    }
    
    // get List of Stops
    public List<String> getAllStops(){
        List<String> stops = new java.util.ArrayList<String>();
        for(StationTimings st: stationTimings){
            stops.add(st.toString());
        }
        return stops;
    }

    // get StationTimings by StationCode
    public StationTimings getStationTimingsByStationCode(String stationCode){
        for(StationTimings st: stationTimings){
            if(st.getStation().getStationCode().equals(stationCode)){
                return st;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        String route = new String();
        for(StationTimings st: stationTimings){
            route += st.toString() + "\n";
        }
        return route;
    }

}