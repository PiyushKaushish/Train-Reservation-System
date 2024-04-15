package com.bookonrails.ooad.Service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// import com.bookonrails.ooad.Interface.TrainSeatAvailibilityManagement;
import com.bookonrails.ooad.Model.ClassType;
import com.bookonrails.ooad.Model.SeatAvailability;
import com.bookonrails.ooad.Model.Ticket;
import com.bookonrails.ooad.Model.Train;
import com.bookonrails.ooad.Repository.SeatAvailabilityRepository;

@Service
public class SeatAvailabilityService // extends TrainSeatAvailibilityManagement
{
    @Autowired
    private SeatAvailabilityRepository seatAvailabilityRepository;

    public SeatAvailability findById(Long sa){
        return seatAvailabilityRepository.findById(sa).orElse(null);
    }
    public void addSeatAvailabitity(SeatAvailability seatAvailability){
        seatAvailabilityRepository.save(seatAvailability);
    }

    
    public void deleteSeatAvailabitity(SeatAvailability seatAvailability){
        if(seatAvailability==null){
            throw new IllegalArgumentException("Seat availability object is null or has no ID.");
        }
        seatAvailabilityRepository.delete(seatAvailability);
    }

    
    public boolean checkSeatAvailability(SeatAvailability seatAvailability){
        return seatAvailability.isFull();
    }

    
    public void updateSeatAvailibity(SeatAvailability seatAvailability){
        if (seatAvailability == null || seatAvailability.getId() == null) {
            throw new IllegalArgumentException("Seat availability object is null or has no ID.");
        }
        seatAvailabilityRepository.save(seatAvailability);
    }

    public List<SeatAvailability> getAllSeatAvailabilities(){
        return seatAvailabilityRepository.findAll();
    }

    public SeatAvailability getSeatAvailibity(Long id){
        return seatAvailabilityRepository.findById(id).orElse(null);
    }
    
    public List<SeatAvailability> getSeatAvailibityForTrain(Train train,Date date){
        return seatAvailabilityRepository.findByTrainAndDate(train, date);
    }
    public SeatAvailability getSeatAvailibity(Train train,ClassType classType,Date date){
        return seatAvailabilityRepository.findByTrainAndDateAndClasses(train, date, classType);
    }

    
    public void addCancelledSeatNo(SeatAvailability seatAvailability,int seatNo){
        seatAvailability.addCancelledSeat(seatNo);
        updateSeatAvailibity(seatAvailability);
    }
    
    public void removeCancelledSeatNo(SeatAvailability seatAvailability,int seatNo){
        seatAvailability.deleteCancelledSeat(seatNo);
        updateSeatAvailibity(seatAvailability);
    }
    
    // Waiting List
    
    public void addWaitingList(SeatAvailability seatAvailability,Ticket waitingList){
        seatAvailability.addTicketToWaitingList(waitingList);
        updateSeatAvailibity(seatAvailability);
    }
    
    
    public void removeWaitingList(SeatAvailability seatAvailability,Ticket waitingList){
        seatAvailability.deleteTicketFromWaitingList(waitingList);
        updateSeatAvailibity(seatAvailability);
    }

    public List<Ticket> getWaitingList(SeatAvailability seatAvailability){
        return seatAvailability.getWaitingList();

    }



   


}
