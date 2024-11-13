package com.estela.HibernateRelacionNaN;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import model.*;
import util.HibernateUtil;

public class EliminarCategoria {

    public static void main(String[] args) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = HibernateUtil.getSession();

        try {
            session.beginTransaction();
            Categorias cat = session.get(Categorias.class, 10); // CREAR UNA CATEGORIA
            session.delete(cat);
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            factory.close();
        }
    }
}