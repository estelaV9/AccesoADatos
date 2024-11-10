package com.example.multidbmanagerfx.Model;

import com.example.multidbmanagerfx.Model.Coche_Hibernate;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "multas")
public class Multa_Hibernate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_multa")
    private int idCoche;

    @Column(name = "precio")
    private double precio;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "matricula")
    private String modelo;

    @ManyToOne
    @JoinColumn(name = "id_coche", referencedColumnName = "id")
    private Coche_Hibernate coche;

    public Multa_Hibernate(Coche_Hibernate coche, String modelo, LocalDate fecha, double precio, int idCoche) {
        this.coche = coche;
        this.modelo = modelo;
        this.fecha = fecha;
        this.precio = precio;
        this.idCoche = idCoche;
    }

    public int getIdCoche() {
        return idCoche;
    }
    public void setIdCoche(int idCoche) {
        this.idCoche = idCoche;
    }

    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public LocalDate getFecha() {
        return fecha;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Coche_Hibernate getCoche() {
        return coche;
    }
    public void setCoche(Coche_Hibernate coche) {
        this.coche = coche;
    }
}