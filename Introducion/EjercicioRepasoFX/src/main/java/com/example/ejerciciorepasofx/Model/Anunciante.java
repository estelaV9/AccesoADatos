package com.example.ejerciciorepasofx.Model;

public class Anunciante extends Cliente{
    private int numAnunciosAn;

    public Anunciante(){

    }

    public Anunciante(String mailCl, String contraseñaCl, double descuentoCl, int numAnunciosAn) {
        super(mailCl, contraseñaCl, descuentoCl);
        this.numAnunciosAn = numAnunciosAn;
    }

    public int getNumAnunciosAn() {
        return numAnunciosAn;
    }

    public void setNumAnunciosAn(int numAnunciosAn) {
        this.numAnunciosAn = numAnunciosAn;
    }
}
