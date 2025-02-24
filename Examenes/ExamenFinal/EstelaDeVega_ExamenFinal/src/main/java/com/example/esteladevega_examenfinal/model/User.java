package com.example.esteladevega_examenfinal.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "Usuarios")
public class User {
    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;
}