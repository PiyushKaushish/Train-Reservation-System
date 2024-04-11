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

    private double ticketPrice; // Attribute for ticket price

    private double foodPrice; // Attribute for food price

    private boolean prebooked; // Flag to indicate if food is precooked
    
    private boolean isVeg; // Flag to indicate if food is veg (true) or non-veg (false)

    
    private String paytmTransactionId; // Attribute for Paytm Transaction ID
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    public double getTotalPrice() {
        if (prebooked) {
            return ticketPrice + foodPrice;
        } else {
            return ticketPrice;
        }
    }

    public void calculateTotalPrice(boolean isPrebooked, boolean isVeg) {
        this.prebooked = isPrebooked;
        this.isVeg = isVeg;
        if (isPrebooked) {
            FoodPrice foodPrice = new FoodPrice(); // Create an instance of FoodPrice
            this.foodPrice = isVeg ? foodPrice.getVegprice() : foodPrice.getNonvegprice();
        } else {
            this.foodPrice = 0.0; // No food price if not precooked
        }
    }

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

    /*public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    */

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

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public double getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(double foodPrice) {
        this.foodPrice = foodPrice;
    }

    public boolean isPrebooked() {
        return prebooked;
    }

    public void setPrebooked(boolean prebooked) {
        this.prebooked = prebooked;
    }

    public boolean isVeg() {
        return isVeg;
    }

    public void setVeg(boolean isVeg) {
        this.isVeg = isVeg;
    }

}
