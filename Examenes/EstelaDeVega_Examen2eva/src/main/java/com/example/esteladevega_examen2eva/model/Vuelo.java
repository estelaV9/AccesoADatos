package com.example.esteladevega_examen2eva.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Vuelos")
public class Vuelo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idvuelo")
    private String idvuelo;

    @Column(name = "horasalida")
    private String horasalida;

    @Column(name = "origen")
    private String origen;

    @Column(name = "destino")
    private String destino;

    @Column(name = "precio")
    private float precio;

    @Column(name = "numeroescalas")
    private int numeroescalas;

    @ManyToOne
    @JoinColumn(name = "idcompañia", referencedColumnName = "idcompañia")
    @JsonBackReference // SE PONE PARA QUE NO SEA UN BUCLE INFINITO
    private Compañia compañia;

    @Override
    public String toString() {
        return "Vuelo{" +
                "horasalida='" + horasalida + '\'' +
                ", origen='" + origen + '\'' +
                ", destino='" + destino + '\'' +
                ", precio=" + precio +
                ", numeroescalas=" + numeroescalas +
                ", compañia=" + compañia.toString() +
                '}';
    }
}