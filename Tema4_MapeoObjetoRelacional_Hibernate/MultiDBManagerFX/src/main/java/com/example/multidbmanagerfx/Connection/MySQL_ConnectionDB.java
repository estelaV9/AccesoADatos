package com.example.multidbmanagerfx.Connection;

import com.example.multidbmanagerfx.Utilities.R;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class MySQL_ConnectionDB {
    public static Connection mysqlConnection;

    public static Connection conectar() {
        try {
            Properties properties = new Properties();
            properties.load(R.getProperties("database.properties"));

            String host = properties.getProperty("host");
            String port = properties.getProperty("port");
            String name = properties.getProperty("name");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");

            return DriverManager.getConnection(
                    "jdbc:mysql://" + host + ":" + port + "/" + name + "?serverTimezone=UTC",
                    username,
                    password
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}