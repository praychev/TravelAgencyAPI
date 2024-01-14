package com.example.TravelAgency.dtos;

import com.example.TravelAgency.entities.Location;
import jakarta.persistence.OneToOne;

import java.time.LocalDate;

public class CreateHolidayDTO {
    private String Title;
    private LocalDate createdDate;
    private int duration;
    private double price;
    private int freeSlots;
    private Long location;

    public String getTitle() {
        return Title;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public int getDuration() {
        return duration;
    }

    public double getPrice() {
        return price;
    }

    public int getFreeSlots() {
        return freeSlots;
    }

    public Long getLocation() {
        return location;
    }
}
