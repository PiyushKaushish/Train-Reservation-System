package com.bookonrails.ooad.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookonrails.ooad.Interface.StationTimingsManagement;
import com.bookonrails.ooad.Model.Route;
import com.bookonrails.ooad.Model.Station;
import com.bookonrails.ooad.Model.StationTimings;
import com.bookonrails.ooad.Repository.StationTimingsRepository;

@Service
public class StationTimingsService implements StationTimingsManagement {

    @Autowired
    private StationTimingsRepository stationTimingsRepository;

    public List<StationTimings> getAllStationTimings() {
        return stationTimingsRepository.findAll();
    }

    public StationTimings getStationTimingsById(Long id) {
        return stationTimingsRepository.findById(id).orElse(null);
    }

    public StationTimings getStationTimingsByStation(Station station) {
        return stationTimingsRepository.findByStation(station);
    }

    public List<StationTimings> getStationTimingsByRoute(Route route) {
        return stationTimingsRepository.findByRoute(route);
    }

    public StationTimings addStationTimings(StationTimings stationTimings) {
        return stationTimingsRepository.save(stationTimings);
    }

    public List<StationTimings> addStationTimingsList(List<StationTimings> s) {
        List<StationTimings> n = new ArrayList<>();
        for (StationTimings st : s) {
            n.add(addStationTimings(st));
        }
        return n;
    }

    public StationTimings updateStationTimings(Long id, StationTimings updatedStationTimings) {
        StationTimings existingStationTimings = stationTimingsRepository.findById(id).orElse(null);
        if (existingStationTimings != null) {
            existingStationTimings.setStation(updatedStationTimings.getStation());
            existingStationTimings.setRoute(updatedStationTimings.getRoute());
            existingStationTimings.setArrivalTime(updatedStationTimings.getArrivalTime());
            existingStationTimings.setDepartureTime(updatedStationTimings.getDepartureTime());
            existingStationTimings.setDistanceFromNextStation(updatedStationTimings.getDistanceFromNextStation());
            return stationTimingsRepository.save(existingStationTimings);
        }
        return null;
    }

    public void deleteStationTimings(Long id) {
        stationTimingsRepository.deleteById(id);
    }

}
