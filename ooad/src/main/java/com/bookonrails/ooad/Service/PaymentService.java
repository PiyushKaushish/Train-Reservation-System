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

    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id).orElse(null);
    }

    public Payment savePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    public Payment updateTransactionDetails(Long paymentId, PaymentStatus ps) {
        Optional<Payment> optionalPayment = paymentRepository.findById(paymentId);
        if (optionalPayment.isPresent()) {
            Payment payment = optionalPayment.get();
            payment.setStatus(ps);
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
            return paymentRepository.save(payment);
        }
        return null; 
    }

    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }
}