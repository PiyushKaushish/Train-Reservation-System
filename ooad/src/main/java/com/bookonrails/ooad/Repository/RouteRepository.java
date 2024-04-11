package com.bookonrails.ooad.Repository;

import java.util.List;

// import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bookonrails.ooad.Model.Route;

// import jakarta.persistence.EntityManager;

// import org.springframework.beans.factory.annotation.Autowired;


@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
    Route findByRouteCode(String routeCode);

    @Procedure(name = "FindRouteBetweenStation")
    public List<String> findRouteBetweenStation(@Param("stationCode1") String stationCode1, @Param("stationCode2") String stationCode2);

    List<Route> findByTrainTrainName(String trainName);

    List<Route> findByStationTimingsStationStationCode(@Param("stationCode") String stationCode);

}