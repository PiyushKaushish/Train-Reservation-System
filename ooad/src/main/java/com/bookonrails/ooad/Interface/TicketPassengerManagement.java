package com.bookonrails.ooad.Interface;
import java.util.List;

import com.bookonrails.ooad.Model.*;

public interface TicketPassengerManagement {
    public void addPassenger(Ticket ticket,Passenger passenger);
    public void removePassenger(Ticket ticket,Passenger passenger);
    public void updatePassenger(Ticket ticket,Passenger passenger,Passenger newPassenger);
    public List<Passenger> getPassengers(Ticket ticket);
    public Passenger getPassenger(Ticket ticket,String passengerName);
}
