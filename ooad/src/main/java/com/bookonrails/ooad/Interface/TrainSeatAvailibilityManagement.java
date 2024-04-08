package com.bookonrails.ooad.Interface;

import java.util.Date;
import java.util.List;

import com.bookonrails.ooad.Model.*;

public interface TrainSeatAvailibilityManagement {

    public void updateSeatAvailibity(SeatAvailability seatAvailability);
    public List<SeatAvailability> getSeatAvailibity(Train train,Date date);
    public SeatAvailability getSeatAvailibity(Train train,ClassType classType,Date date);
    public SeatAvailability getSeatAvailibity(String trainNo,ClassType classType, Date date);
    
}
