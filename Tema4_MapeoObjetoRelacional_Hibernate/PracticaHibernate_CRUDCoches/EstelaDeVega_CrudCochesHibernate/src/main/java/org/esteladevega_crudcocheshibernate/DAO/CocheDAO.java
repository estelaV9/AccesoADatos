package org.esteladevega_crudcocheshibernate.DAO;

import org.esteladevega_crudcocheshibernate.Model.Coche;
import org.esteladevega_crudcocheshibernate.StaticCode.StaticCode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

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

    public static boolean modificarCoche(Session session, Coche coche) {
        try {
            session.beginTransaction(); // INICIAR NUEVA TRANSACCION
            session.update(coche); // MODIFICAR EL COCHE EN LA BASE DE DATOS
            session.getTransaction().commit(); // CONFIRMAR TRANSACCION
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

    public static boolean eliminarCoche(Session session, Coche coche) {
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

    public static ArrayList<Coche> listarCoches(Session session) {
        ArrayList<Coche> listaCoches = new ArrayList<>(); // LISTA PARA ALMACENAR LOS COCHES
        try {
            // CONSULTA PARA OBTENER LOS DATOS DE LOS COCHES DE LA BASE DE DATOS
            List<Coche> listaCochesDB = session.createQuery("from coche").getResultList();
            for(Coche coche: listaCochesDB){
                listaCoches.add(coche); // SE AÑADE LOS COCHES
            } // SE RECORREN LA LISTA OBTENIDA Y SE AÑADE EN UN ARRAYLIST
        } catch (Exception e) {
            // MENSAJE DE ERROR
            StaticCode.Alerts("ERROR", "Error al listar", "¡ERROR!",
                    "Ha ocurrido un error al listar los coches: " + e);
        }
        return  listaCoches; // RETORNA LA LISTA DE COCHES
    } // METODO PARA LISTAR TODOS LOS COCHES DE LA BASE DE DATOS

    public static boolean buscarCoche (Session session, String matricula){
        try {
            session.beginTransaction(); // INICIAR NUEVA TRANSACCION
            // CREAR LA CONSULTA HQL PARA BUSCAR EL COCHE POR MATRICULA
            String hql = "FROM coche WHERE matricula = :matricula";
            Query<Coche> cocheQuery = session.createQuery(hql, Coche.class);
            cocheQuery.setParameter("matricula", matricula);
            Coche cocheEncontrado = cocheQuery.uniqueResult(); // SE EJECUTA LA CONSULTA Y OBTIENE EL RESULTADO
            session.getTransaction().commit(); // CONFIRMAR TRANSACCION
            return cocheEncontrado != null; // SE RETORNA TRUE SI SE ENCONTRO, FALSE SI NO
        }catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback(); // HACER UN ROLLBACK
            }// SI OCURRE UN ERROR, SE REVIERTE LA TRANSACCION

            // MENSAJE DE ERROR
            StaticCode.Alerts("ERROR", "Error al eliminar", "¡ERROR!",
                    "Ha ocurrido un error al eliminar el coche: " + e);
            return false; // OPERACION FALLIDA
        }
    } // METODO PARA BUSCAR UN COCHE
}