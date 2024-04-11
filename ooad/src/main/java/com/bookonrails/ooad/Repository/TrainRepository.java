package com.bookonrails.ooad.Repository;

import com.bookonrails.ooad.Model.*;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bookonrails.ooad.Model.Route;


@Repository
public interface TrainRepository extends JpaRepository<Train, String> {
    public Train findByTrainNo(String trainNo);
    @Query("SELECT t FROM Train t WHERE t.traintype LIKE %:trainType%")
    public List<Train> findByTrainTypeContaining(String trainType);
    @Query("SELECT t FROM Train t WHERE t.trainName LIKE %:trainName%")
    List<Train> findByTrainNameContaining(String trainName);
    public Train findByRoute(Route route);
    

}
