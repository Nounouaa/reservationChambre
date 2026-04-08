package com.reservation.chambre.service;

import com.reservation.chambre.model.Chambre;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.reservation.chambre.repository.ChambreRepository;
import jakarta.transaction.Transactional;

@Service
@AllArgsConstructor
public class ChambreServiceImpl implements ChambreService {

    private final ChambreRepository cr;

    @Override
    public Chambre create(Chambre chambre) {
        return cr.save(chambre);
    }

    @Override
    public List<Chambre> read() {
        return cr.findAll();
    }

    @Override
    public Chambre update(int id, Chambre produit) {
        return cr.findById(id)
                .map(c -> {
                    c.setTypeC(produit.getTypeC());
                    c.setNbreLit(produit.getNbreLit());
                    c.setPrix(produit.getPrix());
                    c.setStatusC(c.getStatusC());
                    return cr.save(c);
                }).orElseThrow(() -> new RuntimeException("Chambre introuvable!"));
    }

    @Override
    public int delete(int id) {
        cr.deleteById(id);
        return 1;
    }
    

    @Transactional
    @Override
    public void changeStatus(int numChambre) {
        cr.changeStatus(numChambre);
    }

    @Transactional
    @Override
    public void changeDispo(int numChambre) {
        cr.changeDispo(numChambre);
    }

}
