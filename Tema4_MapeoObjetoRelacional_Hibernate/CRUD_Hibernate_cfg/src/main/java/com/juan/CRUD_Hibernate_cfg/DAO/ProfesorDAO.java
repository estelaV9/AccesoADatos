package com.juan.CRUD_Hibernate_cfg.DAO;

import com.juan.CRUD_Hibernate_cfg.Model.Profesor;
import org.hibernate.Session;
import java.util.List;

public class ProfesorDAO {
    public static void insertTeacher(Profesor profesor, Session session) {
        session.beginTransaction();
        session.save(profesor);
        session.getTransaction().commit();
    } // METODO PARA INSERTAR PROFESOR

    public static void updateTeacher(Profesor profesor, Session session) {
        session.beginTransaction();
        session.update(profesor);
        session.getTransaction().commit();
    } // METODO PARA MODIFICAR PROFESOR

    public static void deleteTeacher(Profesor profesor, Session session) {
        session.beginTransaction();
        session.delete(profesor);
        session.getTransaction().commit();
    } // METODO PARA ELIMINAR PROFESOR

    public static void listTeachers(Session session) {
        // CONSULTA
        List<Profesor> lista = session.createQuery("from Profesor").getResultList();

        for (Profesor p : lista) {
            // FORMATO CLASICO
            System.out.println(p.toString());
        }
        //list.forEach(System.out::println);//version 1.8 de java*/
    } // METODO PARA LISTAR PROFESORES

    public static Profesor searchTeacher(Session session, int id) {
        // SE TENDRIA QUEPONER VALIDACIONES
        Profesor profesor;
        profesor = session.get(Profesor.class, id);
        System.out.println(profesor.getId());
        System.out.println(profesor.getNombre());
        System.out.println(profesor.getApe1());
        System.out.println(profesor.getApe2());
        return profesor;
    } // METODO PARA BUSCAR PROFESOR

}
