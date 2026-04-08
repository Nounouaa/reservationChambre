package com.reservation.chambre.service;

import com.reservation.chambre.model.Reservation;
import java.util.List;

public interface ReservationService {

    Reservation create(Reservation res);

    List<Reservation> read();

    Reservation update(int id, Reservation res);

    int delete(int id);
    
    void deleteClientFormReservation(int id);
    
// int deleteCommandeByClientId(int id);
}
