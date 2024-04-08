package com.bookonrails.ooad.Interface;

import com.bookonrails.ooad.Model.Ticket;
import com.bookonrails.ooad.Model.User;

public interface UserTicketCancellationManager {
    public boolean cancelTicket(Long ticketId, User user); 
    public boolean cancelTicket(String PNR, User user);
    public boolean cancelTicket(Ticket ticket, User user);
}
