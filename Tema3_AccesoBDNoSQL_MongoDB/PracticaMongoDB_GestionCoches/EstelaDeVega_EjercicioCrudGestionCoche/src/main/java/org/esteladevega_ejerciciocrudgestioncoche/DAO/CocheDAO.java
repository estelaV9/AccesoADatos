package org.esteladevega_ejerciciocrudgestioncoche.DAO;

import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import org.bson.Document;
import org.esteladevega_ejerciciocrudgestioncoche.Connection.ConnectionDB;
import org.esteladevega_ejerciciocrudgestioncoche.Model.Coche;
import org.esteladevega_ejerciciocrudgestioncoche.Utilities.StaticCode;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.esteladevega_ejerciciocrudgestioncoche.Controller.GestionCocheCtrller.collection;

public class CocheDAO {
    public static void insertCar(Coche coche) {
        String json;
        Document doc;
        try {
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
    } // METODO PARA INSERTAR COCHE


    public static ObservableList<Coche> listCar() {
        // CREAR UN CURSOR PARA ITERAR SOBRE LOS DOCMENTOS DE LA COLECCION
        MongoCursor<Document> cursor3 = collection.find().iterator();
        Gson gson = new Gson(); // CREAR UBSTABCUA DE GSON PARA LA SERIALIZACION
        Coche coche;
        ArrayList<Coche> arrayList = new ArrayList<>(); // LISTA PARA ALMACENAR LOS COCHES ANTES DE CONVERTIRLA A UN OBSERVABLELIST
        ObservableList<Coche> listCoches;
        try {
            while (cursor3.hasNext()) {
                //SERIALIZAR: CONVIERTE EL DOCUMENTO A UN OBJETO COCHE
                coche = gson.fromJson(cursor3.next().toJson(), Coche.class);
                arrayList.add(coche); // AÑADIR LOS DATOS AL ARRAYLIST
            } // RECORRER EL CURSOR
            listCoches = FXCollections.observableArrayList(arrayList); // SE AÑADE EL ARRAYLIST AL OBSERVABLELIST
        } finally {
            cursor3.close(); // SE CIERRA EL CURSOR
        }
        return listCoches; // RETORNA LA LISTA
    } // METODO PARA LISTAR LOS COCHES
}
