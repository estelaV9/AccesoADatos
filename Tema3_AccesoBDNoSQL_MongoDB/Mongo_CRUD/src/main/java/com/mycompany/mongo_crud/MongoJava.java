package com.mycompany.mongo_crud;

import com.mongodb.ErrorCategory;
import com.mongodb.MongoClient;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

public class MongoJava {
    public static void main(String[] args) {
        MongoClient con;
        try {
            con = ConnectionDB.conectar();

            //La clase MongoDatabase nos ofrece el método getDatabase() que nos permite seleccionar la base de datos
            //con la que queremos trabajar
            // Me conecto a la BD "biblioteca"
            MongoDatabase database = con.getDatabase("peliculas_infantiles");

            //Comencemos creando una Colección (equivalente a una tabla para MongoDB) para nuestra base de datos.
            //Me devuelve una coleccion si no existe la crea
            MongoCollection<Document> collection = database.getCollection("personajes");

            // Eliminar la colección y empezar de nuevo
            collection.drop();
            System.out.println("La coleccion se ha borrado Correctamente.\n");
            //creo una nueva coleccion
            database.createCollection("personajes");
            System.out.println("Coleccion creada Satisfactoriamente.\n");

            Document mickeyMouse = new Document();
            Document charlieBrown = new Document();
            //crear un documento con los valores que se insertarán usando el método append()
            mickeyMouse.append("_id", 1) // SI NO PONEMOS EL .append("_id"...) SE CREARA IGUAL, PERO NOS PONE UN NUMERO DE 24 EN HEXADECIMAL
                    .append("nombre", "Mickey Mouse")
                    .append("creador", new Document("nombre", "Walt").append("apellido", "Disney"))
                    .append("mascota", "Pluto");

            charlieBrown.append("_id", 2)
                    .append("nombre", "Charlie Brown")
                    .append("creador", new Document("nombre", "Charles").append("apellido", "Shultz"))
                    .append("mascota", "Snoopy");
            try {
                //La función ".insertOne()" se utiliza para insertar el documento en la colección.
                collection.insertOne(mickeyMouse);
                collection.insertOne(charlieBrown);
                System.out.println("Documento Insertado Correctamente. \n");
            } catch (MongoWriteException mwe) {
                if (mwe.getError().getCategory().equals(ErrorCategory.DUPLICATE_KEY)) {
                    System.out.println("El documento con esa identificación ya existe");
                }
            }

            // Tamaño de l coleccion
            System.out.println("Tamaño Collection : " + collection.count() + " documentos. \n");

            // Crear e insertar multiples documentos
            List<Document> documents = new ArrayList<Document>();
            for (int i = 3; i < 51; i++) {
                documents.add(new Document("_id", i)
                        .append("nombre", "")
                        .append("creador", "")
                        .append("mascota", "")
                );
            }
            collection.insertMany(documents);

            // Datos básicos de la recogida
            System.out.println("Tamaño Coleccion : " + collection.count() + " documentos. \n");

            // Modificar un documento
            // imprimir el tercer documento antes de actualizar.
            Document third = collection.find(Filters.eq("_id", 3)).first();
            System.out.println("Original third document:");
            System.out.println(third.toJson());

            collection.updateOne(new Document("_id", 3),
                    new Document("$set", new Document("nombre", "Dilbert")
                            .append("creador", new Document("nombre", "Scott").append("apellido", "Adams"))
                            .append("mascota", "Dogbert"))
            );
            System.out.println("\nModificar el 3 documento:");
            Document dilbert = collection.find(Filters.eq("_id", 3)).first();
            System.out.println(dilbert.toJson());

            // Encuentre e imprima TODOS los documentos de la colección
            System.out.println("Imprime los documentos.");

            MongoCursor<Document> cursor = collection.find().iterator();
            try {
                while (cursor.hasNext()) {
                    System.out.println(cursor.next().toJson());
                }

            } finally {
                cursor.close();
            }

            //Borrar datos
            System.out.println("\nEliminar documentos con un id mayor o igual a 4.");
            //borro todos los documentos con id>4
            collection.deleteMany(Filters.gte("_id", 4));

            // Encuentre e imprima TODOS los documentos de la colección
            System.out.println("\nImprime los documentos.");
            MongoCursor<Document> cursor2 = collection.find().iterator();
            try {
                while (cursor2.hasNext()) {
                    System.out.println(cursor2.next().toJson());
                }
            } finally {
                cursor2.close();
            }

            //me desconecto de la BD
            ConnectionDB.desconectar(con);
            System.out.println("\n Desconectado de la BD.");
        } catch (Exception exception) {
            System.err.println(exception.getClass().getName() + ": " + exception.getMessage());
        }
    }
}