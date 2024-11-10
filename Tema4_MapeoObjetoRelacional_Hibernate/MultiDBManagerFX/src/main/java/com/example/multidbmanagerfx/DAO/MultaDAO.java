package com.example.multidbmanagerfx.DAO;

import com.example.multidbmanagerfx.Connection.MySQL_ConnectionDB;
import com.example.multidbmanagerfx.Model.Multa;
import com.example.multidbmanagerfx.Utilities.StaticCode;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class MultaDAO implements MySQL_MultaInterface {
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
                LocalDate fecha = LocalDate.parse(resultSet.getString("fecha"));
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

    public boolean insertFine (Multa multa) {
        try {
            String sql = "INSERT INTO multas VALUES (?, ?, ?, ?);";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, multa.getIdMulta());
            statement.setDouble(2, multa.getPrecio());
            statement.setString(3, multa.getFecha().toString());
            statement.setString(4, multa.getMatricula());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                return true; // SI SE INSERTO, DEVUELVE TRUE
            }
        } catch (SQLException e) {
            StaticCode.Alerts("ERROR", "Error de conexión", "¡ERROR!",
                    "Error al conectar a la base de datos: " + e.getMessage());
            return false; // SI OCURRIO UN ERROR, DEVUELVE FALSE
        }
        return false;
    } // METODO PARA INSERTAR UNA MULTA A UN COCHE

    public boolean deleteFine(String carNumberPlate){
        try {
            String sql = "DELETE FROM multas WHERE matricula = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, carNumberPlate);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                return true; // SI SE ELIMINO CORRECTAMENTE, DEVUELVE TRUE
            }
        } catch (SQLException e) {
            StaticCode.Alerts("ERROR", "Error de conexión", "¡ERROR!",
                    "Error al conectar a la base de datos: " + e.getMessage());
            return false; // SI OCURRIO UN ERROR, DEVUELVE FALSE
        }
        return false;
    } // METODO PARA ELIMINAR UNA MULTA DE UN COCHE SEGUN LA MATRICULA

    public boolean modifyFine(Multa newMulta, int id) {
        try {
            String sql = "UPDATE multas SET precio = ?, fecha = ? WHERE id_multa = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, newMulta.getPrecio());
            statement.setString(2, newMulta.getFecha().toString());
            statement.setInt(3, id);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                return true; // SI SE MODIFICO CORRECTAMENTE, DEVUELVE TRUE
            }
        } catch (SQLException e) {
            StaticCode.Alerts("ERROR", "Error de conexión", "¡ERROR!",
                    "Error al conectar a la base de datos: " + e.getMessage());
            return false; // SI OCURRIO UN ERROR, DEVUELVE FALSE
        }
        return false;
    } // METODO PARA MODIFICAR UNA MULTA
}
