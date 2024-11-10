package com.example.multidbmanagerfx.DAO;

import com.example.multidbmanagerfx.Model.Multa;
import javafx.collections.ObservableList;

public interface MySQL_MultaInterface {
    ObservableList<Multa> listOfFines(String carNumberPlate); // LISTAR MULTAS
    boolean insertFine(Multa multa); // INSERTAR MULTA
    boolean deleteFine(String carNumberPlate); // ELIMINAR MULTA
    boolean modifyFine(Multa newMulta, int id); // MODIFICAR UNA MULTA
}
