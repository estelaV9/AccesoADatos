package com.example.esteladevega_ejercicioformulario.DAO;

import com.example.esteladevega_ejercicioformulario.Model.CubeUser;
import com.example.esteladevega_ejercicioformulario.Utilities.R;
import com.example.esteladevega_ejercicioformulario.Utilities.StaticCode;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.Properties;

public class CubeUserDAO {
    public static boolean insertUser(Connection con, CubeUser cubeUser) {
        try {
            String sqlInsert = "INSERT INTO cube_users (NAME_USER, PASSWORD_USER, MAIL, REGISTRATION_DATE) " +
                    "VALUES (?, ?, ?, ?);";
            PreparedStatement statement = con.prepareStatement(sqlInsert);
            statement.setString(1, cubeUser.getNameUser());
            statement.setString(2, cubeUser.getPasswordUser());
            statement.setString(3, cubeUser.getMail());
            statement.setString(4, String.valueOf(cubeUser.getRegistrationDate()));
            int rowsInserted = statement.executeUpdate();
            // COMPROBAR SI EL NOMBRE INTRODUCIDO YA EXISTE
            if (rowsInserted > 0) {
                return true;
            }
        } catch (SQLException e) {
            StaticCode.Alerts("ERROR", "Error de conexión", "¡ERROR!",
                    "Error al conectar a la base de datos: " + e.getMessage());
            return false;
        }
        return false;
    }

    public static boolean isExistsUser(Connection con, CubeUser cubeUser) {
        try {
            String sqlQuery = "SELECT * FROM CUBE_USERS WHERE MAIL = ? AND PASSWORD_USER = ?";
            PreparedStatement statementQuery = con.prepareStatement(sqlQuery);
            statementQuery.setString(1, cubeUser.getMail());
            statementQuery.setString(2, cubeUser.getPasswordUser());
            ResultSet resultSet = statementQuery.executeQuery();
            if (resultSet.next()) {
                // SI EL USUARIO EXISTE, MOSTRAR UN MENSAJE DE ERROR
                return true;
            }
        } catch (SQLException e) {
            StaticCode.Alerts("ERROR", "Error de conexión", "¡ERROR!",
                    "Error al conectar a la base de datos: " + e.getMessage());
            return false;
        }
        return false;
    } // METODO PARA COMPROBAR SI EL USUARIO INTRODUCIDO YA EXISTE


    public static boolean deleteUser(Connection con, String mailUser) {
        try {
            String sqlDelete = "DELETE FROM CUBE_USERS WHERE MAIL = ?";
            PreparedStatement statement = con.prepareStatement(sqlDelete);
            statement.setString(1, mailUser);
            int rowsDelete = statement.executeUpdate();
            if (rowsDelete > 0) {
                // SI HA ELIMINADO CORRECTAMETNE RETURN TRUE
                return true;
            }
        } catch (SQLException e) {
            StaticCode.Alerts("ERROR", "Error de conexión", "¡ERROR!",
                    "Error al conectar a la base de datos: " + e.getMessage());
            return false;
        }
        return false;
    }


}
