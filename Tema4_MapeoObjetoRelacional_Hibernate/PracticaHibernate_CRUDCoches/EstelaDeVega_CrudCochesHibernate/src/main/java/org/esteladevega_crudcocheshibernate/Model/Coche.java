package org.esteladevega_crudcocheshibernate.Model;

import javax.persistence.*;

@Entity
@Table(name = "coche")
public class Coche {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cocheID")
    private int cocheID; // ATRIBUTO PARA GUARDAR EL ID DEL COCHE

    @Column(name = "matricula")
    private String matriculaCoche; // ATRIBUTO PARA GUARDAR LA MATRICULA DEL COCHE

    @Column(name = "marca")
    private String marcaCoche; // ATRIBUTO PARA GUARDAR LA MARCA DEL COCHE

    @Column(name = "modelo")
    private String modeloCoche; // ATRIBUTO PARA GUARDAR EL MODELO DEL COCHE

    @Column(name = "tipo")
    private String tipoCoche; // ATRIBUTO PARA GUARDAR EL TIPO DE COCHE


    public Coche() {
    }

    public Coche(int cocheID, String matriculaCoche, String marcaCoche, String modeloCoche, String tipoCoche) {
        this.cocheID = cocheID;
        this.matriculaCoche = matriculaCoche;
        this.marcaCoche = marcaCoche;
        this.modeloCoche = modeloCoche;
        this.tipoCoche = tipoCoche;
    }

    public Coche(String matriculaCoche, String marcaCoche, String modeloCoche, String tipoCoche) {
        this.matriculaCoche = matriculaCoche;
        this.marcaCoche = marcaCoche;
        this.modeloCoche = modeloCoche;
        this.tipoCoche = tipoCoche;
    }


    public int getCocheID() {
        return cocheID;
    }

    public void setCocheID(int cocheID) {
        this.cocheID = cocheID;
    }

    public String getMatriculaCoche() {
        return matriculaCoche;
    }

    public void setMatriculaCoche(String matriculaCoche) {
        this.matriculaCoche = matriculaCoche;
    }

    public String getMarcaCoche() {
        return marcaCoche;
    }

    public void setMarcaCoche(String marcaCoche) {
        this.marcaCoche = marcaCoche;
    }

    public String getModeloCoche() {
        return modeloCoche;
    }

    public void setModeloCoche(String modeloCoche) {
        this.modeloCoche = modeloCoche;
    }

    public String getTipoCoche() {
        return tipoCoche;
    }

    public void setTipoCoche(String tipoCoche) {
        this.tipoCoche = tipoCoche;
    }
}