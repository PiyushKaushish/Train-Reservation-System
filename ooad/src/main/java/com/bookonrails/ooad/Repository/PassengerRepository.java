package com.bookonrails.ooad.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookonrails.ooad.Model.Passenger;
import com.bookonrails.ooad.Model.Ticket;
import com.bookonrails.ooad.Model.User;


@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long>{
    List<Passenger> findByTicket(Ticket ticket);
    List<Passenger> findByUser(User user);

}
