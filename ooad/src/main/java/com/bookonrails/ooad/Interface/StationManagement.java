package com.bookonrails.ooad.Interface;

import com.bookonrails.ooad.Model.Station;
import java.util.List;

public interface StationManagement {

    Station addStation(Station station);

    List<Station> getAllStations();

    Station getStationByCode(String stationCode);

    List<Station> searchStationByName(String stationName);

    Station updateStation(String stationCode, Station updatedStation);

    void deleteStation(String stationCode);
}
