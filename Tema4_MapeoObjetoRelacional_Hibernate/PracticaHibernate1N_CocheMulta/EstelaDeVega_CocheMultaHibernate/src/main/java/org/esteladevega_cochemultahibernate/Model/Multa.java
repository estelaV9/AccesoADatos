package org.esteladevega_cochemultahibernate.Model;

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

    @ManyToOne
    @JoinColumn(name = "id_coche", referencedColumnName = "id")
    private Coche coche;

    public Multa() {
    }

    public Multa(int idMulta, double precio, LocalDate fecha, String matricula) {
        this.idMulta = idMulta;
        this.precio = precio;
        this.fecha = fecha;
        this.matricula = matricula;
    }

    public Multa(Coche coche, String matricula, LocalDate fecha, double precio, int idMulta) {
        this.coche = coche;
        this.matricula = matricula;
        this.fecha = fecha;
        this.precio = precio;
        this.idMulta = idMulta;
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

    public Coche getCoche() {
        return coche;
    }

    public void setCoche(Coche coche) {
        this.coche = coche;
    }
}