package com.juan.CRUD_Hibernate_cfg.DAO;

import com.juan.CRUD_Hibernate_cfg.Model.Profesor;
import com.juan.CRUD_Hibernate_cfg.Model.Usuario;
import org.hibernate.Session;

import java.util.List;

public class UsuarioDAO {
    public static void insertUser(Usuario usuario, Session session) {
        session.beginTransaction();
        session.save(usuario);
        session.getTransaction().commit();
    } // METODO PARA INSERTAR USUARIO

    public static void updateUser(Usuario usuario, Session session) {
        session.beginTransaction();
        session.update(usuario);
        session.getTransaction().commit();
    } // METODO PARA MODIFICAR USUARIO

    public static void deleteUser(Usuario usuario, Session session) {
        session.beginTransaction();
        session.delete(usuario);
        session.getTransaction().commit();
    } // METODO PARA ELIMINAR USUARIO

    public static void listUsers(Session session) {
        // CONSULTA
        List<Usuario> lista = session.createQuery("from Usuario").getResultList();

        for (Usuario user : lista) {
            // FORMATO CLASICO
            System.out.println(user.toString());
        }
    } // METODO PARA LISTAR USUARIOS

    public static Usuario searchUser(Session session, int id) {
        // SE TENDRIA QUEPONER VALIDACIONES
        Usuario usuario;
        usuario = session.get(Usuario.class, id);
        System.out.println(usuario.getId());
        System.out.println(usuario.getNombre());
        System.out.println(usuario.getApellidos());
        System.out.println(usuario.getFechaNacimiento());
        return usuario;
    } // METODO PARA BUSCAR PROFESOR
}
