package org.esteladevega_examen1evaluacion.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.esteladevega_examen1evaluacion.Model.Equipo;
import org.esteladevega_examen1evaluacion.Utilities.HibernateUtil;
import org.esteladevega_examen1evaluacion.Utilities.StaticCode;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class Hibernate_EquipoDAO implements Hibernate_EquipoInterface {
    Session session;

    public Hibernate_EquipoDAO() {
        session = HibernateUtil.getSession();
    } // CUANDO SE CREA SE ESTABLECE LA SESSION

    public ObservableList<Equipo> listarEquipos() {
        List listaEquipoDB = new ArrayList<>();
        try {
            session.beginTransaction(); // INICIAR NUEVA TRANSACCION
            // CONSULTA PARA OBTENER LOS DATOS DE LOS EQUIPOS DE LA BASE DE DATOS
            listaEquipoDB = session.createQuery("from Equipo").list();
            session.getTransaction().commit(); // CONFIRMAR TRANSACCION
            session.clear(); // LIMPIAR SESSION
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
                // MENSAJE DE ERROR
                StaticCode.Alerts("ERROR", "Error al listar", "¡ERROR!",
                        "Ha ocurrido un error al listar los equipos: " + e);
            }
        }
        return FXCollections.observableArrayList(listaEquipoDB); // RETORNA LA LISTA DE EQUIPO
    } // METODO PARA LISTAR TODOS LOS EQUIPOS DE LA BASE DE DATOS

    public boolean isExistsTeam(int id) {
        List listaEquipoDB;
        try {
            session.beginTransaction(); // INICIAR NUEVA TRANSACCION
            // CONSULTA PARA OBTENER LOS DATOS DE LOS EQUIPOS DE LA BASE DE DATOS
            listaEquipoDB = session.createQuery("from Equipo where idEquipo = '" + id + "'").list();
            session.getTransaction().commit(); // CONFIRMAR TRANSACCION
            session.clear(); // LIMPIAR SESSION

            if (!listaEquipoDB.isEmpty()) {
                return true;
            } // SI LA LISTA NO ES NULA, RETORNA TRUE
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
                // MENSAJE DE ERROR
                StaticCode.Alerts("ERROR", "Error al listar", "¡ERROR!",
                        "Ha ocurrido un error al listar los equipos: " + e);
            }
            return false;
        }
        return false;
    } // METODO PARA SABER SI HAY UN EQUIPO CON ESE ID

    public boolean modificarEquipo(Equipo equipo, boolean sancionado) {
        try {
            session.beginTransaction(); // INICIAR NUEVA TRANSACCION
            Equipo existente = session.get(Equipo.class, equipo.getIdEquipo()); // SE CREA EL EQUIPO QUE SE VA A MODIFICAR

            //SE SETTEAN LOS DATOS
            existente.setSancionado(sancionado);

            session.update(existente); // MODIFICAR EL EQUIPO EXISTENTE
            session.getTransaction().commit(); // CONFIRMAR TRANSACCION
            session.clear(); // SE LIMPIAR LA SESSION DE LOS OBJETOS
            return true; // OPERACION EXITOSA
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback(); // HACER UN ROLLBACK
            }// SI OCURRE UN ERROR, SE REVIERTE LA TRANSACCION

            // MENSAJE DE ERROR
            StaticCode.Alerts("ERROR", "Error al modificar", "¡ERROR!",
                    "Ha ocurrido un error al modificar el equipo: " + e);
            return false; // OPERACION FALLIDA
        }
    } // METODO PARA MODIFICAR DATOS DE LOS EQUIPOS

    public boolean eliminarEquipo(Equipo equipo) {
        try {
            session.beginTransaction(); // INICIAR NUEVA TRANSACCION
            session.delete(equipo); // ELIMINAR EL EQUIPO EN LA BASE DE DATOS
            session.getTransaction().commit(); // CONFIRMAR TRANSACCION
            session.clear(); // LIMPIAR SESSION
            return true; // OPERACION EXITOSA
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback(); // HACER UN ROLLBACK
            }// SI OCURRE UN ERROR, SE REVIERTE LA TRANSACCION

            // MENSAJE DE ERROR
            StaticCode.Alerts("ERROR", "Error al eliminar", "¡ERROR!",
                    "Ha ocurrido un error al eliminar el equipo: " + e);
            return false; // OPERACION FALLIDA
        }
    } // METODO PARA ELIMINAR UN EQUIPO
}