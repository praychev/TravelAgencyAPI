package com.example.TravelAgency.dtos;

import java.time.LocalDate;

public class ResponseHolidayDTO {
    private Long id;
    private String title;
    private LocalDate createdDate;
    private int duration;
    private double price;
    private int freeSlots;
    private ResponseLocationDTO location;

    public ResponseHolidayDTO() {
    }

    public ResponseHolidayDTO(Long id, String title, LocalDate createdDate, int duration, double price, int freeSlots, ResponseLocationDTO location) {
        this.id = id;
        this.title = title;
        this.createdDate = createdDate;
        this.duration = duration;
        this.price = price;
        this.freeSlots = freeSlots;
        this.location = location;
    }

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

    public ResponseLocationDTO getLocation() {
        return location;
    }
}
