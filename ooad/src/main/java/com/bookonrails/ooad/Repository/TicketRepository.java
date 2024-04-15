package com.bookonrails.ooad.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookonrails.ooad.Model.Ticket;
import com.bookonrails.ooad.Model.TicketStatus;
import com.bookonrails.ooad.Model.User;


@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    public Ticket findByPNR(String PNR);
    public List<Ticket> findByUser(User user);
    public List<Ticket> findByTrain_TrainNo(String trainNo);
    public List<Ticket> findByStatus(TicketStatus status);
}
