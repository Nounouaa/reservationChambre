package com.reservation.chambre.service;

import com.reservation.chambre.model.Reservation;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.reservation.chambre.repository.ReservationRepository;
import jakarta.transaction.Transactional;

@Service
@AllArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository rr;

    @Override
    public Reservation create(Reservation reservation) {
        return rr.save(reservation);
    }

    @Override
    public List<Reservation> read() {
        return rr.findAll();
    }

    @Override
    public Reservation update(int id, Reservation commande) {

        return rr.findById(id)
                .map(c -> {
                    c.setNumCli(commande.getNumCli());
                    c.setNumChambre(commande.getNumChambre());
                    c.setDateRes(commande.getDateRes());
                    c.setDateDeb(commande.getDateDeb());
                    c.setDateFin(commande.getDateFin());

                    return rr.save(c);
                }).orElseThrow(() -> new RuntimeException("Commande introuvable!"));
    }

    @Override
    public int delete(int id) {
        rr.deleteById(id);
        return 1;
    }

//    @Override
//    public int deleteCommandeByClientId(int id) {
//        cr.deleteCommandeByClientId(id);
//        return 1;
//    }
    @Transactional
    @Override
    public void deleteClientFormReservation(int numClient) {
        rr.deleteClientFormReservation(numClient);
    }

}
