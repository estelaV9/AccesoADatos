package org.esteladevega_cochemultahibernate.DAO;

import javafx.collections.ObservableList;
import org.esteladevega_cochemultahibernate.Model.Coche;
import org.hibernate.Session;

public interface InterfaceCoche {
    boolean insertarCoche(Session session, Coche coche); // METODO PARA INSERTAR COCHES

    boolean modificarCoche(Session session, Coche coche); // METODO PARA MODIFICAR DATOS DE LOS COCHES

    boolean eliminarCoche(Session session, Coche coche); // METODO PARA ELIMINAR UN COCHE

    ObservableList<Coche> listarCoches(Session session); // METODO PARA LISTAR TODOS LOS COCHES DE LA BASE DE DATOS

    boolean buscarCoche(Session session, String matricula); // METODO PARA BUSCAR UN COCHE
}
