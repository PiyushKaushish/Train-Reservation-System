package com.bookonrails.ooad.Model;

import jakarta.persistence.*;

@Entity
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String name;
    private int age;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private boolean isSeniorCitizen;
    private boolean isWaitingList; // true- means present in WL
    @Enumerated(EnumType.STRING)
    private Birth birthpreference;
    private int seatNo; 
    private int coachNo;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ticket_id", nullable = false)
    private Ticket ticket;

    public Passenger(){
        this.isWaitingList = false;
        this.isSeniorCitizen = false;
        this.seatNo = -1;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public boolean isSeniorCitizen() {
        return isSeniorCitizen;
    }

    public void setSeniorCitizen(boolean isSeniorCitizen) {
        this.isSeniorCitizen = isSeniorCitizen;
    }

    public Birth getBirthpreference() {
        return birthpreference;
    }

    public void setBirthpreference(Birth birthpreference) {
        this.birthpreference = birthpreference;
    }

    public int getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(int seatNo) {
        this.seatNo = seatNo;
    }

    public boolean isWaitingList() {
        return isWaitingList;
    }

    public void setWaitingList(boolean isWaitingList) {
        this.isWaitingList = isWaitingList;
    }

    public int getCoachNo() {
        return coachNo;
    }

    public void setCoachNo(int coachNo) {
        this.coachNo = coachNo;
    }

    
    
}
