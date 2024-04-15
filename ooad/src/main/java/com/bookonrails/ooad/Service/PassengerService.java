package com.bookonrails.ooad.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookonrails.ooad.Model.Passenger;
import com.bookonrails.ooad.Model.Ticket;
import com.bookonrails.ooad.Model.User;
import com.bookonrails.ooad.Repository.PassengerRepository;

@Service
public class PassengerService {
    @Autowired
    private PassengerRepository passengerRepository;

    public List<Passenger> getPassengersByTicket(Ticket ticket){
        return passengerRepository.findByTicket(ticket);
    }

    public List<Passenger> getPassengersByTicketId(Long ticketId){
        return passengerRepository.findByTicketTicketId(ticketId);
    }

    public void savePassenger(Passenger passenger){
        passengerRepository.save(passenger);
    }

    public void deletePassenger(Passenger passenger){
        passengerRepository.delete(passenger);
    }

    public void deletePassengerById(Long id){
        passengerRepository.deleteById(id);
    }

    public Passenger getPassengerById(Long id){
        return passengerRepository.findById(id).get();
    }

    public List<Passenger> getAllPassengers(){
        return passengerRepository.findAll();
    }

    public List<Passenger> getPassengerByUser(User u){
        return passengerRepository.findByUser(u);
    }

    public void deletePassengerByTicket(Ticket ticket){
        List<Passenger> passengers = passengerRepository.findByTicket(ticket);
        for(Passenger p: passengers){
            passengerRepository.delete(p);
        }
    }

    public void saveAllPassenger(List<Passenger> passengers){
        passengerRepository.saveAll(passengers);
    }
    
}
