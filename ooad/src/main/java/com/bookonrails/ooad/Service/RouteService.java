package com.bookonrails.ooad.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bookonrails.ooad.Model.Route;
import com.bookonrails.ooad.Repository.RouteRepository;

@Service
public class RouteService {
    @Autowired
    private RouteRepository routeRepository;

    public Route addRoute(Route route) {
        return routeRepository.save(route);
    }

    public Route getRouteByRouteCode(String routeCode) {
        return routeRepository.findByRouteCode(routeCode);
    }

    public Route getRouteById(Long routeId) {
        return routeRepository.findById(routeId).orElse(null);
    }

    public Route updateRoute(Long routeId, Route updatedRoute) {
        Route route = routeRepository.findById(routeId).orElse(null);
        if (route == null) {
            throw new RuntimeException("Route not found with id: " + routeId);
        }
        route.setRouteCode(updatedRoute.getRouteCode());
        return routeRepository.save(route);
    }

    public void deleteRoute(Long routeId) {
        Route route = routeRepository.findById(routeId).orElse(null);
        if (route == null) {
            throw new RuntimeException("Route not found with id: " + routeId);
        }
        routeRepository.delete(route);
    }

    @Transactional
    public List<String> getRouteBetweenStations(String stationCode1, String stationCode2) {
        return routeRepository.findRouteBetweenStation(stationCode1, stationCode2);
    }

    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    @Transactional 
    public List<Route> getRoutesBetweenStations(String SRC,String DEST) {
        List<Route> routes = new ArrayList<>();
        List<String> routeCodes = routeRepository.findRouteBetweenStation(SRC, DEST);
        for (String routeCode : routeCodes) {
            routes.add(routeRepository.findByRouteCode(routeCode));
        }
        return routes;
    }

    public List<Route> getRoutesByTrain(String trainName){
        return routeRepository.findByTrainTrainName(trainName);
    }
    
    public List<Route> getRoutesPassingThroughStation(String stationCode) {
        return routeRepository.findByStationTimingsStationStationCode(stationCode);
    }

}
