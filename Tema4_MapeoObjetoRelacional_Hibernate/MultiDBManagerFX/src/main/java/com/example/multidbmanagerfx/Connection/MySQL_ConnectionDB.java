package com.example.multidbmanagerfx.Connection;

import com.example.multidbmanagerfx.Utilities.R;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySQL_ConnectionDB {
    public static Connection mysqlConnection;

    public static Connection conectar() {
        try {
            Properties properties = new Properties();
            properties.load(R.getProperties("MySQL_Database.properties"));

            String host = properties.getProperty("host");
            String port = properties.getProperty("port");
            String name = properties.getProperty("dbname");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");

            mysqlConnection = DriverManager.getConnection(
                    "jdbc:mysql://" + host + ":" + port + "/" + name + "?serverTimezone=UTC",
                    username,
                    password
            );
            return mysqlConnection;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    } // METODO PARA CONECTAR CON LA BASE DE DATOS DONDE CARGARA LOS DATOS DEL ARCHIVO CONFIGURATION

    public static void desconectar() throws SQLException {
        mysqlConnection.close();
    } // METODO PARA DESCONECTAR UNA APP A LA DATABASE
}