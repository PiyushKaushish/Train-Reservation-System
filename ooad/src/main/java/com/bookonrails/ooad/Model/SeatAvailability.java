package com.bookonrails.ooad.Model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class SeatAvailability {
    @Id
    @OneToMany
    private Train train;
    @Temporal(TemporalType.DATE)
    private Date date;
    private int no_of_coaches;
    private ClassType classes;
    private int availableSeats;
    private List<Ticket> waitingList;
    private List<Integer> CancelledSeats;
    private int lastBookedLowerSeat;
    private int lastBookedUpperSeat;

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getNo_of_coaches() {
        return no_of_coaches;
    }

    public void setNo_of_coaches(int no_of_coaches) {
        this.no_of_coaches = no_of_coaches;
    }

    public ClassType getClasses() {
        return classes;
    }

    public void setClasses(ClassType classes) {
        this.classes = classes;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public List<Ticket> getWaitingList() {
        return waitingList;
    }

    public void setWaitingList(List<Ticket> waitingList) {
        this.waitingList = waitingList;
    }

    public List<Integer> getCancelledSeats() {
        return CancelledSeats;
    }

    public void setCancelledSeats(List<Integer> cancelledSeats) {
        CancelledSeats = cancelledSeats;
    }

    public int getLastBookedLowerSeat() {
        return lastBookedLowerSeat;
    }

    public void setLastBookedLowerSeat(int lastBookedLowerSeat) {
        this.lastBookedLowerSeat = lastBookedLowerSeat;
    }

    public int getLastBookedUpperSeat() {
        return lastBookedUpperSeat;
    }

    public void setLastBookedUpperSeat(int lastBookedUpperSeat) {
        this.lastBookedUpperSeat = lastBookedUpperSeat;
    }

    // birth has only 2 preferences - Lower or Upper

}
