package org.example;

public class Persona {
    private String Nombre;
    private String Apellidos;
    private int edad;
    private boolean carnet;

    public Persona() {
    }

    public String getNombre() {
        return Nombre;
    }
    public void setNombre(String nombre) {
        Nombre = nombre;
    }
    public String getApellidos() {
        return Apellidos;
    }
    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
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