package com.reservation.chambre.repository;

import com.reservation.chambre.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    @Modifying
    @Query("DELETE FROM Reservation r WHERE r.numCli = :numCli")
    void deleteClientFormReservation(@Param("numCli") int numCli);
}
