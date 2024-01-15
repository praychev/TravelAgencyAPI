package com.example.TravelAgency.entities;

import com.example.TravelAgency.dtos.ResponseHolidayDTO;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity(name = "holiday")
public class Holiday implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String title;
    private LocalDate startDate;
    private int duration;
    private double price;
    private int freeSlots;
    @ManyToOne
    private Location location;

    public Holiday() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getCreatedDate() {
        return startDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.startDate = createdDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getFreeSlots() {
        return freeSlots;
    }

    public void setFreeSlots(int freeSlots) {
        this.freeSlots = freeSlots;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public ResponseHolidayDTO toResponseDTO(){
        return new ResponseHolidayDTO(id, title, startDate, duration, price, freeSlots, location.toResponseDTO());
    }
}