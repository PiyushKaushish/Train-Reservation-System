package com.bookonrails.ooad.Service;

import com.bookonrails.ooad.Interface.TrainRouteManagement;
import com.bookonrails.ooad.Model.Route;
import com.bookonrails.ooad.Model.Station;
import com.bookonrails.ooad.Model.StationTimings;
import com.bookonrails.ooad.Model.Train;
import com.bookonrails.ooad.Repository.RouteRepository;
import com.bookonrails.ooad.Repository.StationTimingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.List;

@Service
public class TrainRouteManagementImpl implements TrainRouteManagement {

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private StationTimingsRepository stationTimingsRepository;

    @Override
    public void updateTrainRoute(Route route) {
        routeRepository.save(route);
    }

    @Override
    public Route getTrainRoute(Train train) {
        return (Route) routeRepository.findAll();
    }


    @Override
    public void addStation(Station station, Route route, Time arrivalTime, Time departureTime) {
        // Implement logic to add station to route with timings
    }

    @Override
    public void addStation(Route route, StationTimings stationTimings) {
        // Implement logic to add station timings to route
    }

    @Override
    public void updateStation(Station station, Route route, Time arrivalTime, Time departureTime) {
        // Implement logic to update station timings in route
    }

    @Override
    public void updateStation(Route route, StationTimings stationTimings) {
        // Implement logic to update station timings in route
    }

    @Override
    public void deleteStation(StationTimings stationTimings, Route route) {
        // Implement logic to delete station timings from route
    }

    @Override
    public List<StationTimings> getAllStationInRoute(Route route) {
        // Implement logic to get all station timings in route
        return null;
    }

    @Override
    public List<StationTimings> getAllStationInRoute(Long routeId) {
        // Implement logic to get all station timings in route by route ID
        return null;
    }


    @Override
    public List<StationTimings> getAllStationInRoute(String trainNo) {
        // Implement logic to get all station timings in route by train number
        return null;
    }

    @Override
    public StationTimings getStationInRoute(Route route, Station station) {
        // Implement logic to get station timings in route by station and route
        return null;
    }

    @Override
    public StationTimings getStationInRoute(String trainNo, Station station) {
        // Implement logic to get station timings in route by train number and station
        return null;
    }

    @Override
    public void addStationTiming(StationTimings stationTimings) {
        stationTimingsRepository.save(stationTimings);
    }

    @Override
    public void updateStationTiming(StationTimings stationTimings) {
        stationTimingsRepository.save(stationTimings);
    }

    @Override
    public void deleteStationTiming(StationTimings stationTimings) {
        stationTimingsRepository.delete(stationTimings);
    }

    @Override
    public StationTimings getStationTiming(Long stationTimingId) {
        return stationTimingsRepository.findById(stationTimingId).orElse(null);
    }

    @Override
    public StationTimings getStationTiming(Route route, Station station) {
        // Implement logic to get station timings by route and station
        return null;
    }

    @Override
    public StationTimings getStationTiming(String trainNo, Station station) {
        // Implement logic to get station timings by train number and station
        return null;
    }

    @Override
    public List<StationTimings> getAllStationInRoute(Train train) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllStationInRoute'");
    }

    @Override
    public Route getTrainRoute(String trainNo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTrainRoute'");
    }
}
