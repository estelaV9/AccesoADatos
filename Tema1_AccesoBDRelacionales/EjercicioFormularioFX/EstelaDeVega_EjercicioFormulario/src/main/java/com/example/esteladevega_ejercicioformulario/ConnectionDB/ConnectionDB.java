package com.example.esteladevega_ejercicioformulario.ConnectionDB;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class ConnectionDB {
    public static Connection con;

    public static Connection conectar() throws ClassNotFoundException, SQLException {
        boolean connect = false;
        Properties properties = new Properties();
        String host = "";
        String port = "";
        String name = "";
        String username = "";
        String password = "";

        try {
            properties.load(new FileInputStream(new File("src/main/resources/Configuration/database.properties")));
            host = String.valueOf(properties.get("host"));
            port = String.valueOf(properties.get("port"));
            name = String.valueOf(properties.get("name"));
            username = String.valueOf(properties.get("username"));
            password = String.valueOf(properties.get("password"));
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + name + "?serverTimezone=UTC",
                    username, password);
            return con;
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            return null;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    } // METODO PARA CONECTAR UNA APP A LA DATABASE

    public static void desconectar() throws SQLException {
        con.close();
    } // METODO PARA DESCONECTAR UNA APP A LA DATABASE

}

