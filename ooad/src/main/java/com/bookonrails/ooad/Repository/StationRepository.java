package com.bookonrails.ooad.Repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookonrails.ooad.Model.Station;

@Repository
public interface StationRepository extends JpaRepository<Station,String>{
    public Station findByStationCode(String stationCode);   
    @Query("SELECT s FROM Station s WHERE s.stationName LIKE %:stationName%")
    public List<Station> findByStationName(String stationName);
    

}

