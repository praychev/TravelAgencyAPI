package com.example.TravelAgency.repositories;

import com.example.TravelAgency.entities.Holiday;
import com.example.TravelAgency.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HolidayRepository extends JpaRepository<Holiday, Long> {
    Holiday save(Holiday holiday);

    Optional<Holiday> findById(Long id);

    List<Holiday> findAll();

    void delete(Holiday holiday);

    boolean existsById(Long id);
}
