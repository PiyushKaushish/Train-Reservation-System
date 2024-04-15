package com.bookonrails.ooad.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookonrails.ooad.Model.Contact;
import com.bookonrails.ooad.Repository.ContactRepository;

@Service("frontendContactService")
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;

    public void saveContact(Contact contact) {
        contactRepository.save(contact);
    }

    public void deleteContact(Long id) {
        contactRepository.deleteById(id);
    }

    public Contact getContact(Long id) {
        return contactRepository.findById(id).get();
    }

    public List<Contact> getContact(String email) {
        return contactRepository.findByEmail(email);
    }

     public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }
    
}
