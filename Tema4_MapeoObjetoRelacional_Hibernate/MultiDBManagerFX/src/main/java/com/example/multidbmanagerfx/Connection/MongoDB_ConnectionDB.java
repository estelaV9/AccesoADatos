package com.example.multidbmanagerfx.Connection;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import com.example.multidbmanagerfx.Utilities.R;
import java.io.IOException;
import java.util.Properties;

public class MongoDB_ConnectionDB {
    static MongoClient mongoClient;
    static MongoDatabase database;
    static {
        try {
            Properties configurationDB = new Properties(); // CREAR OBJETO TIPO PROPERTIES
            configurationDB.load(R.getProperties("Mongo_Database.properties")); // LEER EL ARCHIVO PROPERTIES Y COGER LOS DATOS
            String host = configurationDB.getProperty("host");
            String user = configurationDB.getProperty("user");
            String pass = configurationDB.getProperty("password");
            String port = configurationDB.getProperty("port");
            String source = configurationDB.getProperty("source");

            mongoClient = new MongoClient(new MongoClientURI("mongodb://"+user+":"+pass+"@"+host+":"+port+"/?authSource="+source)); // CONECTAR A LA BASE DE DATOS
            database = mongoClient.getDatabase("CarManagementDB");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void closeDataBase(){
        mongoClient.close();
    }
    public static MongoDatabase getDatabase(){
        return database;
    }
}