package com.bookonrails.ooad.Interface;

import com.bookonrails.ooad.Model.Ticket;

// OCP - Open/Closed Principle
public interface TicketType {
    public boolean confirmBooking(Ticket ticket);
    public boolean cancelBooking(Ticket ticket);
}
