package com.reservation.chambre.service;

import com.reservation.chambre.model.Chambre;
import java.util.List;

public interface ChambreService {
    Chambre create(Chambre chambre);

    List<Chambre> read();

    Chambre update(int id, Chambre chambre);

    int delete(int id);
    
    void changeStatus(int numChambre);  
    
    void changeDispo(int numChambre);

}
