package com.example.TravelAgency.repositories;

import com.example.TravelAgency.entities.Location;
import com.example.TravelAgency.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Reservation save(Reservation reservation);

    Optional<Reservation> findById(Long id);

    List<Reservation> findAll();

    void delete(Reservation reservation);

    boolean existsById(Long id);
}
