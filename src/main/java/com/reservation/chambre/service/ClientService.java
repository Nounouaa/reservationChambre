package com.reservation.chambre.service;

import java.util.List;
import com.reservation.chambre.model.Client;

public interface ClientService {

    Client create(Client client);

    List<Client> read();

    Client update(int id, Client client);

    int delete(int id);
    
    List<Object[]> getReservationByClient(int num_cli);

}
