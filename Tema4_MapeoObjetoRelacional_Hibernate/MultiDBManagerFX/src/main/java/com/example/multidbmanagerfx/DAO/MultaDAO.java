package com.example.multidbmanagerfx.DAO;

import com.example.multidbmanagerfx.Connection.MySQL_ConnectionDB;
import com.example.multidbmanagerfx.Model.Multa;
import com.example.multidbmanagerfx.Utilities.StaticCode;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

public class MultaDAO {
    private Connection connection;

    public MultaDAO () {
        connection = MySQL_ConnectionDB.conectar(); // CONECTAR LA BASE DE DATOS
    }

    public ObservableList<Multa> listOfFines(String carNumberPlate) {
        ObservableList<Multa> observableListFine = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM multas WHERE matricula = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, carNumberPlate);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                // AÑADIR LOS DATOS A UN OBSERVABLELIST
                int id = resultSet.getInt("id_multa");
                double precio = resultSet.getDouble("precio");
                LocalDate fecha = resultSet.getDate("fecha").toLocalDate();
                String matricula = resultSet.getString("matricula");
                Multa multa = new Multa(id, precio, fecha, matricula); // OBJETO DE MULTA CON SUS DATOS
                observableListFine.add(multa); // AÑADIR LAS MULTAS CREADAS
            }
        } catch (Exception e) {
            // SI HAY ALGUN PROBLEMA EN EL METODO LISTAR, SALTA UN MENSAJE
            StaticCode.Alerts("ERROR", "Error al listar", "¡ERROR!",
                    "Error al listar los coches de la base de datos: " + e.getMessage());
            System.out.println(e.getMessage());
        }
        return observableListFine;
    } // METODO PARA LISTAR LAS MULTAS DE COCHES DE LA BASE DE DATOS
}
