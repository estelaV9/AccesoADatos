package org.esteladevega_examen1evaluacion.Model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "jugadores")
public class Jugador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idJugador")
    private int idJugador;

    @Column(name = "aliasJugador")
    private String aliasJugador;

    @Column(name = "fechaNacimiento")
    private LocalDate fechaNacimiento;

    @ManyToOne // ID_EQUIPO A LA CLAVE FORANEA DE LA TABLA EQUIPOS, EL ID DE LA CLASE EQUIPO
    @JoinColumn(name = "id_equipo", referencedColumnName = "idEquipo")
    private Equipo equipo;

    public Jugador(String aliasJugador, LocalDate fechaNacimiento) {
        this.aliasJugador = aliasJugador;
        this.fechaNacimiento = fechaNacimiento;
    }

    public Jugador(int idJugador, String aliasJugador, LocalDate fechaNacimiento, Equipo equipo) {
        this.idJugador = idJugador;
        this.aliasJugador = aliasJugador;
        this.fechaNacimiento = fechaNacimiento;
        this.equipo = equipo;
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "idJugador=" + idJugador +
                ", aliasJugador='" + aliasJugador + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", equipo=" + equipo.getIdEquipo() +
                '}';
    }

    public Jugador() {
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getAliasJugador() {
        return aliasJugador;
    }

    public void setAliasJugador(String aliasJugador) {
        this.aliasJugador = aliasJugador;
    }

    public int getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(int idJugador) {
        this.idJugador = idJugador;
    }
}
