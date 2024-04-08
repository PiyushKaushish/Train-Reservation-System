package com.bookonrails.ooad.Interface;

import java.util.List;

import com.bookonrails.ooad.Model.ClassType;
import com.bookonrails.ooad.Model.OperatingDay;
import com.bookonrails.ooad.Model.Route;
import com.bookonrails.ooad.Model.SeatAvailability;
import com.bookonrails.ooad.Model.Train;

public interface TrainManagement {
    // CRUD operations for Train
    public void addTrain(Train train);
    public void updateTrain(Train train);
    public void deleteTrain(String trainNo);
    public Train getTrain(String trainNo);

    // Search operations for Train
    public Train searchTrain(String trainNo);
    public List<Train> searchTrainBetweenStation(String source, String destination);

    public void updateOperatingDays(List<OperatingDay> dayOfWeek);
    public List<OperatingDay> getOperatingDays(Train train);


    public void updateSeatAvailibity(SeatAvailability seatAvailability);
    public List<SeatAvailability> getSeatAvailibity(Train train);
    public SeatAvailability getSeatAvailibity(Train train,ClassType classType);


}
