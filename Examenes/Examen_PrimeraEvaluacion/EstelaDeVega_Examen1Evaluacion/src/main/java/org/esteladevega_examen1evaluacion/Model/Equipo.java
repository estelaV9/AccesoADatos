package org.esteladevega_examen1evaluacion.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "equipos")
public class Equipo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEquipo")
    private int idEquipo;

    @Column(name = "nombreEquipo")
    private String nombreEquipo;

    @Column(name = "patrocinador")
    private String patrocinado;

    @Column(name = "categoria")
    private String categoria;

    @Column(name = "sancionado")
    private boolean sancionado;

    @OneToMany(mappedBy = "equipo", cascade = CascadeType.ALL) // NOMBRE DE LA CLASE SIN MAYUSCULAS
    private List<Jugador> listaJugadores;

    public Equipo() {
    }



    public Equipo(int idEquipo, String nombreEquipo, String patrocinado, String categoria, boolean sancionado) {
        this.idEquipo = idEquipo;
        this.nombreEquipo = nombreEquipo;
        this.patrocinado = patrocinado;
        this.categoria = categoria;
        this.sancionado = sancionado;
    }

    public Equipo(int idEquipo, String nombreEquipo, String patrocinado, String categoria, boolean sancionado, List<Jugador> listaJugadores) {
        this.idEquipo = idEquipo;
        this.nombreEquipo = nombreEquipo;
        this.patrocinado = patrocinado;
        this.categoria = categoria;
        this.sancionado = sancionado;
        this.listaJugadores = listaJugadores;
    }


    public int getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public String getPatrocinado() {
        return patrocinado;
    }

    public void setPatrocinado(String patrocinado) {
        this.patrocinado = patrocinado;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public boolean isSancionado() {
        return sancionado;
    }

    public void setSancionado(boolean sancionado) {
        this.sancionado = sancionado;
    }


    public List<Jugador> getListaJugadores() {
        return listaJugadores;
    }

    public void setListaJugadores(List<Jugador> listaJugadores) {
        this.listaJugadores = listaJugadores;
    }
}
