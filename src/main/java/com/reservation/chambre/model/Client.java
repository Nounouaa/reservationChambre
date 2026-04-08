package com.reservation.chambre.model;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "client")
@Getter
@Setter
@NoArgsConstructor
public class Client{
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numCli")
    private int numCli;
    @Column(name = "nom", length = 100)
    private String nom;
    @Column(name = "prenom", length = 155)
    private String prenom;
    @Column(name = "adresse", length = 55)
    private String adresse;
    @Column(name = "tel", length = 15)
    private String tel;
    public Client(int numCli, String nom, String prenom, String adresse, String tel) {
        this.numCli = numCli;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.tel = tel;
    }
    @Override
    public String toString() {
        return "Client [numCli=" + numCli + ", nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse + ", tel="
                + tel + ", getAdresse()=" + getAdresse() + ", getNom()=" + getNom() + ", getNumCli()=" + getNumCli()
                + ", getPrenom()=" + getPrenom() + ", getTel()=" + getTel() + ", getClass()=" + getClass()
                + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
    }
  

    
}
