package com.example.esteladevega_practicaapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
@Table(name = "hotel")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idHotel")
    private int idHotel;

    @Column(name = "nombreHotel")
    private String nombreHotel;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "categoria")
    private String categoria;

    @Column(name = "piscina")
    private boolean piscina;

    @Column(name = "localidad")
    private String localidad;

    // UN HOTEL POSEE VARIAS HABITACIONES
    // NOMBRE ATRIBUTO-OBJETO DE LA RELACION ManyToOne DE HABITACION
    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    @JsonBackReference // SE PONE PARA QUE NO SEA UN BUCLE INFINITO
    private List<Habitacion> habitacionList;
}