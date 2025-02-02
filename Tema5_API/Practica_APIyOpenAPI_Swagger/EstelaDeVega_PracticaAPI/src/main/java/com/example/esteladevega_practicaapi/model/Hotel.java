package com.example.esteladevega_practicaapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import java.util.List;

@Entity
@Data
@Table(name = "hotel")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_hotel")
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
    @JsonManagedReference
    private List<Habitacion> habitacionList;
}