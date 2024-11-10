package com.example.multidbmanagerfx.Controller;

import com.example.multidbmanagerfx.Model.Coche;
import javafx.collections.ObservableList;

public interface MySQL_CocheInterface {
    boolean insertCar(Coche coche); // INSERTAR COCHE

    ObservableList<Coche> listOfCars(); // LISTAR COCHES
}
