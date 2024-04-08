package com.bookonrails.ooad.Interface;

import java.util.Date;
import java.util.List;

import com.bookonrails.ooad.Model.*;

public interface TicketBookingManagement {
    public String generateUniquePNR();
    public boolean checkSeatAvailability(Train train,ClassType classType,Date date);
    public Ticket makeReservation(Train train,ClassType classType,Date date,User user,List<Passenger> passenger);
    public PaymentStatus initiatePayment(Ticket ticket,Payment payment);
    public boolean confirmBooking(Ticket ticket,Payment payment,PaymentStatus paymentStatus);
    public boolean cancelBooking(Ticket ticket,User user);
    public boolean cancelBooking(String PNR,User user);
    public void setTicketPayementStatus(Ticket ticket,PaymentStatus paymentStatus);
    public void setTicketStatus(Ticket ticket,TicketStatus ticketStatus);
    public void setTicketStatus(String PNR,TicketStatus ticketStatus);
    public TicketStatus getTicketStatus(Ticket ticket);
    public TicketStatus getTicketStatus(String PNR);
    public PaymentStatus getPaymentStatus(Ticket ticket);
    public PaymentStatus getPaymentStatus(String PNR);
}
