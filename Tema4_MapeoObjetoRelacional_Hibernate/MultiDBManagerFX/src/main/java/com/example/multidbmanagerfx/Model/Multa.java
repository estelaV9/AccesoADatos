package com.example.multidbmanagerfx.Model;

import java.time.LocalDate;

public class Multa {
    private int idMulta;
    private double precio;
    private LocalDate fecha;
    private String matricula;

    public Multa(double precio, LocalDate fecha, String matricula) {
        this.precio = precio;
        this.fecha = fecha;
        this.matricula = matricula;
    }

    public Multa(int idMulta, double precio, LocalDate fecha, String matricula) {
        this.idMulta = idMulta;
        this.precio = precio;
        this.fecha = fecha;
        this.matricula = matricula;
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

    public String getMatricula() {
        return matricula;
    }
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}