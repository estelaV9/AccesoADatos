package com.example.esteladevega_practicaapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "habitacion")
public class Habitacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_habitacion")
    private int idHabitacion;

    @Column(name = "tamanio")
    private int tamanio;

    @Column(name = "precio_noche")
    private double precioNoche;

    @Column(name = "desayuno")
    private boolean desayuno;

    @Column(name = "ocupada")
    private boolean ocupada;

    // UNA HABITACION PERTENECE A UN HOTEL
    @ManyToOne
    @JoinColumn(name = "id_hotel", referencedColumnName = "id_hotel")
    @JsonBackReference // SE PONE PARA QUE NO SEA UN BUCLE INFINITO
    private Hotel hotel;
}