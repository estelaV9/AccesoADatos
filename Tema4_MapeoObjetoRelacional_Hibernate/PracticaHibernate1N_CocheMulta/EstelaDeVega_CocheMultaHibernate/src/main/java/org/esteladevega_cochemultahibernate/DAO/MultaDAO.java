package org.esteladevega_cochemultahibernate.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.esteladevega_cochemultahibernate.Model.Coche;
import org.esteladevega_cochemultahibernate.Model.Multa;
import org.esteladevega_cochemultahibernate.Utilities.HibernateUtil;
import org.esteladevega_cochemultahibernate.Utilities.StaticCode;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class MultaDAO {
    public Session session;

    public MultaDAO() {
        session = HibernateUtil.getSession();
    } // CUANDO SE CREA SE ESTABLECE LA SESION

    public ObservableList<Multa> listarMultas(String matricula) {
        List listaMultasDB = new ArrayList<>(); // LISTA PARA ALMACENAR LOS MULTAS
        try {
            session.beginTransaction(); // INICIAR NUEVA TRANSACCION
            // CONSULTA PARA OBTENER LOS DATOS DE LAS MULTAS  SEGUN LA MATRICULA DE LA BASE DE DATOS
            listaMultasDB = session.createQuery("from Multa where matricula = '" + matricula + "'").list();
            session.getTransaction().commit(); // CONFIRMAR TRANSACCION
            session.clear();
        } catch (Exception e) {
            // MENSAJE DE ERROR
            StaticCode.Alerts("ERROR", "Error al listar", "¡ERROR!",
                    "Ha ocurrido un error al listar las multas: " + e);
        }
        return FXCollections.observableList(listaMultasDB); // RETORNA LA LISTA DE MULTAS
    } // METODO PARA LISTAR TODOS LAS MULTAS DE ESE COCHE DE LA BASE DE DATOS

    public boolean insertarMulta(Multa multa) {
        try {
            session.beginTransaction(); // INICIAR NUEVA TRANSACCION
            session.save(multa); // GUARDAR LA MULTA EN LA BASE DE DATOS
            session.getTransaction().commit(); // CONFIRMAR TRANSACCION
            return true; // OPERACION EXITOSA
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback(); // HACER UN ROLLBACK
            }// SI OCURRE UN ERROR, SE REVIERTE LA TRANSACCION

            // MENSAJE DE ERROR
            StaticCode.Alerts("ERROR", "Error al insertar", "¡ERROR!",
                    "Ha ocurrido un error al insertar la multa: " + e);
            return false; // OPERACION FALLIDA
        }
    } // METODO PARA INSERTAR MULTAS

    public boolean modificarMulta(Multa multa) {
        try {
            session.beginTransaction(); // INICIAR NUEVA TRANSACCION
            Multa existente = session.get(Multa.class, multa.getIdMulta()); // SE CREA LA MULTA QUE SE VA A MODIFICAR

            //SE SETTEAN LOS DATOS
            existente.setPrecio(multa.getPrecio());
            existente.setFecha(multa.getFecha());

            session.update(existente); // MODIFICAR LA MULTA EXISTENTE
            session.getTransaction().commit(); // CONFIRMAR TRANSACCION
            session.clear(); // SE LIMPIAR LA SESSION DE LOS OBJETOS
            return true; // OPERACION EXITOSA
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback(); // HACER UN ROLLBACK
            }// SI OCURRE UN ERROR, SE REVIERTE LA TRANSACCION

            // MENSAJE DE ERROR
            StaticCode.Alerts("ERROR", "Error al modificar", "¡ERROR!",
                    "Ha ocurrido un error al modificar la multa: " + e);
            return false; // OPERACION FALLIDA
        }
    } // METODO PARA MODIFICAR DATOS DE LAS MULTAS

    public boolean eliminarMulta(Multa multa) {
        try {
            session.beginTransaction(); // INICIAR NUEVA TRANSACCION
            session.delete(multa); // ELIMINAR LA MULTA EN LA BASE DE DATOS
            session.getTransaction().commit(); // CONFIRMAR TRANSACCION
            return true; // OPERACION EXITOSA
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback(); // HACER UN ROLLBACK
            }// SI OCURRE UN ERROR, SE REVIERTE LA TRANSACCION

            // MENSAJE DE ERROR
            StaticCode.Alerts("ERROR", "Error al eliminar", "¡ERROR!",
                    "Ha ocurrido un error al eliminar la multa: " + e);
            return false; // OPERACION FALLIDA
        }
    } // METODO PARA ELIMINAR UNA MULTA
}