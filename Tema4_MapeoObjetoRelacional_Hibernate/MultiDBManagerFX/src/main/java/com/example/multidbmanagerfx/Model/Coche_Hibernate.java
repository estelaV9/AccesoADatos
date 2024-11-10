package com.example.multidbmanagerfx.Model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "coches")
public class Coche_Hibernate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int idCoche; // ATRIBUTO PARA GUARDAR EL ID DEL COCHE

    @Column(name = "matricula")
    private String matricula; // ATRIBUTO PARA GUARDAR LA MATRICULA DEL COCHE

    @Column(name = "marca")
    private String marca; // ATRIBUTO PARA GUARDAR LA MARCA DEL COCHE

    @Column(name = "modelo")
    private String modelo; // ATRIBUTO PARA GUARDAR EL MODELO DEL COCHE

    @Column(name = "tipo")
    private String tipo; // ATRIBUTO PARA GUARDAR EL TIPO DE COCHE

    @OneToMany(mappedBy = "coche", cascade = CascadeType.ALL) // NOMBRE DE LA CLASE SIN MAYUSCULAS
    private List<Multa> listaMultas;

    public Coche_Hibernate(String matricula, String marca, String modelo, String tipo) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.tipo = tipo;
    }

    public Coche_Hibernate(int idCoche, String matricula, String marca, String modelo, String tipo) {
        this.idCoche = idCoche;
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.tipo = tipo;
    }

    public int getIdCoche() {
        return idCoche;
    }
    public void setIdCoche(int idCoche) {
        this.idCoche = idCoche;
    }

    public String getMatricula() {
        return matricula;
    }
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}