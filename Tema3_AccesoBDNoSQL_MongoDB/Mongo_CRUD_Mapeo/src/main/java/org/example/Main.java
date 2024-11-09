package org.example;

import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class Main {
    public static void main(String[] args) {
        MongoClient con;
        MongoCollection<Document> collection = null;
        String json;
        Document doc;

        try {
            con = ConnectionDB.conectar();

            //La clase MongoDatabase nos ofrece el método getDatabase() que nos permite seleccionar la base de datos
            //con la que queremos trabajar
            // Me conecto a la BD "taller" si NO existe la crea.

            MongoDatabase database = con.getDatabase("taller");
            //me creo una coleccion coches
            collection = database.getCollection("coches");


        } catch (Exception exception) {
            System.err.println(exception.getClass().getName() + ": " + exception.getMessage());
        }

        //Trabajar con objetos en java utilizando gson
        Coche coche1 = new Coche(); // Creo un objeto

        coche1.setMatricula("6666HHH");
        coche1.setMarca("Renault");
        coche1.setModelo("Clio");
        coche1.setTipo("Deportivo");
        Coche coche2 = new Coche(); // Creo un objeto

        coche2.setMatricula("5555BCD");
        coche2.setMarca("Ford");
        coche2.setModelo("SMax");
        coche2.setTipo("Familiar");

        // Deserializar objeto a string json
        Gson gson = new Gson();
        json = gson.toJson(coche1);

        //Ten en cuenta que lo que se inserta en Mongo son documentos por tanto tengo que convertirlo
        // Parsear un documento bson e insertar
        doc = Document.parse(json);

        collection.insertOne(doc);


        //inserto el coche 2
        json = gson.toJson(coche1);
        // Parsear un documento bson e insertar
        doc = Document.parse(json);

        collection.insertOne(doc);

        // Recuperar para asegurarse de que se insertó el objeto
        MongoCursor<Document> cursor3 = collection.find().iterator();
        try {
            while (cursor3.hasNext()) {
                //System.out.println(cursor3.next().toJson());
                //También puede usar Gson para convertir el documento bson recuperado de nuevo al objeto Java
                //SERIALIZAR
                Coche coche3 = gson.fromJson(cursor3.next().toJson(), Coche.class);
                System.out.print(coche3.toString());
            }
        } finally {
            cursor3.close();
        }
    }
}