package com.estela.HibernateRelacionNaN;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import model.*;
import util.HibernateUtil;

public class LeerCategoria {
    public static void main(String[] args) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = HibernateUtil.getSession();

        try {
            session.beginTransaction();
			// CREAR UNA CATEGORIA
            Categorias cat = session.get(Categorias.class, 1);
            System.out.println(cat);
            System.out.println(cat.getProductos());
            session.getTransaction().commit();
        } finally {
            factory.close();
        }
    }
}