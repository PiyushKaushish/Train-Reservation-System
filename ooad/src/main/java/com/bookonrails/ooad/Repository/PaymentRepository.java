package com.bookonrails.ooad.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookonrails.ooad.Model.Payment;
import com.bookonrails.ooad.Model.Ticket;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Payment findByTicket(Ticket ticket);
}