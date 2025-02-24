package com.example.esteladevega_examenfinal.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "Pisos")
public class Piso {
    @Id
    @Column(name = "idpiso")
    private int idpiso;

    @Column(name = "tipooperacion")
    private String tipooperacion;

    @Column(name = "metros")
    private int metros;

    @Column(name = "precio")
    private int precio;

    @Column(name = "reservado")
    private String reservado;

    @ManyToOne
    @JoinColumn(name = "idzona", referencedColumnName = "idzona")
    @JsonBackReference // SE PONE PARA QUE NO SEA UN BUCLE INFINITO
    private Zona zona;
}