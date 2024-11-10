package com.example.multidbmanagerfx.Model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "coches")
public class Coche {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id; // ATRIBUTO PARA GUARDAR EL ID DEL COCHE

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

    public Coche(){

    } // CONSTRUCTOR VACIO

    public Coche(String matricula) {
        this.matricula = matricula;
    }

    public Coche(String marca, String modelo, String tipo) {
        this.marca = marca;
        this.modelo = modelo;
        this.tipo = tipo;
    }

    public Coche(String matricula, String marca, String modelo, String tipo) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.tipo = tipo;
    }

    public Coche(int id, String matricula, String marca, String modelo, String tipo) {
        this.id = id;
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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