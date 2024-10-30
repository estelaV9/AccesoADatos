package org.esteladevega_crudcocheshibernate.DAO;

import org.esteladevega_crudcocheshibernate.Model.Coche;
import org.esteladevega_crudcocheshibernate.StaticCode.StaticCode;
import org.hibernate.Session;

public class CocheDAO {
    public static boolean insertarCoche(Session session, Coche coche) {
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

}
