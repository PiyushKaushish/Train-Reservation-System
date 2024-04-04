package com.bookonrails.ooad.Model;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String PNR;
    private Station SRC;
    private Station DEST;
    private User user;
    private List<Passenger> passengers;
    private Date date;
    private ClassType classes;
    private TicketStatus status;
    private int WaitingListNumber;
    private String bogeyNumber;
    private PaymentStatus PaymentStatus;

    public PaymentStatus getPaymentStatus() {
        return PaymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        PaymentStatus = paymentStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPNR() {
        return PNR;
    }

    public void setPNR(String pNR) {
        PNR = pNR;
    }

    public Station getSRC() {
        return SRC;
    }

    public void setSRC(Station sRC) {
        SRC = sRC;
    }

    public Station getDEST() {
        return DEST;
    }

    public void setDEST(Station dEST) {
        DEST = dEST;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
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

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    public int getWaitingListNumber() {
        return WaitingListNumber;
    }

    public void setWaitingListNumber(int waitingListNumber) {
        WaitingListNumber = waitingListNumber;
    }

    public String getBogeyNumber() {
        return bogeyNumber;
    }

    public void setBogeyNumber(String bogeyNumber) {
        this.bogeyNumber = bogeyNumber;
    }

}
