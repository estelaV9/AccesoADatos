package org.esteladevega_examen1evaluacion.Model;

public class Categoria {
    private String nombre;
    private String abreviatura;
    private String apellidos;
    private int edad;
    private boolean carnet;

    public Categoria(){

    }

    public Categoria(String nombre) {
        this.nombre = nombre;
    }

    public Categoria(String nombre, String abreviatura) {
        this.nombre = nombre;
        this.abreviatura = abreviatura;
    }

    public Categoria(String nombre, String abreviatura, String apellidos, int edad, boolean carnet) {
        this.nombre = nombre;
        this.abreviatura = abreviatura;
        this.apellidos = apellidos;
        this.edad = edad;
        this.carnet = carnet;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public boolean isCarnet() {
        return carnet;
    }

    public void setCarnet(boolean carnet) {
        this.carnet = carnet;
    }
}
