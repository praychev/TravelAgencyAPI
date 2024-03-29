package com.example.TravelAgency.controllers;

import com.example.TravelAgency.dtos.*;
import com.example.TravelAgency.entities.Holiday;
import com.example.TravelAgency.entities.Location;
import com.example.TravelAgency.repositories.HolidayRepository;
import com.example.TravelAgency.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class HolidayController {
    @Autowired
    private HolidayRepository holidayRepository;
    @Autowired
    private LocationRepository locationRepository;
    @PostMapping("/holidays")
    public ResponseEntity <ResponseHolidayDTO> createHoliday(@RequestBody CreateHolidayDTO holidayDTO){
        Holiday holiday = new Holiday();
        holiday.setCreatedDate(holidayDTO.getStartDate());
        holiday.setDuration(holidayDTO.getDuration());
        holiday.setFreeSlots(holidayDTO.getFreeSlots());
        holiday.setPrice(holidayDTO.getPrice());
        holiday.setTitle(holidayDTO.getTitle());
        if(!locationRepository.existsById(holidayDTO.getLocation())){
            return ResponseEntity.notFound().build();
        }
            Location location = locationRepository.findById(holidayDTO.getLocation()).get();
        holiday.setLocation(location);
        Holiday created = holidayRepository.save(holiday);
        return ResponseEntity.ok(created.toResponseDTO());
    }

    @DeleteMapping("/holidays/{holidayId}")
    public boolean deleteHoliday(@PathVariable Long holidayId){
        Holiday holiday = holidayRepository.getById(holidayId);
        if(holiday == null){
            return true;
        }
        holidayRepository.delete(holiday);
        return !holidayRepository.existsById(holidayId);
    }

    @GetMapping("/holidays")
    public ResponseEntity<List<ResponseHolidayDTO>> getHolidays(@RequestParam(required = false) Long location, @RequestParam(required = false) Integer duration, @RequestParam(required = false)LocalDate startDate){

        List<Holiday> holidays = new ArrayList<>();
        if(location != null && duration != null && startDate != null){
            holidays = holidayRepository.findByLocationAndDurationAndStartDate(location, duration.intValue() ,startDate);
        }else if(location != null && duration != null ){
            holidays = holidayRepository.findByLocationAndDuration(location, duration.intValue());
        } else if (location != null && startDate != null) {
            holidays = holidayRepository.findByLocationAndStartDate(location, startDate);
        } else if (duration != null && startDate != null) {
            holidays = holidayRepository.findByDurationAndStartDate(duration.intValue(), startDate);
        } else if (location != null) {
            holidays = holidayRepository.findByLocation(locationRepository.findById(location).get());
        }else if(duration != null ){
            holidays = holidayRepository.findByDuration(duration.intValue());
        } else if (startDate != null) {
            holidays = holidayRepository.findByStartDate(startDate);
        }else{
            holidays = holidayRepository.findAll();
        }
        List<ResponseHolidayDTO> holidaysResponse = new ArrayList<>();
        for (Holiday holiday: holidays){
            holidaysResponse.add(holiday.toResponseDTO());
        }
        return ResponseEntity.ok(holidaysResponse);
    }

    @GetMapping("/holidays/{holidayId}")
    public ResponseEntity <ResponseHolidayDTO> getHoliday(@PathVariable Long holidayId){
        Optional<Holiday> getHoliday = holidayRepository.findById(holidayId);
        if(!getHoliday.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(getHoliday.get().toResponseDTO());
    }

    @PutMapping("/holidays")
    public ResponseEntity<ResponseHolidayDTO> putHolidays(@RequestBody UpdateHolidayDTO holidayDTO){
        Optional<Location> location = locationRepository.findById(holidayDTO.getLocation());
        if(location.isPresent()){
            Optional<Holiday> holiday = holidayRepository.findById(holidayDTO.getId());
            Holiday holidayToUpdate = new Holiday();
            if (holiday.isPresent()){
                if(holidayDTO.getId() != null){
                    holidayToUpdate.setId(holidayDTO.getId());
                }else {
                    return ResponseEntity.notFound().build();
                }
                if(holidayDTO.getStartDate() != null){
                    holidayToUpdate.setCreatedDate(holidayDTO.getStartDate());
                }
                if(holidayDTO.getDuration()!=0) {
                    holidayToUpdate.setDuration(holidayDTO.getDuration());
                }
                if(holidayDTO.getFreeSlots()!=0) {
                    holidayToUpdate.setFreeSlots(holidayDTO.getFreeSlots());
                }
                if(holidayDTO.getPrice()!=0) {
                    holidayToUpdate.setPrice(holidayDTO.getPrice());
                }
                if(holidayDTO.getTitle() != null) {
                    holidayToUpdate.setTitle(holidayDTO.getTitle());
                }
                holidayToUpdate.setLocation(location.get());
                Holiday updated = holidayRepository.save(holidayToUpdate);
                return ResponseEntity.ok(updated.toResponseDTO());
            }
        }
        return ResponseEntity.notFound().build();
    }
}