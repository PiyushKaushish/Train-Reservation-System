package com.bookonrails.ooad.Model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class SeatAvailibility {
    @Id
    @OneToMany
    Train train;
    ClassType classes;
    Date date;
    int availableSeats;
    List<Ticket> waitingList;

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

}
