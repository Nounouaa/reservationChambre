package com.reservation.chambre.repository;

import com.reservation.chambre.model.Chambre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ChambreRepository extends JpaRepository<Chambre, Integer> {
    @Modifying
    @Query("UPDATE Chambre c SET statusC = 'occupée' WHERE c.numChambre = :numChambre")
    void changeStatus(@Param("numChambre") int id);
    @Modifying
    @Query("UPDATE Chambre c SET statusC = 'disponible' WHERE c.numChambre = :numChambre")
    void changeDispo(@Param("numChambre") int id);
}
