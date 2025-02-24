package com.juan.CRUD_Hibernate_cfg;

import com.juan.CRUD_Hibernate_cfg.DAO.UsuarioDAO;
import com.juan.CRUD_Hibernate_cfg.Model.Usuario;
import com.juan.CRUD_Hibernate_cfg.Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.time.LocalDate;

public class App {
    static SessionFactory factory = null;

    public static void main(String[] args) {
        // SE TIENE QUE CREAR UNA NUEVA FACTORY Y UNA NUEVA SESION
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = HibernateUtil.getSession();

        Usuario user1  = new Usuario(1, "Pepe", "Pepito", LocalDate.of(2010, 12, 22));
        Usuario user2  = new Usuario(2, "Eugenio", "Daniel", LocalDate.of(2014, 11, 11));
        Usuario user3  = new Usuario(3, "Daniel", "Eugenio", LocalDate.of(2013, 10, 15));
        Usuario user4  = new Usuario(4, "Daniele", "Eugenie", LocalDate.of(2018, 2, 9));

        System.out.println("\n\n************************** INSERTAR USUARIO ***************************");
        UsuarioDAO.insertUser(user1, session);
        UsuarioDAO.insertUser(user2, session);
        UsuarioDAO.insertUser(user3, session);
        UsuarioDAO.insertUser(user4, session);


        System.out.println("\n\n************************** MODIFICAR USUARIO ***************************");
        Usuario user_aux = UsuarioDAO.searchUser(session, 1);
        user_aux.setNombre("Alfredo");

        UsuarioDAO.updateUser(user_aux, session);


        System.out.println("\n\n************************** ELIMINAR USUARIO ***************************");
        UsuarioDAO.deleteUser(user3, session);

        System.out.println("\n\n************************** LISTAR USUARIOS ***************************");
        UsuarioDAO.listUsers(session);

        System.out.println("\n\n************************** BUSCAR USUARIO 1 ***************************");
        Usuario usuario = UsuarioDAO.searchUser(session, 1);
        System.out.println(usuario);

        session.close();
        factory.close();



        /*//SessionFactoryes una instancia que creará objetos de tipo Sessiono(fabrica sessiones).

        SessionFactory factory = HibernateUtil.getSessionFactory();

        //Ahora que ya tenemos el objeto SessionFactory podemos obtener la Session
        //para trabajar con Hibernate.

        //Una sesión se utiliza para obtener una conexión física con una base de datos.
        //El objeto Session es liviano y está diseñado para ser instanciado
        //cada vez que se necesita una interacción con la base de datos.
        //Los objetos persistentes se guardan y recuperan a través de un objeto de sesión.

        //Los objetos de la sesión no deben mantenerse abiertos durante mucho tiempo
        //porque generalmente no son seguros para subprocesos y deben crearse y destruirse
        //según sea necesario. La función principal de la sesión
        //es ofrecer, crear, leer y eliminar operaciones para instancias
        //de clases de entidades asignadas.

        Session session = HibernateUtil.getSession();

        Profesor profesor1 = new Profesor(102, "Carlos", "González", "Oltra");
        Profesor profesor2 = new Profesor(103, "Ana", "Sanchez", "Velasco");
        Profesor profesor3 = new Profesor(104, "Luis", "Colinas", "Alonso");

        System.out.println("\n\n************************** INSERTAR PROFESOR ***************************");
        ProfesorDAO.insertTeacher(profesor1, session);
        ProfesorDAO.insertTeacher(profesor2, session);
        ProfesorDAO.insertTeacher(profesor3, session);


        System.out.println("\n\n************************** MODIFICAR PROFESOR ***************************");
        Profesor profesor_aux = ProfesorDAO.searchTeacher(session, 102);
        profesor_aux.setNombre("Alfredo");

        ProfesorDAO.updateTeacher(profesor_aux, session);


        System.out.println("\n\n************************** ELIMINAR PROFESOR ***************************");
        ProfesorDAO.deleteTeacher(profesor3, session);

        System.out.println("\n\n************************** LISTAR PROFESOR ***************************");
        ProfesorDAO.listTeachers(session);

        System.out.println("\n\n************************** BUSCAR PROFESOR 102***************************");
        Profesor profesor = ProfesorDAO.searchTeacher(session, 102);
        System.out.println(profesor);

        session.close();
        factory.close();*/
    }
}