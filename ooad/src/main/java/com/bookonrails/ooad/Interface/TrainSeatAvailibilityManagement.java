package com.bookonrails.ooad.Interface;

import java.sql.Date;
import java.util.List;

import com.bookonrails.ooad.Model.*;

public interface TrainSeatAvailibilityManagement {

    public void addSeatAvailabitity(SeatAvailability seatAvailability);
    public void deleteSeatAvailabitity(SeatAvailability seatAvailability);

    public boolean checkSeatAvailability(SeatAvailability seatAvailability);

    public void updateSeatAvailibity(SeatAvailability seatAvailability);
    public List<SeatAvailability> getAllSeatAvailabilities();

    public SeatAvailability getSeatAvailibity(Long id);
    public List<SeatAvailability> getSeatAvailibityForTrain(Train train,Date date);
    public SeatAvailability getSeatAvailibity(Train train,ClassType classType,Date date);
    
    public void addCancelledSeatNo(SeatAvailability seatAvailability,int seatNo);

    public void removeCancelledSeatNo(SeatAvailability seatAvailability,int seatNo);

    // Waiting List
    public void addWaitingList(SeatAvailability seatAvailability,Ticket waitingList);

    public void removeWaitingList(SeatAvailability seatAvailability,Ticket waitingList);

    public List<Ticket> getWaitingList(SeatAvailability seatAvailability);

    public void assignSeats(SeatAvailability seatAvailability,Ticket ticket);
   
}
