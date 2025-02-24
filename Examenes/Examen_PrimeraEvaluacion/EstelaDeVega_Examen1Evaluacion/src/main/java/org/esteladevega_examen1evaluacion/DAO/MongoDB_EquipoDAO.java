package org.esteladevega_examen1evaluacion.DAO;

import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.esteladevega_examen1evaluacion.Connection.MongoDB_ConnectionDB;
import org.esteladevega_examen1evaluacion.Model.Equipo;
import org.esteladevega_examen1evaluacion.Utilities.StaticCode;

public class MongoDB_EquipoDAO implements MongoDB_EquipoInterface{
    MongoClient mongoClient;
    MongoDatabase mongoDatabase; // BASE DE DATOS DE MONGO
    MongoCollection<Document> collection;

    public MongoDB_EquipoDAO() {
        mongoClient = MongoDB_ConnectionDB.conectar();
        mongoDatabase = mongoClient.getDatabase("ExamenEquipos");
        collection = mongoDatabase.getCollection("Equipos");
    }

    public boolean insertEquipo(Equipo equipo) {
        Document doc = new Document(); // DOCUMENTO BSON QUE SE INSERTARA EN LA BD
        try {
            doc.append("idEquipo", equipo.getIdEquipo())
                    .append("nombreEquipo", equipo.getNombreEquipo())
                    .append("patrocinador", equipo.getPatrocinado())
                    .append("categoria", equipo.getCategoria())
                    .append("sancionado", equipo.isSancionado());

            collection.insertOne(doc); // INSERTAR EL DOCUMENTO EN LA COLECCION
            return true;
        } catch (Exception e) {
            StaticCode.Alerts("ERROR", "Error de conexión", "¡ERROR!",
                    "Error al conectar a la base de datos: " + e.getMessage());
        }
        return false;
    } // METODO PARA INSERTAR EQUIPO

    public boolean eliminarEquipo(int id) {
        // ELIMINA EL EQUIPO CUYO ID COINCIDE CON EL PARAMETRO
        collection.findOneAndDelete(Filters.eq("idEquipo", id));
        return true;
    } // METODO PARA ELIMINAR EQUIPO POR SU ID

}
