package com.example.multidbmanagerfx.Model;

import javafx.fxml.FXML;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "multas")
public class Multa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_multa")
    private int idMulta;

    @Column(name = "precio")
    private double precio;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "matricula")
    private String matricula;

    @ManyToOne // ID_COCHE A LA CLAVE FORANEA DE LA TABLA MULTAS, EL ID DE LA CLASE COCHE
    @JoinColumn(name = "id_coche", referencedColumnName = "id")
    private Coche coche;

    public Multa(int idMulta, double precio, LocalDate fecha, String matricula, Coche coche) {
        this.idMulta = idMulta;
        this.precio = precio;
        this.fecha = fecha;
        this.matricula = matricula;
        this.coche = coche;
    }

    public Multa(double precio, LocalDate fecha, Coche coche) {
        this.precio = precio;
        this.fecha = fecha;
        this.coche = coche;
    }

    public Multa(int idMulta, double precio, LocalDate fecha, Coche coche) {
        this.idMulta = idMulta;
        this.precio = precio;
        this.fecha = fecha;
        this.coche = coche;
    }

    public int getIdMulta() {
        return idMulta;
    }
    public void setIdMulta(int idMulta) {
        this.idMulta = idMulta;
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

    public Coche getCoche() {
        return coche;
    }
    public void setCoche(Coche coche) {
        this.coche = coche;
    }

    public String getMatricula() {
        return matricula;
    }
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}