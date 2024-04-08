package com.bookonrails.ooad.Interface;

import com.bookonrails.ooad.Model.*;

// OCP - Open/Closed Principle
public interface TicketType extends TicketBookingManagement{
    public boolean confirmBooking(Ticket ticket);
    public boolean cancelBooking(Ticket ticket);
}
