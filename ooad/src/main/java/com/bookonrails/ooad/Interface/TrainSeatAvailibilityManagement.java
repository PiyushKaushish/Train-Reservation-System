package com.bookonrails.ooad.Interface;

import java.util.Date;
import java.util.List;

import com.bookonrails.ooad.Model.*;

public interface TrainSeatAvailibilityManagement {

    public void updateSeatAvailibity(SeatAvailability seatAvailability);
    public List<SeatAvailability> getSeatAvailibity(Train train,Date date);
    public SeatAvailability getSeatAvailibity(Train train,ClassType classType,Date date);
    public SeatAvailability getSeatAvailibity(String trainNo,ClassType classType, Date date);
    
    public void updateSeatAvailibityLastBookedLowerSeats(SeatAvailability seatAvailability);
    public void updateSeatAvailibityLastBookedLowerSeats(Train train,ClassType classType,Date date);

    public void updateSeatAvailibityLastBookedUpperSeats(SeatAvailability seatAvailability);
    public void updateSeatAvailibityLastBookedUpperSeats(Train train,ClassType classType,Date date);

    public void addCancelledSeatNo(SeatAvailability seatAvailability,int seatNo);
    public void addCancelledSeatNo(Train train,ClassType classType,Date date,int seatNo);

    public void removeCancelledSeatNo(SeatAvailability seatAvailability,int seatNo);
    public void removeCancelledSeatNo(Train train,ClassType classType,Date date,int seatNo);

    public List<Integer> getCancelledSeatNo(SeatAvailability seatAvailability);
    public List<Integer> getCancelledSeatNo(Train train,ClassType classType,Date date);

    // Waiting List
    public void addWaitingList(SeatAvailability seatAvailability,Ticket waitingList);
    public void addWaitingList(Train train,ClassType classType,Date date,Ticket waitingList);

    // take one ticket out of Waiting List and assign seat no for the certain set of passengers in the ticket
    public void assignSeatNoFromWaitingList(SeatAvailability seatAvailability,Ticket waitingList);
    public void assignSeatNoFromWaitingList(Train train,ClassType classType,Date date,Ticket waitingList);

    public void removeWaitingList(SeatAvailability seatAvailability,Ticket waitingList);
    public void removeWaitingList(Train train,ClassType classType,Date date,Ticket waitingList);

    public List<Ticket> getWaitingList(SeatAvailability seatAvailability);
    public List<Ticket> getWaitingList(Train train,ClassType classType,Date date);
   
}
