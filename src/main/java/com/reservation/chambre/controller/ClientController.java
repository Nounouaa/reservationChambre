package com.reservation.chambre.controller;

import com.reservation.chambre.exception.ResourceNotFoundException;
import com.reservation.chambre.model.Client;
import com.reservation.chambre.repository.ClientRepository;
import com.reservation.chambre.service.ClientService;
import com.reservation.chambre.service.ReservationService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "https://projetstages.netlify.app")

@RestController
@RequestMapping("/client")
@AllArgsConstructor
public class ClientController {

    private final ClientService cs;
    private final ReservationService rs;

    private final ClientRepository cr;

    @PostMapping("/create")
    public Client create(@RequestBody Client client) {
        return cs.create(client);
    }

    @GetMapping("/read")
    public List<Client> read() {
        return cs.read();
    }

    @GetMapping("/getClientById/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable int id) {
        Client client = cr.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client not exist with id :" + id));
        return ResponseEntity.ok(client);
    }

    @GetMapping("/getReservationByClient/{id}")
    public List<Object[]> getCommandeById(@PathVariable int id) {
        
        return cs.getReservationByClient(id);
    }

    @PutMapping("/update/{id}")
    public Client update(@PathVariable int id, @RequestBody Client client) {
        return cs.update(id, client);
    }

    @DeleteMapping("/delete/{id}")
    public int delete(@PathVariable int id) {
        rs.deleteClientFormReservation(id);
        return cs.delete(id);
    }
}
