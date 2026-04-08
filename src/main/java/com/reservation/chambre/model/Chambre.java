package com.reservation.chambre.model;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "chambre")
@Getter
@Setter
@NoArgsConstructor
public class Chambre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numChambre")
    private int numChambre;

    @Column(name = "typeC", length = 55)
    private String typeC;
    
     @Column(name = "nbreLit")
    private int nbreLit;

    @Column(name = "prix")
    private int prix;
    
    @Column(name = "statusC")
    private String statusC;

    public Chambre(int numChambre, String typeC, int nbreLit, int prix, String statusC) {
        this.numChambre = numChambre;
        this.typeC = typeC;
        this.nbreLit = nbreLit;
        this.prix = prix;
        this.statusC = statusC;
    }

    @Override
    public String toString() {
        return "Chambre [numChambre=" + numChambre + ", typeC=" + typeC + ", nbreLit=" + nbreLit + ", prix=" + prix
                + ", statusC=" + statusC + ", getNbreLit()=" + getNbreLit() + ", getNumChambre()=" + getNumChambre()
                + ", getPrix()=" + getPrix() + ", getStatusC()=" + getStatusC() + ", getTypeC()=" + getTypeC()
                + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
                + "]";
    }


    
    
}
