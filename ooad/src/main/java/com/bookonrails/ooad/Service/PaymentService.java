package com.bookonrails.ooad.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookonrails.ooad.Model.Payment;
import com.bookonrails.ooad.Model.PaymentStatus;
import com.bookonrails.ooad.Repository.PaymentRepository;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Optional<Payment> getPaymentById(Long id) {
        return paymentRepository.findById(id);
    }

    public Payment savePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    public Payment updateTransactionDetails(Long paymentId, String orderId, double transactionAmountValue, String transactionAmountCurrency, String posId, String paytmTransactionId) {
        Optional<Payment> optionalPayment = paymentRepository.findById(paymentId);
        if (optionalPayment.isPresent()) {
            Payment payment = optionalPayment.get();
            payment.setOrderId(orderId);
            payment.setTransactionAmountValue(transactionAmountValue);
            payment.setTransactionAmountCurrency(transactionAmountCurrency);
            payment.setPosId(posId);
            payment.setPaytmTransactionId(paytmTransactionId);
            return paymentRepository.save(payment);
        }
        return null; 
    }

    public Payment updateTicketPrice(Long paymentId, double ticketPrice) {
        Optional<Payment> optionalPayment = paymentRepository.findById(paymentId);
        if (optionalPayment.isPresent()) {
            Payment payment = optionalPayment.get();
            payment.setTicketPrice(ticketPrice);
            return paymentRepository.save(payment);
        }
        return null; 
    }

    public Payment updateFoodPrice(Long paymentId, double foodPrice) {
        Optional<Payment> optionalPayment = paymentRepository.findById(paymentId);
        if (optionalPayment.isPresent()) {
            Payment payment = optionalPayment.get();
            payment.setFoodPrice(foodPrice);
            return paymentRepository.save(payment);
        }
        return null; 
    }

    public Payment updatePaymentStatus(Long paymentId, PaymentStatus status) {
        Optional<Payment> optionalPayment = paymentRepository.findById(paymentId);
        if (optionalPayment.isPresent()) {
            Payment payment = optionalPayment.get();
            payment.setStatus(status);
            return paymentRepository.save(payment);
        }
        return null; 
    }

    public Payment updatePrebookingDetails(Long paymentId, boolean prebooked, boolean isVeg) {
        Optional<Payment> optionalPayment = paymentRepository.findById(paymentId);
        if (optionalPayment.isPresent()) {
            Payment payment = optionalPayment.get();
            payment.setPrebooked(prebooked);
            payment.setVeg(isVeg);
            return paymentRepository.save(payment);
        }
        return null; 
    }

    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }
}