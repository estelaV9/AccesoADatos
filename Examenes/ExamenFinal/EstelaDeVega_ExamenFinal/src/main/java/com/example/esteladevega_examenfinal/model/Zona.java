package com.example.esteladevega_examenfinal.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "Zonas")
public class Zona {
    @Id
    @Column(name = "idzona")
    private int idzona;

    @Column(name = "nombrezona")
    private String nombrezona;

    @OneToMany(mappedBy = "zona", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Piso> pisoList;
}