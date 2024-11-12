package org.esteladevega_cochemultahibernate.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.esteladevega_cochemultahibernate.Model.Coche;
import org.esteladevega_cochemultahibernate.Utilities.HibernateUtil;
import org.esteladevega_cochemultahibernate.Utilities.StaticCode;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class CocheDAO implements InterfaceCoche {
    public Session session;

    public CocheDAO() {
        session = HibernateUtil.getSession();
    } // CUANDO SE CREA SE ESTABLECE LA SESSION

    public boolean insertarCoche(Session session, Coche coche) {
        try {
            session.beginTransaction(); // INICIAR NUEVA TRANSACCION
            session.save(coche); // GUARDAR EL COCHE EN LA BASE DE DATOS
            session.getTransaction().commit(); // CONFIRMAR TRANSACCION
            return true; // OPERACION EXITOSA
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback(); // HACER UN ROLLBACK
            }// SI OCURRE UN ERROR, SE REVIERTE LA TRANSACCION

            // MENSAJE DE ERROR
            StaticCode.Alerts("ERROR", "Error al insertar", "¡ERROR!",
                    "Ha ocurrido un error al insertar el coche: " + e);
            return false; // OPERACION FALLIDA
        }
    } // METODO PARA INSERTAR COCHES

    public boolean modificarCoche(Session session, Coche coche) {
        try {
            session.beginTransaction(); // INICIAR NUEVA TRANSACCION
            Coche existente = session.get(Coche.class, coche.getIdCoche()); // SE CREA EL COCHE QUE SE VA A MODIFICAR

            //SE SETTEAN LOS DATOS
            existente.setMarca(coche.getMarca());
            existente.setModelo(coche.getModelo());
            existente.setTipo(coche.getTipo());

            session.update(existente); // MODIFICAR EL COCHE EXISTENTE
            session.getTransaction().commit(); // CONFIRMAR TRANSACCION
            session.clear(); // SE LIMPIAR LA SESSION DE LOS OBJETOS
            return true; // OPERACION EXITOSA
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback(); // HACER UN ROLLBACK
            }// SI OCURRE UN ERROR, SE REVIERTE LA TRANSACCION

            // MENSAJE DE ERROR
            StaticCode.Alerts("ERROR", "Error al modificar", "¡ERROR!",
                    "Ha ocurrido un error al modificar el coche: " + e);
            return false; // OPERACION FALLIDA
        }
    } // METODO PARA MODIFICAR DATOS DE LOS COCHES

    public boolean eliminarCoche(Session session, Coche coche) {
        try {
            session.beginTransaction(); // INICIAR NUEVA TRANSACCION
            session.delete(coche); // ELIMINAR EL COCHE EN LA BASE DE DATOS
            session.getTransaction().commit(); // CONFIRMAR TRANSACCION
            return true; // OPERACION EXITOSA
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback(); // HACER UN ROLLBACK
            }// SI OCURRE UN ERROR, SE REVIERTE LA TRANSACCION

            // MENSAJE DE ERROR
            StaticCode.Alerts("ERROR", "Error al eliminar", "¡ERROR!",
                    "Ha ocurrido un error al eliminar el coche: " + e);
            return false; // OPERACION FALLIDA
        }
    } // METODO PARA ELIMINAR UN COCHE

    public ObservableList<Coche> listarCoches(Session session) {
        ObservableList<Coche> observableList = null; // LISTA PARA ALMACENAR LOS COCHES
        try {
            // CONSULTA PARA OBTENER LOS DATOS DE LOS COCHES DE LA BASE DE DATOS
            List listaCochesDB = session.createQuery("from Coche").list();
            observableList = FXCollections.observableArrayList(listaCochesDB); // SE AÑADE EL ARRAYLIST AL OBSERVABLELIST
        } catch (Exception e) {
            // MENSAJE DE ERROR
            StaticCode.Alerts("ERROR", "Error al listar", "¡ERROR!",
                    "Ha ocurrido un error al listar los coches: " + e);
        }
        return observableList; // RETORNA LA LISTA DE COCHES
    } // METODO PARA LISTAR TODOS LOS COCHES DE LA BASE DE DATOS

    public boolean buscarCoche(Session session, String matricula) {
        try {
            session.beginTransaction(); // INICIAR NUEVA TRANSACCION
            // CREAR LA CONSULTA HQL PARA BUSCAR EL COCHE POR MATRICULA
            String hql = "FROM coche WHERE matricula = :matricula";
            Query<Coche> cocheQuery = session.createQuery(hql, Coche.class);
            cocheQuery.setParameter("matricula", matricula);
            Coche cocheEncontrado = cocheQuery.uniqueResult(); // SE EJECUTA LA CONSULTA Y OBTIENE EL RESULTADO
            session.getTransaction().commit(); // CONFIRMAR TRANSACCION
            return cocheEncontrado != null; // SE RETORNA TRUE SI SE ENCONTRO, FALSE SI NO
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback(); // HACER UN ROLLBACK
            }// SI OCURRE UN ERROR, SE REVIERTE LA TRANSACCION

            // MENSAJE DE ERROR
            StaticCode.Alerts("ERROR", "Error al listar", "¡ERROR!",
                    "Ha ocurrido un error al listar el coche: " + e);
            return false; // OPERACION FALLIDA
        }
    } // METODO PARA BUSCAR UN COCHE
}