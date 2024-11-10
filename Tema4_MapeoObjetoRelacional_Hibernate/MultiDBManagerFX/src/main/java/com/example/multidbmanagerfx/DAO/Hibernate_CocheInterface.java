package com.example.multidbmanagerfx.DAO;

import com.example.multidbmanagerfx.Model.Coche;
import javafx.collections.ObservableList;

public interface Hibernate_CocheInterface {
    boolean insertarCoche(Coche coche); // METODO PARA INSERTAR COCHES

    boolean modificarCoche(Coche coche); // METODO PARA MODIFICAR DATOS DE LOS COCHES

    boolean eliminarCoche(Coche coche); // METODO PARA ELIMINAR UN COCHE

    ObservableList<Coche> listarCoches(); // METODO PARA LISTAR TODOS LOS COCHES DE LA BASE DE DATOS

    boolean buscarCoche(String matricula); // METODO PARA BUSCAR UN COCHE
}