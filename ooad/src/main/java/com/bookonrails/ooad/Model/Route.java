package com.bookonrails.ooad.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.List;

@Entity
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long Id;
    private List<StattionTimings> stationTimings;
    
    public Long getId() {
        return Id;
    }
    public void setId(Long id) {
        Id = id;
    }
    public List<StattionTimings> getStationTimings() {
        return stationTimings;
    }
    public void setStationTimings(List<StattionTimings> stationTimings) {
        this.stationTimings = stationTimings;
    }
    
}
