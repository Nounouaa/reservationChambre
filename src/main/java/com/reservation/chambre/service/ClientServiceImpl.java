package com.reservation.chambre.service;

import com.reservation.chambre.model.Client;
import com.reservation.chambre.repository.ClientRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository cr;

    @Override
    public Client create(Client client) {
        return cr.save(client);
    }

    @Override
    public List<Client> read() {
        return cr.findAll();
    }

    @Override
    public Client update(int id, Client client) {
        return cr.findById(id)
                .map(c -> {
                    c.setNom(client.getNom());
                    c.setPrenom(client.getPrenom());
                    c.setAdresse(client.getAdresse());
                    c.setTel(client.getTel());
                    return cr.save(c);
                }).orElseThrow(() -> new RuntimeException("Client introuvable!"));

    }

    @Override
    public int delete(int id) {
        cr.deleteById(id);
        return 1;
    }

    @Override
    public List<Object[]> getReservationByClient(int num_cli) {
        return cr.getReservationByClient(num_cli);
    }

}
