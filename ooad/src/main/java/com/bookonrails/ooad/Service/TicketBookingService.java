package com.bookonrails.ooad.Service;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bookonrails.ooad.Model.*;
import com.bookonrails.ooad.Interface.TicketBookingManagement;
import com.bookonrails.ooad.Model.ClassType;
import com.bookonrails.ooad.Model.Passenger;
import com.bookonrails.ooad.Model.Payment;
import com.bookonrails.ooad.Model.PaymentStatus;
import com.bookonrails.ooad.Model.Ticket;
import com.bookonrails.ooad.Model.TicketStatus;
import com.bookonrails.ooad.Model.Train;
import com.bookonrails.ooad.Model.User;
import com.bookonrails.ooad.Repository.PaymentRepository;
import com.bookonrails.ooad.Repository.TicketRepository;



@Service
public class TicketBookingService implements TicketBookingManagement {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    
    public String generateUniquePNR() {
        
        String uniqueID = UUID.randomUUID().toString();
        String pnr = uniqueID.substring(0, 6);
        pnr = "PNR"+ pnr;
        return pnr; 
    }

    
    public Ticket makeReservation(Train train,ClassType classType,Date date,User user,List<Passenger> passenger,Station SRC,Station DEST){

        if (train == null || classType == null || date == null || user == null || passenger == null || passenger.isEmpty()) {
            throw new IllegalArgumentException("Invalid input parameters");
        }

        Ticket ticket = new Ticket();
        String pnr =generateUniquePNR();
        ticket.setPNR(pnr) ;
        ticket.setDEST(DEST);
        ticket.setSRC(SRC);
        ticket.setTrain(train);
        ticket.setClasses(classType);
        ticket.setDate(date);
        ticket.setUser(user);
        ticket.setPassengers(passenger);
        return ticket;

    }

    
    public PaymentStatus initiatePayment(Ticket ticket, Payment payment) {
        // Logic to initiate payment
        return PaymentStatus.Pending; // Placeholder value
    }

    
    public boolean confirmBooking(Ticket ticket, Payment payment, PaymentStatus paymentStatus) {
        // Logic to confirm booking
        if (paymentStatus == PaymentStatus.Success) {
            ticket.setStatus(TicketStatus.Confirmed); // Update ticket status to CONFIRMED
            // Perform any additional booking confirmation logic here
            return true; }
            return false;
            
    }

    
    public boolean cancelBooking(Ticket ticket, User user) {
        // Logic to cancel booking
        if (ticket == null || user == null) {
            throw new IllegalArgumentException("Invalid ticket or user");
        }
    
        // Check authorization
        if (!ticket.getUser().equals(user)) {
            System.out.println("User is not authorized to cancel this booking");
        }
        

        // Update ticket status to Cancelled
        ticket.setStatus(TicketStatus.Cancelled);
        
        // List<Integer> cancelledSeats = new ArrayList<>();
        // for (Passenger passenger : ticket.getPassengers()) {
        //     if (passenger.isBookingCancelled()) {  //model me method must be added
        //         cancelledSeats.add(passenger.getSeatNo());
        //     }
        // }
    
        // // Append the cancelled seats to the SeatAvailability
        // seatAvailability.addToCancelledSeats(cancelledSeats);
        return true; // Placeholder value
    }

    
    public boolean cancelBooking(String PNR, User user) {
        if (PNR == null || PNR.isEmpty() || user == null) {
            throw new IllegalArgumentException("Invalid PNR or user");
        }
    
        // Retrieve the ticket by PNR
        Ticket ticket = ticketRepository.findByPNR(PNR);
    
        // Check if the ticket exists
        if (ticket == null) {
            System.out.println("Ticket not found for PNR: " + PNR);
        }
    
        // Call the cancelBooking method with the retrieved ticket and user
        cancelBooking(ticket, user);

        return true;
    }


    
    public void setTicketPayementStatus(Ticket ticket, PaymentStatus paymentStatus) {
        
        if(ticket==null){
            System.out.println("Invalid Ticket");
        }
        ticket.setPaymentStatus(paymentStatus);

        // Save the updated ticket to the database
        ticketRepository.save(ticket);
    }

    
    public void setTicketStatus(Ticket ticket, TicketStatus ticketStatus) {
        if(ticket==null){
            System.out.println("Invalid Ticket");
        }
        ticket.setStatus(ticketStatus);
        ticketRepository.save(ticket);
        
    }

    
    public void setTicketStatus(String PNR, TicketStatus ticketStatus) {
        // Logic to set ticket status by PNR
        Ticket ticket = ticketRepository.findByPNR(PNR);
        if (ticket != null) {
            // Set the ticket status
            ticket.setStatus(ticketStatus);
    
            // Save the updated ticket to the database
            ticketRepository.save(ticket);
        } else {
            // Handle the case when the ticket with the provided PNR is not found
            System.out.println("Ticket with PNR " + PNR + " not found");
        }
    }

    
    public TicketStatus getTicketStatus(Ticket ticket) {
        if(ticket==null){
            System.out.println("Invalid Ticket");
        }
        return ticket.getStatus();
    }

    
    public TicketStatus getTicketStatus(String PNR) {
        Ticket ticket = ticketRepository.findByPNR(PNR);

        // Check if the ticket exists
        if (ticket == null) {
            System.out.println("Ticket not found");}

        return ticket.getStatus();   
       
    }

    
    public PaymentStatus getPaymentStatus(Ticket ticket) {
        Payment payment = paymentRepository.findByTicket(ticket);

        if(payment==null){
            throw new RuntimeException("No associated payment for this ticket.");
        } 

        return payment.getStatus();
    }


    
    public PaymentStatus getPaymentStatus(String PNR) {
        Ticket ticket = ticketRepository.findByPNR(PNR);
        Payment payment = paymentRepository.findByTicket(ticket);

        if(payment==null){
            throw new RuntimeException("No associated payment for this ticket.");
        } 

        return payment.getStatus();

    }


    @Override
    public Ticket makeReservation(Train train, ClassType classType, java.sql.Date date, User user,
            List<Passenger> passenger) {
            // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'makeReservation'");
    }
}
