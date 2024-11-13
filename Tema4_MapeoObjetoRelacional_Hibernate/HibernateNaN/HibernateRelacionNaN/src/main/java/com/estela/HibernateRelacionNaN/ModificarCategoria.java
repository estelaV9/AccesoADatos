package com.estela.HibernateRelacionNaN;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import model.*;
import util.HibernateUtil;

public class ModificarCategoria {

    public static void main(String[] args) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = HibernateUtil.getSession();

        try {
            session.beginTransaction();
            Categorias cat = session.get(Categorias.class, 1);
            cat.setNombre("Fruta de Invierno");
            session.save(cat);
            session.getTransaction().commit();
        } finally {
            factory.close();
        }
    }
}