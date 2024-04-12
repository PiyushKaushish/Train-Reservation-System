package com.bookonrails.ooad.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookonrails.ooad.Model.Passenger;
import com.bookonrails.ooad.Model.PaymentStatus;
import com.bookonrails.ooad.Model.Station;
import com.bookonrails.ooad.Model.Ticket;
import com.bookonrails.ooad.Model.TicketStatus;
import com.bookonrails.ooad.Model.Train;
import com.bookonrails.ooad.Repository.TicketRepository;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
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

    public Ticket updateBogeyNumber(Long ticketId, String newBogeyNumber) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(ticketId);
        if (optionalTicket.isPresent()) {
            Ticket ticket = optionalTicket.get();
            ticket.setBogeyNumber(newBogeyNumber);
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
}