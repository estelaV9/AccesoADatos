package com.example.esteladevega_ejercicioformulario.DAO;

import com.example.esteladevega_ejercicioformulario.Model.CubeUser;
import com.example.esteladevega_ejercicioformulario.Utilities.R;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }


}
