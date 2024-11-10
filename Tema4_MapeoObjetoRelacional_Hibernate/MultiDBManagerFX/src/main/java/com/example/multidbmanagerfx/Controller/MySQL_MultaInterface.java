package com.example.multidbmanagerfx.Controller;

import com.example.multidbmanagerfx.Model.Multa;
import javafx.collections.ObservableList;

public interface MySQL_MultaInterface {
    ObservableList<Multa> listOfFines(String carNumberPlate); // LISTAR MULTAS
    boolean insertFine(Multa multa); // INSERTAR MULTA
    boolean deleteFine(String carNumberPlate); // ELIMINAR MULTA
}
