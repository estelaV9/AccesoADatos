package org.esteladevega_cochemultahibernate.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.esteladevega_cochemultahibernate.Model.Coche;
import org.esteladevega_cochemultahibernate.Model.Multa;
import org.esteladevega_cochemultahibernate.Utilities.HibernateUtil;
import org.esteladevega_cochemultahibernate.Utilities.StaticCode;
import org.hibernate.Session;

import java.util.List;

public class MultaDAO {
    public Session session;
    public MultaDAO(){
        session = HibernateUtil.getSession();
    } // CUANDO SE CREA SE ESTABLECE LA SESION

    public ObservableList<Multa> listarMultas(String matricula) {
        ObservableList<Multa> observableList = null; // LISTA PARA ALMACENAR LOS MULTAS
        try {
            // CONSULTA PARA OBTENER LOS DATOS DE LAS MULTAS  SEGUN LA MATRICULA DE LA BASE DE DATOS
            List listaMultasDB = session.createQuery("from Multa where matricula = '" + matricula+"'").list();
            observableList = FXCollections.observableArrayList(listaMultasDB); // SE AÑADE EL ARRAYLIST AL OBSERVABLELIST
        } catch (Exception e) {
            // MENSAJE DE ERROR
            StaticCode.Alerts("ERROR", "Error al listar", "¡ERROR!",
                    "Ha ocurrido un error al listar las multas: " + e);
        }
        return observableList; // RETORNA LA LISTA DE MULTAS
    } // METODO PARA LISTAR TODOS LAS MULTAS DE ESE COCHE DE LA BASE DE DATOS

}
