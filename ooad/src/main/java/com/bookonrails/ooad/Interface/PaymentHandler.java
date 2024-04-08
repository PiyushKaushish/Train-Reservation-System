package com.bookonrails.ooad.Interface;

import com.bookonrails.ooad.Model.*;

public interface PaymentHandler {
    public PaymentStatus makePayment(Payment payment);

    public void updatePaymentStatus(Ticket ticket, PaymentStatus status);

    public PaymentStatus refundPayment(Ticket ticket,Payment payment);
    
}
