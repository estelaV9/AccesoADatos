package org.esteladevega_ejerciciocrudgestioncoche.DAO;

import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.esteladevega_ejerciciocrudgestioncoche.Connection.ConnectionDB;
import org.esteladevega_ejerciciocrudgestioncoche.Model.Coche;
import org.esteladevega_ejerciciocrudgestioncoche.Utilities.StaticCode;

public class CocheDAO {
    public static void insertCar(MongoClient con, Coche coche) {
        MongoCollection<Document> collection = null;
        String json;
        Document doc;

        try {
            con = ConnectionDB.conectar();
            MongoDatabase database = con.getDatabase("Coches");
            // CREAR UNA COLECCION
            database.createCollection("Coches");

            // INSERTAR UN DOCUMENTO EN LA COLECCION coches
            collection = database.getCollection("Coches");

            // DESERIALIZAR OBJETO A STRING JSON
            Gson gson = new Gson();
            json = gson.toJson(coche); // INSERTAR COCHE

            // INSERTAR DOCUMENTOS, PARA ELLO TENDREMOS QUE CONVERTIRLO Y PARSEAR UN DOC BSON E INSERTAR
            doc = Document.parse(json); // PARSEAR UN DOCUMENTO BSON E INSERTAR
            collection.insertOne(doc);
            StaticCode.Alerts("INFORMATION", "Insertar Coche", "INFORMATION",
                    "Se ha insertado el coche correctamente");
        } catch (Exception e) {
            StaticCode.Alerts("ERROR", "Error de conexión", "¡ERROR!",
                    "Error al conectar a la base de datos: " + e.getMessage());
        }
        StaticCode.Alerts("ERROR", "Ha ocurrido un error", "¡ERROR!",
                "Ha ocurrido un error al insertar un coche");
    } // METODO PARA INSERTAR COCHE


}
