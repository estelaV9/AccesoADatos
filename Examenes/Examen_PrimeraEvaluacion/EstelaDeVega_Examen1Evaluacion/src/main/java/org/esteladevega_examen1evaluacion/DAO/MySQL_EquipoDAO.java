package org.esteladevega_examen1evaluacion.DAO;

import org.esteladevega_examen1evaluacion.Connection.MySQL_ConnectionDB;
import org.esteladevega_examen1evaluacion.Model.Equipo;
import org.esteladevega_examen1evaluacion.Utilities.StaticCode;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySQL_EquipoDAO implements  MySQL_EquipoInterface{
    private Connection connection;

    public MySQL_EquipoDAO() {
        connection = MySQL_ConnectionDB.conectar(); // CONECTAR LA BASE DE DATOS
    }

    public boolean insertEquipo(Equipo equipo) {
        try {
            String sql = "INSERT INTO equipos VALUES (?, ?, ?, ?, ?);";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, equipo.getIdEquipo());
            statement.setString(2, equipo.getNombreEquipo());
            statement.setString(3, equipo.getPatrocinado());
            statement.setString(4, equipo.getCategoria());
            statement.setBoolean(5, equipo.isSancionado());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                return true; // SI SE INSERTO, DEVUELVE TRUE
            }
        } catch (SQLException e) {
            StaticCode.Alerts("ERROR", "Error de conexión", "¡ERROR!",
                    "Error al conectar a la base de datos: " + e.getMessage());
            return false; // SI OCURRIO UN ERROR, DEVUELVE FALSE
        }
        return false;
    } // METODO PARA INSERTAR EQUIPO
}
