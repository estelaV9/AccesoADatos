package com.example.esteladevega_examen2eva.model;

import javax.persistence.*;
import lombok.Data;

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
