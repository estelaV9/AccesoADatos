package com.example.multidbmanagerfx.Connection;

import com.example.multidbmanagerfx.Utilities.StaticCode;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import com.example.multidbmanagerfx.Utilities.R;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class MongoDB_ConnectionDB {
    static MongoClient mongoClient;
    static MongoDatabase database;
    public static MongoClient conectar() {
        Properties properties = new Properties();
        String host = "", port = "", name = "", username = "", password = "";

        try {
            properties.load(new FileInputStream("src/main/resources/configuration/Mongo_Database.properties"));
            host = String.valueOf(properties.get("host"));
            port = String.valueOf(properties.get("port"));
            name = String.valueOf(properties.get("name"));
            username = String.valueOf(properties.get("username"));
            password = String.valueOf(properties.get("password"));
            return mongoClient = new MongoClient(new MongoClientURI("mongodb://" + username + ":" + password + "@" + host + ":" + port + "/?authSource=admin"));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            StaticCode.Alerts("ERROR", "Error de conexión",
                    "Archivo de configuración no encontrado",
                    "No se ha encontrado el archivo database.properties: " + e.getMessage());
            return null;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            StaticCode.Alerts("ERROR", "Error de conexión",
                    "Error de E/S", "Hubo un problema al leer el archivo de configuración: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    } // METODO PARA CONECTAR UNA APP A LA DATABASE DE MONGODB
    public static void closeDataBase(){
        mongoClient.close();
    }
    public static MongoDatabase getDatabase(){
        return database;
    }
}