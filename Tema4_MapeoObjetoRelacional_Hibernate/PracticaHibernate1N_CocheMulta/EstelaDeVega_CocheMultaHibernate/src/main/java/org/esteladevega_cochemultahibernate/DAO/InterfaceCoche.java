package org.esteladevega_cochemultahibernate.DAO;

import javafx.collections.ObservableList;
import org.esteladevega_cochemultahibernate.Model.Coche;
import org.hibernate.Session;

public interface InterfaceCoche {
    boolean insertarCoche(Coche coche); // METODO PARA INSERTAR COCHES

    boolean modificarCoche(Coche coche); // METODO PARA MODIFICAR DATOS DE LOS COCHES

    boolean eliminarCoche(Coche coche); // METODO PARA ELIMINAR UN COCHE

    ObservableList<Coche> listarCoches(); // METODO PARA LISTAR TODOS LOS COCHES DE LA BASE DE DATOS

    boolean buscarCoche(String matricula); // METODO PARA BUSCAR UN COCHE
}
