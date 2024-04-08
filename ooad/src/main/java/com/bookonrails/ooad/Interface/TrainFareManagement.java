package com.bookonrails.ooad.Interface;

import com.bookonrails.ooad.Model.*;

public interface TrainFareManagement {
    public void updateTrainFare(Train train,ClassType classType ,double basePrice,double farePerKM);
    public void updateTrainFare(SeatAvailability seatAvailability ,double basePrice,double farePerKM);
    public double getTrainFare(String trainNo,ClassType classType);    
}
