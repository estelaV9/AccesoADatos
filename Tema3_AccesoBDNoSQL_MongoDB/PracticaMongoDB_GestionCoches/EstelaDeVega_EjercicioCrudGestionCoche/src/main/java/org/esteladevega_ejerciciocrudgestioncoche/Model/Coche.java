package org.esteladevega_ejerciciocrudgestioncoche.Model;

public class Coche {
    private String matricula; // ATRIBUTO PARA GUARDAR LA MATRICULA DEL COCHE
    private String marca; // ATRIBUTO PARA GUARDAR LA MARCA DEL COCHE
    private String modelo; // ATRIBUTO PARA GUARDAR EL MODELO DEL COCHE
    private String tipo; // ATRIBUTO PARA GUARDAR EL TIPO DE COCHE

    public Coche () {

    }

    public Coche(String matricula, String marca, String modelo) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
    }

    public Coche(String matricula, String marca, String modelo, String tipo) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.tipo = tipo;
    }

    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }
    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
}