package com.bookonrails.ooad.Interface;

import java.sql.Time;
import java.util.List;

import com.bookonrails.ooad.Model.*;

public interface TrainRouteManagement {
    public void updateTrainRoute(Route route);
    public Route getTrainRoute(Train train);
    public Route getTrainRoute(String trainNo);
    public void addStation(Station station,Route route, Time arrivalTime, Time departureTime);
    public void addStation(Route route, StationTimings stationTimings);
    public void updateStation(Station station,Route route, Time arrivalTime, Time departureTime);
    public void updateStation(Route route, StationTimings stationTimings);
    public void deleteStation(StationTimings stationTimings,Route route);

    public List<StationTimings> getAllStationInRoute(Route route);
    public List<StationTimings> getAllStationInRoute(Long route_id);
    public List<StationTimings> getAllStationInRoute(Train train);
    public List<StationTimings> getAllStationInRoute(String trainNo);

    public StationTimings getStationInRoute(Route route,Station station);
    public StationTimings getStationInRoute(String trainNo,Station station);
    
    // CRUD Station Timing class
    public void addStationTiming(StationTimings stationTimings);
    public void updateStationTiming(StationTimings stationTimings);
    public void deleteStationTiming(StationTimings stationTimings);
    public StationTimings getStationTiming(Long stationTimingId);
    public StationTimings getStationTiming(Route route,Station station);
    public StationTimings getStationTiming(String trainNo,Station station);
    
    
}
