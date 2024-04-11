package com.bookonrails.ooad.Repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookonrails.ooad.Model.ClassType;
import com.bookonrails.ooad.Model.SeatAvailability;
import com.bookonrails.ooad.Model.Train;

@Repository
public interface SeatAvailabilityRepository extends JpaRepository<SeatAvailability,Long>{
    public SeatAvailability findByTrainAndDateAndClasses(Train train, Date date,ClassType classType);
    public List<SeatAvailability> findByTrainAndDate(Train train, Date date);    
} 