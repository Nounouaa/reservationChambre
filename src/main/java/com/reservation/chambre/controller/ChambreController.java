package com.reservation.chambre.controller;

import com.reservation.chambre.exception.ResourceNotFoundException;
import com.reservation.chambre.model.Chambre;
import com.reservation.chambre.repository.ChambreRepository;
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
import com.reservation.chambre.service.ChambreService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/chambre")
@AllArgsConstructor
public class ChambreController {

    private final ChambreService cs;
    private final ChambreRepository cr;

    @PostMapping("/create")
    public Chambre create(@RequestBody Chambre chambre) {
        return cs.create(chambre);
    }

    @GetMapping("/read")
    public List<Chambre> read() {
        return cs.read();
    }

    @GetMapping("/getChambreById/{id}")
    public ResponseEntity<Chambre> getProduitById(@PathVariable int id) {
        Chambre chambre = cr.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Chambre not exist with id :" + id));
        return ResponseEntity.ok(chambre);
    }
    
    @PostMapping("/changeDispo/{numChambre}")
    public void changeDispo(@PathVariable int numChambre) {
        // mettre à jour le status de la chambre
        cs.changeDispo(numChambre);
        
    }

    @PutMapping("/update/{id}")
    public Chambre update(@PathVariable int id, @RequestBody Chambre chambre) {
        return cs.update(id, chambre);
    }

    @DeleteMapping("/delete/{id}")
    public int delete(@PathVariable int id) {
        return cs.delete(id);
    }
}
