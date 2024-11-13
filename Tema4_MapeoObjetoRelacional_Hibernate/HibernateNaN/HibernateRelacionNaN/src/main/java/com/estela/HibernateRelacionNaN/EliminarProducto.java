package com.estela.HibernateRelacionNaN;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import model.*;
import util.HibernateUtil;

public class EliminarProducto {
    public static void main(String[] args) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = HibernateUtil.getSession();

        try {
            session.beginTransaction();
            Productos prod = session.get(Productos.class, 6);
            session.delete(prod);
            session.getTransaction().commit();
        } finally {
            factory.close();
        }
    }
}