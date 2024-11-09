package org.example;

public class Coche {
    /* Cada documento de la colección tiene un campo "_id" que se utiliza para identificar
    de forma única el documento en una colección en particular; actúa como la clave principal
    para los documentos de la colección. El campo "_id" se puede utilizar en cualquier formato 
    y el formato predeterminado es ObjectId del documento. */
    private String matricula;
    private String marca;
    private String modelo;
    private String tipo;

    public Coche() {

    }

    public Coche(String matricula, String marca, String modelo, String tipo) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.tipo = tipo;
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

    @Override
    public String toString() {
        return matricula + " " + marca + " " + modelo + " " + tipo;
    }
}