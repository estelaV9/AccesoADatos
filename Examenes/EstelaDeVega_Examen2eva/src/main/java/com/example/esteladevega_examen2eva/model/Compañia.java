package com.example.esteladevega_examen2eva.model;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
@Table(name = "Compañias")
public class Compañia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcompañia")
    private int idcompañia;

    @Column(name = "nombrecompañia")
    private String nombrecompañia;

    public Compañia(String nombrecompañia) {
        this.nombrecompañia = nombrecompañia;
    }
    public Compañia() {
    }

    public int getIdcompañia() {
        return idcompañia;
    }
    public void setIdcompañia(int idcompañia) {
        this.idcompañia = idcompañia;
    }
    public String getNombrecompañia() {
        return nombrecompañia;
    }
    public void setNombrecompañia(String nombrecompañia) {
        this.nombrecompañia = nombrecompañia;
    }

    @Override
    public String toString() {
        return "Compañia{" +
                "idcompañia=" + idcompañia +
                ", nombrecompañia='" + nombrecompañia + '\'' +
                '}';
    }
}