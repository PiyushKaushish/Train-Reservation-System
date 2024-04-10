package com.bookonrails.ooad.Model;

import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;

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
    public List<Station> getStations() {
        List<Station> stations = new ArrayList<>();
        for(StationTimings st: stationTimings){
            stations.add(st.getStation());
        }
        return stations;
    }
    
    // to check if a particular station is present given station code from the list of all stations in the Route
    // returns index; -1 if not present
    public int isStationPresent(String stationCode){ 
        for(StationTimings st: stationTimings){
            if(st.getStation().getStationCode().equals(stationCode)){
                return stationTimings.indexOf(st);
            }
        }
        return -1;
    }

    // to get the next station by station code from the list of all stations in the Route
    public StationTimings getNextStation(String currentStationCode){
        for(int i=0; i<stationTimings.size(); i++){
            if(stationTimings.get(i).getStation().getStationCode().equals(currentStationCode)){
                if(i+1 < stationTimings.size()){
                    return stationTimings.get(i+1);
                }
            }
        }
        return null; // returns null if that is the last station
    }

    // to get the previous station by station code from the list of all stations in the Route
    public StationTimings getPreviousStation(String currentStationCode){
        for(int i=0; i<stationTimings.size(); i++){
            if(stationTimings.get(i).getStation().getStationCode().equals(currentStationCode)){
                if(i-1 >= 0){
                    return stationTimings.get(i-1);
                }
            }
        }
        return null; // returns null if that is the first station
    }

    // to get the distance between two stations by station code from the list of all stations in the Route
    public double getDistanceBetweenStations(String stationCode1, String stationCode2){
        int distance = 0;
        boolean sourceFlag=false;
        boolean destFlag=false;
        for(StationTimings st: stationTimings){
            if(st.getStation().getStationCode().equals(stationCode1)){
                sourceFlag=true;
            }
            if(st.getStation().getStationCode().equals(stationCode2)){
                destFlag=true;
            }
            if(sourceFlag && !destFlag){
                System.out.print(st.getStation().getStationCode());
                System.out.println(st.getDistanceFromNextStation());

                distance += st.getDistanceFromNextStation();
            }
            if(sourceFlag && destFlag){
                return distance;
            }
        }
        return -1.0; // returns -1 if the stations are not present in the route
    }

    // checks if a route exists in a particular train or not
    public boolean isRoutePresent(Station src, Station dest){
        int s= isStationPresent(src.getStationCode());
        int d=isStationPresent(dest.getStationCode());
        return s>=0 && d>=0 && s<d; // both should be present to create a route
    }
    
}