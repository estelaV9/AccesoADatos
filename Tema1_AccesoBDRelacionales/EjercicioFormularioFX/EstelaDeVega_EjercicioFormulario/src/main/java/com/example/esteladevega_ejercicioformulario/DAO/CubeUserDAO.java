package com.example.esteladevega_ejercicioformulario.DAO;

import com.example.esteladevega_ejercicioformulario.Controller.RegistrationCtrller;
import com.example.esteladevega_ejercicioformulario.Model.CubeUser;
import com.example.esteladevega_ejercicioformulario.Utilities.StaticCode;
import java.sql.*;

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
            if (rowsInserted > 0) {
                return true;
            } // SI SE HA INSERTADO, RETORNARA TRUE
        } catch (SQLException e) {
            StaticCode.Alerts("ERROR", "Error de conexión", "¡ERROR!",
                    "Error al conectar a la base de datos: " + e.getMessage());
            return false;
        }
        return false;
    } // METODO PARA INSERTAR USUARIO

    public static boolean isExistsUser(Connection con, CubeUser cubeUser) {
        try {
            String sqlQuery = "SELECT * FROM CUBE_USERS WHERE MAIL = ? AND PASSWORD_USER = ?";
            PreparedStatement statementQuery = con.prepareStatement(sqlQuery);
            statementQuery.setString(1, cubeUser.getMail());
            statementQuery.setString(2, cubeUser.getPasswordUser());
            ResultSet resultSet = statementQuery.executeQuery();
            if (resultSet.next()) {
                return true;
            } // SI EL USUARIO EXISTE, RETORNARA TRUE
        } catch (SQLException e) {
            StaticCode.Alerts("ERROR", "Error de conexión", "¡ERROR!",
                    "Error al conectar a la base de datos: " + e.getMessage());
            return false;
        }
        return false;
    } // METODO PARA COMPROBAR SI EL USUARIO INTRODUCIDO YA EXISTE

    public static boolean isExistsNameUser(Connection con, String name, String mail) {
        try {
            String sqlQuery = "SELECT * FROM CUBE_USERS WHERE NAME_USER = ? AND MAIL != ?";
            PreparedStatement statementQuery = con.prepareStatement(sqlQuery);
            statementQuery.setString(1, name);
            statementQuery.setString(2, mail);
            ResultSet resultSet = statementQuery.executeQuery();
            if (resultSet.next()) {
                return true;
            } // SI EL NOMBRE DE USUARIO EXISTE, RETORNARA TRUE
        } catch (SQLException e) {
            StaticCode.Alerts("ERROR", "Error de conexión", "¡ERROR!",
                    "Error al conectar a la base de datos: " + e.getMessage());
            return false;
        }
        return false;
    } // METODO PARA COMPROBAR SI EL NOMBRE DE USUARIO YA EXISTE


    public static boolean deleteUser(Connection con, String mailUser) {
        try {
            String sqlDelete = "DELETE FROM CUBE_USERS WHERE MAIL = ?";
            PreparedStatement statement = con.prepareStatement(sqlDelete);
            statement.setString(1, mailUser);
            int rowsDelete = statement.executeUpdate();
            if (rowsDelete > 0) {
                RegistrationCtrller.cubeUser = null; // INVALIDAR USUARIO
                return true;
            } // SI HA ELIMINADO CORRECTAMETNE RETURN TRUE
        } catch (SQLException e) {
            StaticCode.Alerts("ERROR", "Error de conexión", "¡ERROR!",
                    "Error al conectar a la base de datos: " + e.getMessage());
            return false;
        }
        return false;
    } // METODO PARA ELIMINAR USUARIO

    public static boolean modifyPassword(Connection con, String newPassword, String mailUser) {
        try {
            String sqlUpdate = "UPDATE CUBE_USERS SET PASSWORD_USER = ? WHERE MAIL = ?";
            PreparedStatement statement = con.prepareStatement(sqlUpdate);
            statement.setString(1, newPassword);
            statement.setString(2, mailUser);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                return true;
            } // SI SE HA ACTUALIZADO CORRECTAMENTE, RETORNARA TRUE
        } catch (SQLException e) {
            StaticCode.Alerts("ERROR", "Error de conexión", "¡ERROR!",
                    "Error al conectar a la base de datos: " + e.getMessage());
            return false;
        }
        return false;
    } // METODO PARA MODIFICAR CONTRASEÑA

    public static boolean modifyUser(Connection connection, String nameUser, String emailUser, String mailUser) {
        try {
            String sqlUpdate = "UPDATE CUBE_USERS SET NAME_USER = ?, MAIL = ? WHERE MAIL = ?";
            PreparedStatement statement = connection.prepareStatement(sqlUpdate);
            statement.setString(1, nameUser);
            statement.setString(2, emailUser);
            statement.setString(3, mailUser);
            int rowsUpdate = statement.executeUpdate();
            if (rowsUpdate > 0) {
                return true;
            } // SI SE HA ACTUALIZADO CORRECTAMENTE, RETORNARA TRUE
        } catch (SQLException e) {
            StaticCode.Alerts("ERROR", "Error de conexión", "¡ERROR!",
                    "Error al conectar a la base de datos: " + e.getMessage());
            return false;
        }
        return false;
    } // METODO PARA MODIFICAR AL USUARIO

    public static String searchNameUser(Connection con, String mail) {
        try {
            String sqlQuery = "SELECT NAME_USER FROM CUBE_USERS WHERE MAIL = ?";
            PreparedStatement statementQuery = con.prepareStatement(sqlQuery);
            statementQuery.setString(1, mail);
            ResultSet resultSet = statementQuery.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("NAME_USER");
            } // SI DEVUELVE DATOS EN LA CONSULTA, RETORNARA EL NOMBRE DEL USUARIO
        } catch (SQLException e) {
            StaticCode.Alerts("ERROR", "Error de conexión", "¡ERROR!",
                    "Error al conectar a la base de datos: " + e.getMessage());
            return null;
        }
        return null;
    } // METODO PARA DEVOLVER EL NOMBRE DE UN USUARIO MEDIANTE SU MAIL
}