package org.esteladevega_ejerciciocrudgestioncoche.DAO;

import com.google.gson.Gson;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.bson.Document;
import org.esteladevega_ejerciciocrudgestioncoche.Model.Coche;
import org.esteladevega_ejerciciocrudgestioncoche.Utilities.StaticCode;
import java.util.ArrayList;
import java.util.Objects;
import static org.esteladevega_ejerciciocrudgestioncoche.Controller.GestionCocheCtrller.collection;

public class CocheDAO {
    public static void insertCar(Coche coche) {
        String json; // VARIABLE PARA ALMACENAR EL JSON DEL COCHE
        Document doc; // DOCUMENTO BSON QUE SE INSERTARA EN LA BD
        try {
            // DESERIALIZAR OBJETO A STRING JSON
            Gson gson = new Gson();
            json = gson.toJson(coche); // INSERTAR COCHE

            // INSERTAR DOCUMENTOS -> CONVERTIR LA CADENA JSON EN UN DOCUMENTO BSON
            doc = Document.parse(json); // PARSEAR UN DOCUMENTO BSON E INSERTAR
            collection.insertOne(doc); // INSERTAR EL DOCUMENTO EN LA COLECCION
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
        Gson gson = new Gson(); // CREAR OBJETO GSON PARA LA SERIALIZACION
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


    public static void deleteCar(String matricula) {
        // ELIMINA EL COCHE CUYA MATRICULA COINCIDE CON EL PARAMETRO
        collection.findOneAndDelete(Filters.eq("matricula", matricula));
    } // METODO PARA ELIMINAR COCHE POR SU MATRICULA


    public static void modifyCar(String matricula, Coche coche) {
        // ACTUALIZA LOS CAMPOS DE UN COCHE POR SU MATRICULA
        collection.findOneAndUpdate(Filters.eq("matricula", matricula),
                Updates.combine(
                        Updates.set("matricula", coche.getMatricula()),
                        Updates.set("marca", coche.getMarca()),
                        Updates.set("modelo", coche.getModelo()),
                        Updates.set("tipo", coche.getTipo())
                ));
    } // METODO PARA MODIFICAR LOS DATOS DE UN COCHE EXISTENTE

    public static boolean estaMatricula(String matriculaNueva, String matriculaActual) {
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
    } // VERIFICAR SI UNA MTRICULA YA EXISTE
}