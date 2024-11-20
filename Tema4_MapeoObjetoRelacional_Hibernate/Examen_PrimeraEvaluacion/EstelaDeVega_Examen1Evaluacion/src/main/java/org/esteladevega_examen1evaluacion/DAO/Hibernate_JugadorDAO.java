package org.esteladevega_examen1evaluacion.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.esteladevega_examen1evaluacion.Model.Equipo;
import org.esteladevega_examen1evaluacion.Model.Jugador;
import org.esteladevega_examen1evaluacion.Utilities.HibernateUtil;
import org.esteladevega_examen1evaluacion.Utilities.StaticCode;
import org.hibernate.Session;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Hibernate_JugadorDAO implements Hibernate_JugadorInterface {
    Session session;

    public Hibernate_JugadorDAO() {
        session = HibernateUtil.getSession();
    } // CUANDO SE CREA SE ESTABLECE LA SESSION


    public ObservableList<Jugador> listarJugadores(int idEquipo) {
        List<Jugador> jugadores = new ArrayList<>();
        try {
            jugadores = session.createQuery(" from Jugador where id_equipo = '" + idEquipo + "'", Jugador.class).getResultList();
        } catch (Exception e) {
            session.clear();
            StaticCode.Alerts("ERROR", "Error de conexión", "¡ERROR!",
                    "Error al conectar a la base de datos: " + e.getMessage());
        }
        return FXCollections.observableArrayList(jugadores);
    } // METODO PARA LISTAR TODOS LOS EQUIPOS DE LA BASE DE DATOS


    public ObservableList<Jugador> listarJugadoresCumpleaños(LocalDate localDate) {
        List<Jugador> jugadores = new ArrayList<>(); // LISTA JUGADORES CON ESE CUMPLEAÑOS
        try {
            // SE SUBSTRAE EL DIA Y MES DEL CUMPLEAÑOS Y EL DIA Y MES DEL DIA Y MES ACTUAL
            jugadores = session.createQuery("from Jugador WHERE substr(fechaNacimiento, 6) = substr('" + localDate + "', 6)", Jugador.class).getResultList();
        } catch (Exception e) {
            session.clear();
            StaticCode.Alerts("ERROR", "Error de conexión", "¡ERROR!",
                    "Error al conectar a la base de datos: " + e.getMessage());
        }
        return FXCollections.observableArrayList(jugadores);
    } // METODO PARA LISTAR TODOS LOS EQUIPOS DE LA BASE DE DATOS


    public boolean modificarJugador(Jugador jugador, Equipo equipo) {
        try {
            session.beginTransaction(); // INICIAR NUEVA TRANSACCION
            Jugador existente = session.get(Jugador.class, jugador.getIdJugador()); // SE CREA EL JUGADOR QUE SE VA A MODIFICAR

            //SE SETTEAN LOS DATOS
            existente.setEquipo(equipo);

            session.update(existente); // MODIFICAR EL JUGADOR EXISTENTE
            session.getTransaction().commit(); // CONFIRMAR TRANSACCION
            session.clear(); // SE LIMPIAR LA SESSION DE LOS OBJETOS
            return true; // OPERACION EXITOSA
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback(); // HACER UN ROLLBACK
            }// SI OCURRE UN ERROR, SE REVIERTE LA TRANSACCION

            // MENSAJE DE ERROR
            StaticCode.Alerts("ERROR", "Error al modificar", "¡ERROR!",
                    "Ha ocurrido un error al modificar el jugador: " + e);
            return false; // OPERACION FALLIDA
        }
    } // METODO PARA CAMBIAR DE EQUIPO AL JUGADOR
}
