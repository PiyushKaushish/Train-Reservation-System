package com.bookonrails.ooad.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookonrails.ooad.Model.Passenger;
import com.bookonrails.ooad.Model.PaymentStatus;
import com.bookonrails.ooad.Model.SeatAvailability;
import com.bookonrails.ooad.Model.Station;
import com.bookonrails.ooad.Model.Ticket;
import com.bookonrails.ooad.Model.TicketStatus;
import com.bookonrails.ooad.Model.Train;
import com.bookonrails.ooad.Model.User;
import com.bookonrails.ooad.Repository.TicketRepository;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private PassengerService passengerService;

    @Autowired
    private SeatAvailabilityService seatAvailabilityService;


    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public String generateUniquePNR() {
        
        String uniqueID = UUID.randomUUID().toString();
        String pnr = uniqueID.substring(0, 6);
        pnr = "PNR"+ pnr;
        return pnr; 
    }

    public List<Ticket> getTicketByUser(User user) {
        List<Ticket> tickets = ticketRepository.findByUser(user);
        if (tickets == null || tickets.isEmpty()) {
            System.out.println("Ticket not present");
        } 
        return tickets; // Return the first and only ticket of this user
        
    }

    public Ticket updateTicket(Ticket t){
        return ticketRepository.save(t);
    }

    public Ticket getTicketById(Long id) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(id);
        if (optionalTicket.isPresent()) {
            return optionalTicket.get();
        } else {
        System.out.println("Ticket not found with ID: " + id);
        return null; // or throw an exception if preferred
    }
    }

    public Ticket saveTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public Ticket updatePNR(Long ticketId, String newPNR) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(ticketId);
        if (optionalTicket.isPresent()) {
            Ticket ticket = optionalTicket.get();
            ticket.setPNR(newPNR);
            return ticketRepository.save(ticket);
        }
        return null; // or throw an exception indicating ticket not found
    }

    public Ticket updateTrain(Long ticketId, Train newTrain) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(ticketId);
        if (optionalTicket.isPresent()) {
            Ticket ticket = optionalTicket.get();
            ticket.setTrain(newTrain);
            return ticketRepository.save(ticket);
        }
        return null; // or throw an exception indicating ticket not found
    }

    public Ticket updateSource(Long ticketId, Station newSource) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(ticketId);
        if (optionalTicket.isPresent()) {
            Ticket ticket = optionalTicket.get();
            ticket.setSRC(newSource);
            return ticketRepository.save(ticket);
        }
        return null; // or throw an exception indicating ticket not found
    }

    public Ticket updateDestination(Long ticketId, Station newDestination) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(ticketId);
        if (optionalTicket.isPresent()) {
            Ticket ticket = optionalTicket.get();
            ticket.setDEST(newDestination);
            return ticketRepository.save(ticket);
        }
        return null; // or throw an exception indicating ticket not found
    }

    public Ticket updatePassengers(Long ticketId, List<Passenger> newPassengers) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(ticketId);
        if (optionalTicket.isPresent()) {
            Ticket ticket = optionalTicket.get();
            ticket.setPassengers(newPassengers);
            return ticketRepository.save(ticket);
        }
        return null; // or throw an exception indicating ticket not found
    }

    public Ticket updateTicketDate(Long ticketId, Date newDate) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(ticketId);
        if (optionalTicket.isPresent()) {
            Ticket ticket = optionalTicket.get();
            ticket.setDate(newDate);
            return ticketRepository.save(ticket);
        }
        return null; // or throw an exception indicating ticket not found
    }

    public Ticket updateTicketStatus(Long ticketId, TicketStatus newStatus) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(ticketId);
        if (optionalTicket.isPresent()) {
            Ticket ticket = optionalTicket.get();
            ticket.setStatus(newStatus);
            return ticketRepository.save(ticket);
        }
        return null; // or throw an exception indicating ticket not found
    }


    public Ticket updatePaymentStatus(Long ticketId, PaymentStatus newPaymentStatus) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(ticketId);
        if (optionalTicket.isPresent()) {
            Ticket ticket = optionalTicket.get();
            ticket.getPayment().setStatus(newPaymentStatus);
            return ticketRepository.save(ticket);
        }
        return null; // or throw an exception indicating ticket not found
    }

    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
    }

    public Ticket allocateSeats(Ticket t){
        // get passengers
        List<Passenger> ps =t.getPassengers();
        SeatAvailability s= t.getSeatAvailability();
        s.allocatePassengerSeatNo(ps);
        List<Passenger> sp= passengerService.saveAllPassenger(ps);
        t.setPassengers(sp);
        t.setSeatAvailability(s);
        if(t.isPassengersWaiting()){
            s.addTicketToWaitingList(t);
            t.setStatus(TicketStatus.Waiting);
        }
        else{
            t.setStatus(TicketStatus.Confirmed);
        }
        seatAvailabilityService.updateSeatAvailibity(s);
        Ticket saved_ticket= saveTicket(t);
        return saved_ticket;
    }

    public List<Ticket> getCancelledTickets(){
        return ticketRepository.findByStatus(TicketStatus.Cancelled);
    }

    public List<Ticket> getConfirmedTickets(){
        return ticketRepository.findByStatus(TicketStatus.Confirmed);
    }

    public List<Ticket> getWaitingListTickets(){
        return ticketRepository.findByStatus(TicketStatus.Waiting);
    }

    public void  cancelTicket(Ticket t){
        SeatAvailability sa= t.getSeatAvailability();
        sa.cancelTicket(t);
        // Get Waiting List tickets
        List<Ticket> wlTick= sa.getWaitingList();
        for (Ticket tk :wlTick) {
            if(sa.getAvailableSeats()>0){
                // Move from waiting list passenger and allocate seat
                allocateSeats(tk);
                if(tk.isPassengersWaiting()){
                    sa.addTicketToWaitingList(t);
                    tk.setStatus(TicketStatus.Waiting);
                }
                else{
                    tk.setStatus(TicketStatus.Confirmed);
                    sa.deleteTicketFromWaitingList(tk);
                }
                ticketRepository.save(tk);
            }
            else{
                break;
            }
        }
        seatAvailabilityService.updateSeatAvailibity(sa);
        ticketRepository.save(t);
    }




}