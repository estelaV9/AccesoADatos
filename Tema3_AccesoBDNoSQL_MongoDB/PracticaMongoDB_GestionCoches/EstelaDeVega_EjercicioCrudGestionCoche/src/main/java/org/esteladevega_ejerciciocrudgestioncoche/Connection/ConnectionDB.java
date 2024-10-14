package org.esteladevega_ejerciciocrudgestioncoche.Connection;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionDB {
    // ATRIBUTO ESTATICO DE TIPO CONEXION PARA PODER LLAMARLO DESDE CUALQUIER CLASE
    public static MongoClient con;
    public static MongoClient conectar() {
        Properties properties = new Properties();
        String host = "", port = "", name = "", username = "", password = "";

        try {
            properties.load(new FileInputStream("src/main/resources/Configuration/database.properties"));
            host = String.valueOf(properties.get("host"));
            port = String.valueOf(properties.get("port"));
            name = String.valueOf(properties.get("name"));
            username = String.valueOf(properties.get("username"));
            password = String.valueOf(properties.get("password"));
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = new MongoClient(new MongoClientURI("mongodb://" + username + ":" + password + "@" + host + ":" + port + "/?authSource=admin"));
            return con;
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            return null;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    } // METODO PARA CONECTAR UNA APP A LA DATABASE DE MONGODB

    public static void desconectar(MongoClient con) {
        con.close();
    } // METODO PARA DESCONECTAR UNA APP A LA DATABASE DE MONGODB
}