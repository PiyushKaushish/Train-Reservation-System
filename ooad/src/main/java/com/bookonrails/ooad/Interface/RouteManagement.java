package com.bookonrails.ooad.Interface;

import com.bookonrails.ooad.Model.Route;
import java.util.List;

public interface RouteManagement{
    
   
    Route addRoute(Route route);
    Route getRouteByRouteCode(String routeCode);
    Route getRouteById(Long routeId);
    Route updateRoute(Long routeId, Route updatedRoute);
    void deleteRoute(Long routeId);
    List<String> getRouteBetweenStations(String stationCode1, String stationCode2);
    List<Route> getAllRoutes();
    List<Route> getRoutesBetweenStations(String SRC, String DEST);
    List<Route> getRoutesByTrain(String trainName);
    List<Route> getRoutesPassingThroughStation(String stationCode);
}
