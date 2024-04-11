package com.bookonrails.ooad.Interface;

import java.util.List;

import com.bookonrails.ooad.Model.Route;
import com.bookonrails.ooad.Model.Station;
import com.bookonrails.ooad.Model.StationTimings;

public interface StationTimingsManagement {

    public StationTimings addStationTimings(StationTimings stationTiming);
    public List<StationTimings> getAllStationTimings();
    public StationTimings getStationTimingById(Long id);
    public StationTimings updateStationTimings(StationTimings stationTiming);
    public void deleteStationTimings(Long id);
    public StationTimings getStationTimingsByRoute(Route route);
    public StationTimings getStationTimingsByStation(Station station);
    
}
