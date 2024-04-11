package com.bookonrails.ooad.Service;

import com.bookonrails.ooad.Model.Station;
import com.bookonrails.ooad.Repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationService {

    @Autowired
    private StationRepository stationRepository;

    
    public Station addStation(Station station) {
        return stationRepository.save(station);
    }

  
    public List<Station> getAllStations() {
        return stationRepository.findAll();
    }

    public Station getStationByCode(String stationCode) {
        return stationRepository.findByStationCode(stationCode);
    }

    public List<Station> searchStationByName(String stationName) {
        return stationRepository.findByStationNameContaining(stationName);
    }


    public Station updateStation(String stationCode, Station updatedStation) {
        Station station = stationRepository.findByStationCode(stationCode);
        if (station == null) {
            throw new RuntimeException("Station not found with code: " + stationCode);
        }
        station.setStationName(updatedStation.getStationName());
        return stationRepository.save(station);
    }

    public void deleteStation(String stationCode) {
        Station station = stationRepository.findByStationCode(stationCode);
        if (station == null) {
            throw new RuntimeException("Station not found with code: " + stationCode);
        }
        stationRepository.delete(station);
    }

    public List<Station> findAll(){
        return stationRepository.findAll();
    }
}
