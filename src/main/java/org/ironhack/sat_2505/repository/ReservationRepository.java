package org.ironhack.sat_2505.repository;


import org.ironhack.sat_2505.enums.ReservationStatus;
import org.ironhack.sat_2505.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    // JPA
    List<Reservation> findByStatus(ReservationStatus status);
}
