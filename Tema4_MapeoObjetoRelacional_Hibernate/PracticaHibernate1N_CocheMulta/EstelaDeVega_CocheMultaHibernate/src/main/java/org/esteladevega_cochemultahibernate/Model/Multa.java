package org.esteladevega_cochemultahibernate.Model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "multas")
public class Multa {
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

    @ManyToMany
    @JoinColumn(name = "idCoche", referencedColumnName = "id")
    private Coche coche;

    public Multa(Coche coche, String modelo, LocalDate fecha, double precio, int idCoche) {
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

    public Coche getCoche() {
        return coche;
    }

    public void setCoche(Coche coche) {
        this.coche = coche;
    }
}