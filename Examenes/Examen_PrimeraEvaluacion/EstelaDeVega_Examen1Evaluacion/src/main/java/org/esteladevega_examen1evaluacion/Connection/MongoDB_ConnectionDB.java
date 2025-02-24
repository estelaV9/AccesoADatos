package org.esteladevega_examen1evaluacion.Connection;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import org.esteladevega_examen1evaluacion.Utilities.StaticCode;

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
            name = String.valueOf(properties.get("source"));
            username = String.valueOf(properties.get("user"));
            password = String.valueOf(properties.get("password"));
            mongoClient = new MongoClient(new MongoClientURI("mongodb://" + username + ":" + password + "@" + host + ":" + port + "/?authSource=admin"));
            database =mongoClient.getDatabase("ExamenEquipos");
            return mongoClient;
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