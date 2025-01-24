package com.example.esteladevega_practicaapi.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "usuario")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUser")
    private int idUser;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "token")
    private String token;
}