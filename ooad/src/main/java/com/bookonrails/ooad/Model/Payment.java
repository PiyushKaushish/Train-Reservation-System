package com.bookonrails.ooad.Model;

import jakarta.persistence.*;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_id", referencedColumnName = "ticket_id")
    private Ticket ticket;

    private String orderId; // Attribute for Order_ID

    private double transactionAmountValue; // Attribute for transaction amount value

    private String transactionAmountCurrency = "INR"; // Attribute for transaction amount currency

    private String posId; // Attribute for POS_ID

    private String paytmTransactionId; // Attribute for Paytm Transaction ID

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public double getTransactionAmountValue() {
        return transactionAmountValue;
    }

    public void setTransactionAmountValue(double transactionAmountValue) {
        this.transactionAmountValue = transactionAmountValue;
    }

    public String getTransactionAmountCurrency() {
        return transactionAmountCurrency;
    }

    public void setTransactionAmountCurrency(String transactionAmountCurrency) {
        this.transactionAmountCurrency = transactionAmountCurrency;
    }

    public String getPosId() {
        return posId;
    }

    public void setPosId(String posId) {
        this.posId = posId;
    }

    public String getPaytmTransactionId() {
        return paytmTransactionId;
    }

    public void setPaytmTransactionId(String paytmTransactionId) {
        this.paytmTransactionId = paytmTransactionId;
    }

   

}
