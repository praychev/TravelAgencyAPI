package com.example.TravelAgency.controllers;

import com.example.TravelAgency.dtos.*;
import com.example.TravelAgency.entities.Holiday;
import com.example.TravelAgency.entities.Location;
import com.example.TravelAgency.entities.Reservation;
import com.example.TravelAgency.repositories.HolidayRepository;
import com.example.TravelAgency.repositories.LocationRepository;
import com.example.TravelAgency.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ReservationController {
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private HolidayRepository holidayRepository;
    @PostMapping("/reservations")
    public ResponseEntity<ResponseReservationDTO> createReservation(@RequestBody CreateReservationDTO reservationDTO){
        Reservation reservation = new Reservation();
        reservation.setContactName(reservationDTO.getContactName());
        reservation.setPhoneNumber(reservationDTO.getPhoneNumber());
        if(!holidayRepository.existsById(reservationDTO.getHoliday())){
            return ResponseEntity.notFound().build();
        }
        Holiday holiday = holidayRepository.findById(reservationDTO.getHoliday()).get();
        reservation.setHoliday(holiday);
        Reservation created = reservationRepository.save(reservation);
        return ResponseEntity.ok(created.toResponseDTO());
    }

    @DeleteMapping("/reservations/{reservationId}")
    public boolean deleteReservation(@PathVariable Long reservationId){
        Reservation reservation = reservationRepository.getById(reservationId);
        if(reservation == null){
            return true;
        }
        reservationRepository.delete(reservation);
        return !reservationRepository.existsById(reservationId);
    }

    @GetMapping("/reservations")
    public ResponseEntity<List<ResponseReservationDTO>> getReservations(){
        List<Reservation> reservations = reservationRepository.findAll();
        List<ResponseReservationDTO> reservationsResponse = new ArrayList<>();
        for (Reservation reservation: reservations){
            reservationsResponse.add(reservation.toResponseDTO());
        }
        return ResponseEntity.ok(reservationsResponse);
    }

    @GetMapping("/reservations/{reservationId}")
    public ResponseEntity <ResponseReservationDTO> getReservation(@PathVariable Long reservationId){
        Optional<Reservation> foundReservation = reservationRepository.findById(reservationId);
        if(!foundReservation.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(foundReservation.get().toResponseDTO());
    }

    @PutMapping("/reservations")
    public ResponseEntity<ResponseReservationDTO> putReservations(@RequestBody UpdateReservationDTO reservationDTO){
        Optional<Holiday> holiday = holidayRepository.findById(reservationDTO.getHoliday());
        if(holiday.isPresent()){
            Optional<Reservation> reservation = reservationRepository.findById(reservationDTO.getId());
            Reservation reservationToUpdate = new Reservation();
            if (reservation.isPresent()){
                if(reservationDTO.getId() != null){
                    reservationToUpdate.setId(reservationDTO.getId());
                }else {
                    return ResponseEntity.notFound().build();
                }
                if(reservationDTO.getContactName() != null){
                    reservationToUpdate.setContactName(reservationDTO.getContactName());
                }
                if(reservationDTO.getPhoneNumber() != null) {
                    reservationToUpdate.setPhoneNumber(reservationDTO.getPhoneNumber());
                }
                reservationToUpdate.setHoliday(holiday.get());
                Reservation updated = reservationRepository.save(reservationToUpdate);
                return ResponseEntity.ok(updated.toResponseDTO());
            }
        }
        return ResponseEntity.notFound().build();
    }
}