package com.reservation.chambre.model;

import jakarta.persistence.*;
import java.sql.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "reservation")
@Getter
@Setter
@NoArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numRes")
    private int numRes;

    @Column(name = "numCli")
    private int numCli;

    @Column(name = "numChambre")
    private int numChambre;

    @Column(name = "dateRes")
    private Date dateRes;

    @Column(name = "dateDeb")
    private Date dateDeb;

    @Column(name = "dateFin")
    private Date dateFin;

    public Reservation(int numRes, int numCli, int numChambre, Date dateRes, Date dateDeb, Date dateFin) {
        this.numRes = numRes;
        this.numCli = numCli;
        this.numChambre = numChambre;
        this.dateRes = dateRes;
        this.dateDeb = dateDeb;
        this.dateFin = dateFin;
    }

    @Override
    public String toString() {
        return "Reservation [numRes=" + numRes + ", numCli=" + numCli + ", numChambre=" + numChambre + ", dateRes="
                + dateRes + ", dateDeb=" + dateDeb + ", dateFin=" + dateFin + ", getDateDeb()=" + getDateDeb()
                + ", getDateFin()=" + getDateFin() + ", getDateRes()=" + getDateRes() + ", getNumChambre()="
                + getNumChambre() + ", getNumCli()=" + getNumCli() + ", getNumRes()=" + getNumRes() + ", getClass()="
                + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
    }



    
}
