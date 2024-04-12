package com.bookonrails.ooad.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookonrails.ooad.Model.OperatingDay;
import com.bookonrails.ooad.Model.Train;


@Repository
public interface OperatingDayRepository extends JpaRepository<OperatingDay, Long> {
    public List<OperatingDay> findByTrain(Train t);    
    public List<OperatingDay> findByTrainTrainNo(String trainNo);
}