package com.example.multidbmanagerfx.DAO;

import com.example.multidbmanagerfx.Connection.MongoDB_ConnectionDB;
import com.example.multidbmanagerfx.Model.Coche;
import com.example.multidbmanagerfx.Utilities.StaticCode;
import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.bson.Document;
import java.util.ArrayList;

public class MongoDB_CocheDAO implements CocheInterface {
    MongoClient mongoClient; // BASE DE DATOS DE MONGO
    MongoDatabase mongoDatabase;
    Gson gson; // ATRIBUTO GSON PARA CONVERTIR DE JSON A Gson
    MongoCollection<Document> collection;

    public MongoDB_CocheDAO() {
        mongoClient = MongoDB_ConnectionDB.conectar();
        gson = new Gson(); // CREAR OBJETO GSON PARA LA SERIALIZACION
        mongoDatabase = mongoClient.getDatabase("CarManagementDB");
        collection = mongoDatabase.getCollection("coches");
    }

    public boolean insertCar(Coche coche) {
        String json; // VARIABLE PARA ALMACENAR EL JSON DEL COCHE
        Document doc; // DOCUMENTO BSON QUE SE INSERTARA EN LA BD
        try {
            json = gson.toJson(coche); // INSERTAR COCHE

            // INSERTAR DOCUMENTOS -> CONVERTIR LA CADENA JSON EN UN DOCUMENTO BSON
            doc = Document.parse(json); // PARSEAR UN DOCUMENTO BSON E INSERTAR
            collection.insertOne(doc); // INSERTAR EL DOCUMENTO EN LA COLECCION
            return true;
        } catch (Exception e) {
            StaticCode.Alerts("ERROR", "Error de conexión", "¡ERROR!",
                    "Error al conectar a la base de datos: " + e.getMessage());
        }
        return false;
    } // METODO PARA INSERTAR COCHE


    public ObservableList<Coche> listOfCars() {
        // CREAR UN CURSOR PARA ITERAR SOBRE LOS DOCMENTOS DE LA COLECCION
        MongoCursor<Document> cursor3 = collection.find().iterator();
        Coche coche;
        ArrayList<Coche> arrayListCoches = new ArrayList<>(); // LISTA PARA ALMACENAR LOS COCHES ANTES DE CONVERTIRLA A UN OBSERVABLELIST
        ObservableList<Coche> listCoches; // LISTA OBSERVABLE QUE SE DEVOLVERA
        try {
            // ITERAR CADA DOCUMENTO EN LA COLECCION
            while (cursor3.hasNext()) {
                //SERIALIZAR: CONVIERTE EL DOCUMENTO A UN OBJETO COCHE
                coche = gson.fromJson(cursor3.next().toJson(), Coche.class);
                arrayListCoches.add(coche); // AÑADIR LOS DATOS AL ARRAYLIST
            } // RECORRER EL CURSOR
            // CONVERTIR LA LISTA TEMPORAL EN UN OBSERVABLELIST
            listCoches = FXCollections.observableArrayList(arrayListCoches); // SE AÑADE EL ARRAYLIST AL OBSERVABLELIST
        } finally {
            cursor3.close(); // SE CIERRA EL CURSOR
        }
        return listCoches; // RETORNA LA LISTA
    } // METODO PARA LISTAR LOS COCHES


    public boolean deleteCar(String matricula) {
        // ELIMINA EL COCHE CUYA MATRICULA COINCIDE CON EL PARAMETRO
        collection.findOneAndDelete(Filters.eq("matricula", matricula));
        return true;
    } // METODO PARA ELIMINAR COCHE POR SU MATRICULA


    public boolean modifyCar(Coche coche, String matricula) {
        // ACTUALIZA LOS CAMPOS DE UN COCHE POR SU MATRICULA
        collection.findOneAndUpdate(Filters.eq("matricula", matricula),
                Updates.combine(
                        Updates.set("matricula", coche.getMatricula()),
                        Updates.set("marca", coche.getMarca()),
                        Updates.set("modelo", coche.getModelo()),
                        Updates.set("tipo", coche.getTipo())
                ));
        return true;
    } // METODO PARA MODIFICAR LOS DATOS DE UN COCHE EXISTENTE

    /*public static boolean estaMatricula(String matriculaNueva, String matriculaActual) {
        // CREAR UN CURSOR PARA ITERAR SOBRE LOS DOCMENTOS DE LA COLECCION
        MongoCursor<Document> cursor3 = collection.find().iterator();
        Gson gson = new Gson(); // CREAR OBJETO GSON PARA LA SERIALIZACION
        Coche coche;
        ArrayList<Coche> listCoches = new ArrayList<>(); // LISTA PARA ALMACENAR LOS COCHES ANTES DE CONVERTIRLA A UN OBSERVABLELIST
        try {
            // ITERAR CADA DOCUMENTO EN LA COLECCION
            while (cursor3.hasNext()) {
                //SERIALIZAR: CONVIERTE EL DOCUMENTO A UN OBJETO COCHE
                coche = gson.fromJson(cursor3.next().toJson(), Coche.class);
                listCoches.add(coche); // AÑADIR LOS DATOS AL ARRAYLIST
            } // RECORRER EL CURSOR
            // CONVERTIR LA LISTA TEMPORAL EN UN OBSERVABLELIST
        } finally {
            cursor3.close(); // SE CIERRA EL CURSOR
        }

        // VERIFICAR SI ALGUNA MATRICULA DE LA LISTA COINCIDE CON EL PARAMETRO
        for (Coche coche1 : listCoches) {
            if (Objects.equals(coche1.getMatricula(), matriculaNueva) &&
                    (!Objects.equals(coche1.getMatricula(), matriculaActual) || coche1.getMatricula() == null)) {
                return true; // SI EXISSTE Y NO ES LA MATRICULA ACTUAL O LA MATRICULA ACTUAL ES NULO, DEVUELVE TRUE
            }
        }
        return false; // SI NO EXISTE, DEVUELVE FALSE
    } // VERIFICAR SI UNA MTRICULA YA EXISTE*/
}
