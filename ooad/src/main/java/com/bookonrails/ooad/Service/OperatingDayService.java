package com.bookonrails.ooad.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

import com.bookonrails.ooad.Model.*;
import com.bookonrails.ooad.Repository.OperatingDayRepository;

@Service
public class OperatingDayService {
    @Autowired
    private OperatingDayRepository operatingDayRepository;

    public OperatingDay addOperatingDay(OperatingDay operatingDay) {
        return operatingDayRepository.save(operatingDay);
    }

    public OperatingDay getOperatingDayById(Long operatingDayId) {
        return operatingDayRepository.findById(operatingDayId).orElse(null);
    }

    public OperatingDay updateOperatingDay(Long operatingDayId, OperatingDay updatedOperatingDay) {
        OperatingDay operatingDay = operatingDayRepository.findById(operatingDayId).orElse(null);
        if (operatingDay == null) {
            throw new RuntimeException("OperatingDay not found with id: " + operatingDayId);
        }
        operatingDay.setDayOfWeek(updatedOperatingDay.getDayOfWeek());
        return operatingDayRepository.save(operatingDay);
    }

    public void deleteOperatingDay(Long operatingDayId) {
        OperatingDay operatingDay = operatingDayRepository.findById(operatingDayId).orElse(null);
        if (operatingDay == null) {
            throw new RuntimeException("OperatingDay not found with id: " + operatingDayId);
        }
        operatingDayRepository.delete(operatingDay);
    }

    public List<OperatingDay> getOperatingDaysByTrain(Train train){
        return operatingDayRepository.findByTrain(train);
    }

    public List<OperatingDay> getAllOperatingDays() {
        return operatingDayRepository.findAll();
    }

    public List<OperatingDay> getOperatingDaysByTrainNo(String trainNo) {
        return operatingDayRepository.findByTrainTrainNo(trainNo);
    }
}
