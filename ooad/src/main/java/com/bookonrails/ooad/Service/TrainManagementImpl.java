package com.bookonrails.ooad.Service;

import com.bookonrails.ooad.Interface.TrainManagement;
import com.bookonrails.ooad.Model.OperatingDay;
import com.bookonrails.ooad.Model.Train;
import com.bookonrails.ooad.Repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainManagementImpl implements TrainManagement {

    @Autowired
    private TrainRepository trainRepository;

    @Override
    public void addTrain(Train train) {
        trainRepository.save(train);
    }

    @Override
    public void updateTrain(Train train) {
        trainRepository.save(train);
    }

    @Override
    public void deleteTrain(String trainNo) {
        trainRepository.deleteById(trainNo);
    }

    @Override
    public Train getTrain(String trainNo) {
        return trainRepository.findById(trainNo).orElse(null);
    }

    @Override
    public Train searchTrain(String trainNo) {
        return trainRepository.findByTrainNo(trainNo);
    }

    @Override
    public List<Train> searchTrainBetweenStation(String source, String destination) {
        // Implement logic to search trains between stations
        return null;
    }

    @Override
    public void updateOperatingDays(List<OperatingDay> dayOfWeek) {
        // Implement logic to update operating days
    }

    @Override
    public List<OperatingDay> getOperatingDays(Train train) {
        // Implement logic to get operating days for a train
        return null;
    }


}
