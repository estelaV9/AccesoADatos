package org.esteladevega_examen1evaluacion.DAO;

import javafx.collections.ObservableList;
import org.esteladevega_examen1evaluacion.Model.Equipo;
import org.esteladevega_examen1evaluacion.Model.Jugador;

import java.time.LocalDate;

public interface Hibernate_JugadorInterface {
    ObservableList<Jugador> listarJugadores(int idEquipo);
    ObservableList<Jugador> listarJugadoresCumpleaños(LocalDate localDate);
    boolean modificarJugador(Jugador jugador, Equipo equipo);
}
