package com.example.TravelAgency.repositories;

import com.example.TravelAgency.entities.Holiday;
import com.example.TravelAgency.entities.Location;
import com.example.TravelAgency.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface HolidayRepository extends JpaRepository<Holiday, Long> {
    Holiday save(Holiday holiday);

    Optional<Holiday> findById(Long id);

    List<Holiday> findAll();
    List<Holiday> findByLocation(Location location);
    List<Holiday> findByDuration(int duration);
    List<Holiday> findByStartDate(LocalDate startDate);
    @Query("SELECT h FROM holiday h" +
            " WHERE h.location.id = :locationId and h.duration = :duration")
    List<Holiday> findByLocationAndDuration(@Param("locationId")Long locationId, @Param("duration") int duration);
    @Query("SELECT h FROM holiday h" +
            " WHERE h.location.id = :locationId and h.duration = :duration and h.startDate = :startDate")
    List<Holiday> findByLocationAndDurationAndStartDate(@Param("locationId")Long locationId, @Param("duration") int duration, @Param("startDate") LocalDate startDate);
    @Query("SELECT h FROM holiday h" +
            " WHERE h.duration = :duration and h.startDate = :startDate")
    List<Holiday> findByDurationAndStartDate(@Param("duration") int duration, @Param("startDate") LocalDate startDate);
    @Query("SELECT h FROM holiday h" +
            " WHERE h.location.id = :locationId and h.startDate = :startDate")
    List<Holiday> findByLocationAndStartDate(@Param("locationId")Long locationId, @Param("startDate") LocalDate startDate);



    void delete(Holiday holiday);

    boolean existsById(Long id);
}
