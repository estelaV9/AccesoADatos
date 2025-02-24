package jsl.com.farming.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;


import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;


@Entity
@Table(name = "Agricultor")
public class Agricultor {
    /*
    OJO es buenas practica para NO TENER CIERTOS PROBLEMAS, que tanto los nombres de los atributos de la clase
    como los nombre de los campos de la tabla vayan en MINUSCULAS
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "idagricultor")
    private int idagricultor;

    @Column(name = "nombreagricultor")
    private String nombreagricultor;

    @Column(name = "edadagricultor")
    private int edadagricultor;


    @OneToMany(mappedBy = "agricultor", cascade = CascadeType.ALL)
    @JsonManagedReference //OJO ahi que ponerlo para que no sea un bucle infinito
    // ERROR --> Infinite Recursion with Jackson JSON and Hibernate JPA
    private List<Granja> granjas;


    public Agricultor() {
    }

    public Agricultor(int edad, String nombre) {
        this.edadagricultor = edad;
        this.nombreagricultor = nombre;

    }

    public List<Granja> getGranjas() {
        return granjas;
    }

    public void setGranjas(List<Granja> granjas) {
        this.granjas = granjas;
    }

    public int getIdAgricultor() {
        return idagricultor;
    }

    public void setIdAgricultor(int idAgricultor) {
        this.idagricultor = idAgricultor;
    }

    public String getNombreAgricultor() {
        return nombreagricultor;
    }

    public void setNombreAgricultor(String nombreAgricultor) {
        this.nombreagricultor = nombreAgricultor;
    }

    public int getEdadAgricultor() {
        return edadagricultor;
    }

    public void setEdadAgricultor(int edadAgricultor) {
        this.edadagricultor = edadAgricultor;
    }

    public void addAgricultor(Granja granja) {
        this.getGranjas().add(granja);
        granja.setAgricultor(this);
    }

    @Override
    public String toString() {
        return "Agricultor{" +
                "id='" + idagricultor + '\'' +
                ", age=" + edadagricultor +
                ", name='" + nombreagricultor +
                '}';
    }
}
