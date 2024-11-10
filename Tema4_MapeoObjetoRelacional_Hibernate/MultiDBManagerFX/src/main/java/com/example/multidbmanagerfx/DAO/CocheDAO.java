package com.example.multidbmanagerfx.DAO;

import com.example.multidbmanagerfx.Connection.MySQL_ConnectionDB;
import com.example.multidbmanagerfx.Controller.MySQL_CocheInterface;
import com.example.multidbmanagerfx.Model.Coche;
import com.example.multidbmanagerfx.Utilities.StaticCode;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CocheDAO implements MySQL_CocheInterface {
    private static final Connection connection = MySQL_ConnectionDB.conectar(); // CONECTAR LA BASE DE DATOS

    public static boolean insertCar(Coche coche) {
        try {
            String sql = "INSERT INTO coches (matricula, marca, modelo, tipo) VALUES (?, ?, ?, ?);";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, coche.getMatricula());
            statement.setString(2, coche.getMarca());
            statement.setString(3, coche.getModelo());
            statement.setString(4, coche.getTipo());
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
    } // METODO PARA INSERTAR COCHE

    public static ObservableList<Coche> listOfCars() {
        ObservableList<Coche> observableListCar = FXCollections.observableArrayList();
        try {
            String sql = "SELECT matricula, marca, modelo, tipo FROM coches;";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                // AÑADIR LOS DATOS A UN OBSERVABLELIST
                String matricula = resultSet.getString("matricula");
                String marca = resultSet.getString("marca");
                String modelo = resultSet.getString("modelo");
                String tipo = resultSet.getString("tipo");
                Coche cocheList = new Coche(matricula, marca, modelo, tipo);
                observableListCar.add(cocheList);
            }
        } catch (Exception e) {
            // SI HAY ALGUN PROBLEMA EN EL METODO LISTAR, SALTA UN MENSAJE
            StaticCode.Alerts("ERROR", "Error al listar", "¡ERROR!",
                    "Error al listar los coches de la base de datos: " + e.getMessage());
            System.out.println(e.getMessage());
        }
        return observableListCar;
    } // METODO PARA LISTAR LOS COCHES DE LA BASE DE DATOS


}
