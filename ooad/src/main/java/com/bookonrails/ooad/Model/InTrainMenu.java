package com.bookonrails.ooad.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.List;

@Entity
public class InTrainMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long Id;
    private List<MenuItem> items;
    
    public Long getId() {
        return Id;
    }
    public void setId(Long id) {
        Id = id;
    }
    public List<MenuItem> getItems() {
        return items;
    }
    public void setItems(List<MenuItem> items) {
        this.items = items;
    }
    

}
