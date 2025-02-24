package com.example.demoJasperReport.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "mascota")
public class Mascota {//mascota
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idmascota")
    private Long idmascota;

    @Column(name = "razamascota")
    private String razamascota;//raza

    @Column(name = "namemascota")
    private String namemascota;

    @Column(name = "birthdaymascota")
    private Date birthdaymascota;


}
