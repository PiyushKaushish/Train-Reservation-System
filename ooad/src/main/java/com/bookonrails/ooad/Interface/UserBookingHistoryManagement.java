package com.bookonrails.ooad.Interface;

import java.util.List;

import com.bookonrails.ooad.Model.Ticket;
import com.bookonrails.ooad.Model.TicketStatus;
import com.bookonrails.ooad.Model.User;

public interface UserBookingHistoryManagement {
    public List<Ticket> getBookingHistory(User user);
    public Ticket searchTicket(String PNR);
    public Ticket getBookingDetails(Ticket ticket);
    public TicketStatus checkPNRStatus(String PNR);
}
