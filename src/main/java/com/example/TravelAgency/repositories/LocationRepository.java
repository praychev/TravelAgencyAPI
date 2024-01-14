package com.example.TravelAgency.repositories;

import com.example.TravelAgency.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, Long> {
    Location save(Location location);

    Optional<Location> findById(Long id);

    List<Location> findAll();

    void delete(Location location);

    boolean existsById(Long id);
}
