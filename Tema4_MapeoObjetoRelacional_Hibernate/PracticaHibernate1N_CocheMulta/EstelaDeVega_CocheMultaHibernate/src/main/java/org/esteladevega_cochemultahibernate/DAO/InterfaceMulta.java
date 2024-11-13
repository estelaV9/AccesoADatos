package org.esteladevega_cochemultahibernate.DAO;

import javafx.collections.ObservableList;
import org.esteladevega_cochemultahibernate.Model.Multa;

public interface InterfaceMulta {

    ObservableList<Multa> listarMultas(String matricula); // METODO PARA LISTAR TODOS LAS MULTAS DE ESE COCHE DE LA BASE DE DATOS

    boolean insertarMulta(Multa multa); // METODO PARA INSERTAR MULTAS

    boolean modificarMulta(Multa multa); // METODO PARA MODIFICAR DATOS DE LAS MULTAS

    boolean eliminarMulta(Multa multa); // METODO PARA ELIMINAR UNA MULTA

}
