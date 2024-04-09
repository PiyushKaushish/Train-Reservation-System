package com.bookonrails.ooad.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookonrails.ooad.Model.Contact;
import java.util.List;


public interface ContactRepository extends JpaRepository<Contact,Long> {
    List<Contact> findByEmail(String email);
}
