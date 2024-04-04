package com.bookonrails.ooad.Model;
import jakarta.persistence.*;

@Entity
public class Payment {
    @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;
   private Ticket ticket;
   private double amount;
   private PaymentStatus status;

public int getId() {
    return id;
}
public void setId(int id) {
    this.id = id;
}
public Ticket getTicket() {
    return ticket;
}
public void setTicket(Ticket ticket) {
    this.ticket = ticket;
}
public double getAmount() {
    return amount;
}
public void setAmount(double amount) {
    this.amount = amount;
}
public PaymentStatus getStatus() {
    return status;
}
public void setStatus(PaymentStatus status) {
    this.status = status;
}
       
}
