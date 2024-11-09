package org.example.DAO;

import org.example.domain.Usuario;
import java.sql.*;

public class UsuariosDAO {
    public static boolean guardarUsuario(Connection con, Usuario usuario) {
        try {
            String sql = "INSERT INTO usuarios (email, contraseña) VALUES (?, ?)";

            PreparedStatement sentencia = con.prepareStatement(sql);
            sentencia.setString(1, usuario.getEmail());
            sentencia.setString(2, usuario.getContraseña());

            sentencia.executeUpdate();
            return true;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static boolean existeUsuario(Connection con, String email) {
        try {
            String sql = "SELECT * FROM usuarios WHERE email = ?";
            PreparedStatement sentencia = con.prepareStatement(sql);
            sentencia.setString(1, email);
            ResultSet resultado = sentencia.executeQuery();
            return resultado.next();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println(e.getMessage());
            return false;
        }
    }
}