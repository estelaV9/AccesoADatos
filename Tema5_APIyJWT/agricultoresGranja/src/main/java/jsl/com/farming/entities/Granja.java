package jsl.com.farming.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;


import java.util.Objects;

import static jakarta.persistence.GenerationType.IDENTITY;


@Entity
@Table(name = "Granja")
public class Granja {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "idgranja")
    private int idgranja;

    @Column(name = "tamañogranja", nullable = false)
    private int tamañogranja;


    @ManyToOne
    @JoinColumn(name = "idagricultor", referencedColumnName = "idagricultor")
    @JsonBackReference //OJO ahi que ponerlo para que no sea un bucle infinito
    //ERRPR --> Infinite Recursion with Jackson JSON and Hibernate JPA
    private Agricultor agricultor;


    public Granja() {
    }

    public Granja(int tamañogranja) {

        this.tamañogranja = tamañogranja;

    }

    public int getIdGranja() {
        return idgranja;
    }

    public void setIdGranja(int idGranja) {
        this.idgranja = idGranja;
    }

    public double getTamañoGranja() {
        return tamañogranja;
    }

    public void setTamañoGranja(int tamañoGranja) {
        this.tamañogranja = tamañoGranja;
    }

    public Agricultor getAgricultor() {
        return agricultor;
    }

    public void setAgricultor(Agricultor agricultor) {
        this.agricultor = agricultor;
    }

    @Override
    public String toString() {
        return "Granja{" +
                "id='" + idgranja + '\'' +

                ", farmSize=" + tamañogranja +

                ", agricultor=" + agricultor.getIdAgricultor() +
                '}';
    }
}