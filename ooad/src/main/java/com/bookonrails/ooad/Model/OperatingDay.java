package com.bookonrails.ooad.Model;

import jakarta.persistence.*;

@Entity
public class OperatingDay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "trainNo", nullable = false)
    private Train train;

    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    // Constructors, getters, and setters
}
