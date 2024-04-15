package com.bookonrails.ooad.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookonrails.ooad.Model.*;

@Repository
public interface StationTimingsRepository extends JpaRepository<StationTimings,Long> {
    public StationTimings findByStation(Station station);    
    public List<StationTimings> findByRoute(Route route);    
}
