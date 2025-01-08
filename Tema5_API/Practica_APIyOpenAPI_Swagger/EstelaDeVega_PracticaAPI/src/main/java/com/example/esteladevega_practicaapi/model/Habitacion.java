package com.example.esteladevega_practicaapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "habitacion")
public class Habitacion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idHabitacion")
    private int idHabitacion;

    @Column(name = "tamanio")
    private int tamanio;

    @Column(name = "precioNoche")
    private double precioNoche;

    @Column(name = "desayuno")
    private boolean desayuno;

    @Column(name = "ocupada")
    private boolean ocupada;

    @Column(name = "localidad")
    private String localidad;

    // UNA HABITACION PERTENECE A UN HOTEL
    @ManyToOne
    @JoinColumn(name = "idHotel", referencedColumnName = "idHotel")
    @JsonBackReference // SE PONE PARA QUE NO SEA UN BUCLE INFINITO
    private Hotel hotel;
}