package com.example.esteladevega_ejercicioformulario.DAO;

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
    private Connection conexion;

    public void conectar() throws ClassNotFoundException, SQLException, IOException {
        Properties configuration = new Properties();
        configuration.load(R.getProperties("database.properties"));
        String host = configuration.getProperty("host");
        String port = configuration.getProperty("port");
        String name = configuration.getProperty("name");
        String username = configuration.getProperty("username");
        String password = configuration.getProperty("password");

        Class.forName("com.mysql.cj.jdbc.Driver");
        conexion = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + name + "?serverTimezone=UTC",
                username, password);
    }

    public void desconectar() throws SQLException {
        conexion.close();
    }

    public boolean insertarUsuarios(String nameUser, String passwdUser, String mailUser, LocalDate registration) throws SQLException {
        String sqlInsert = "INSERT INTO cube_users (NAME_USER, PASSWORD_USER, MAIL, REGISTRATION_DATE) " +
                "VALUES (?, ?, ?, ?);";
        PreparedStatement statement = conexion.prepareStatement(sqlInsert);
        statement.setString(1, nameUser);
        statement.setString(2, passwdUser);
        statement.setString(3, mailUser);
        statement.setString(4, String.valueOf(registration));
        int rowsInserted = statement.executeUpdate();
        // COMPROBAR SI EL NOMBRE INTRODUCIDO YA EXISTE
        if (rowsInserted > 0) {
            return true;
        }
        return false;
    }


}
