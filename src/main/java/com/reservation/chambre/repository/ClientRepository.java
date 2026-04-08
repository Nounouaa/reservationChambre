package com.reservation.chambre.repository;

import com.reservation.chambre.model.Client;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    @Query("select client.numCli, client.nom, client.prenom, reservation.numChambre, reservation.dateDeb from Reservation reservation inner join Client client on reservation.numCli = client.numCli where client.numCli = :num_cli")
    public List<Object[]> getReservationByClient(@Param("num_cli") int num_cli);
}
