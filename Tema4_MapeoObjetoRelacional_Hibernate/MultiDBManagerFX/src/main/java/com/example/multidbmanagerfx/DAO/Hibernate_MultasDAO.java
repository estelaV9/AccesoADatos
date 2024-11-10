package com.example.multidbmanagerfx.DAO;

import com.example.multidbmanagerfx.Model.Multa;
import com.example.multidbmanagerfx.Utilities.HibernateUtil;
import com.example.multidbmanagerfx.Utilities.StaticCode;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.ArrayList;
import java.util.List;

public class Hibernate_MultasDAO implements MultaInterface {
    Session session;

    public Hibernate_MultasDAO() {
        session = HibernateUtil.getSession(); // INICIAR LA SESIÓN AL CREAR EL DAO
    }

    @Override
    public ObservableList<Multa> listOfFines(String carNumberPlate) {
        List multas = new ArrayList<>();
        try {
            session.beginTransaction(); // INICIAR TRANSACCIÓN
            multas = session.createQuery("from Multa where matricula = '" + carNumberPlate+"'").list(); // OBTENER LA LISTA DE RESULTADOS
            session.getTransaction().commit(); // CONFIRMAR LA TRANSACCIÓN
            session.clear(); // LIMPIAR SESSION
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback(); // HACER ROLLBACK SI OCURRE ERROR
            }
            StaticCode.Alerts("ERROR", "Error al listar multas", "¡ERROR!",
                    "Ha ocurrido un error al listar las multas: " + e);
        }
        return FXCollections.observableArrayList(multas); // RETORNAR LA LISTA DE MULTAS
    }

    @Override
    public boolean insertFine(Multa multa) {
        try {
            session.beginTransaction(); // INICIAR NUEVA TRANSACCIÓN
            session.save(multa); // INSERTAR MULTA EN LA BASE DE DATOS
            session.getTransaction().commit(); // CONFIRMAR TRANSACCIÓN
            session.clear(); // LIMPIAR SESSION
            return true; // OPERACIÓN EXITOSA
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback(); // HACER ROLLBACK SI OCURRE ERROR
            }
            StaticCode.Alerts("ERROR", "Error al insertar multa", "¡ERROR!",
                    "Ha ocurrido un error al insertar la multa: " + e);
            return false; // OPERACIÓN FALLIDA
        }
    }

    @Override
    public boolean deleteFine(String carNumberPlate) {
        try {
            session.beginTransaction(); // INICIAR NUEVA TRANSACCIÓN

            // CONSULTA HQL PARA ELIMINAR MULTAS POR MATRÍCULA
            Query query = session.createQuery("DELETE FROM multas WHERE matricula = :matricula");
            query.setParameter("matricula", carNumberPlate);
            int result = query.executeUpdate(); // EJECUTAR ACTUALIZACIÓN

            session.getTransaction().commit(); // CONFIRMAR TRANSACCIÓN
            session.clear(); // LIMPIAR SESSION
            return result > 0; // RETORNAR TRUE SI SE ELIMINÓ ALGUNA FILA
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback(); // HACER ROLLBACK SI OCURRE ERROR
            }
            StaticCode.Alerts("ERROR", "Error al eliminar multa", "¡ERROR!",
                    "Ha ocurrido un error al eliminar la multa: " + e);
            return false; // OPERACIÓN FALLIDA
        }
    }

    @Override
    public boolean modifyFine(Multa newMulta, int id) {
        try {
            session.beginTransaction(); // INICIAR NUEVA TRANSACCIÓN
            // OBTENER MULTA EXISTENTE A MODIFICAR
            Multa multaExistente = session.get(Multa.class, id);

            if (multaExistente != null) {
                // ACTUALIZAR CAMPOS DE LA MULTA
                multaExistente.setPrecio(newMulta.getPrecio());
                multaExistente.setFecha(newMulta.getFecha());
                multaExistente.getCoche().setMatricula(newMulta.getCoche().getMatricula());

                session.update(multaExistente); // ACTUALIZAR LA MULTA
                session.getTransaction().commit(); // CONFIRMAR TRANSACCIÓN
                session.clear(); // LIMPIAR SESSION
                return true; // OPERACIÓN EXITOSA
            } else {
                StaticCode.Alerts("ERROR", "Multa no encontrada", "¡ERROR!",
                        "No se encontró la multa a modificar.");
                return false;
            }
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback(); // HACER ROLLBACK SI OCURRE ERROR
            }
            StaticCode.Alerts("ERROR", "Error al modificar multa", "¡ERROR!",
                    "Ha ocurrido un error al modificar la multa: " + e);
            return false; // OPERACIÓN FALLIDA
        }
    }
}