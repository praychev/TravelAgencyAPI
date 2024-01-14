package com.example.TravelAgency.dtos;

import java.time.LocalDate;

public class UpdateHolidayDTO {
    private Long id;
    private String title;
    private LocalDate createdDate;
    private int duration;
    private double price;
    private int freeSlots;
    private Long location;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
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