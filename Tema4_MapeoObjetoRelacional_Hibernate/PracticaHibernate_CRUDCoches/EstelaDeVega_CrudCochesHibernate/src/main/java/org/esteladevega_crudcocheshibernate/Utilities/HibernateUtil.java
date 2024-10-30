package org.esteladevega_crudcocheshibernate.Utilities;

import org.esteladevega_crudcocheshibernate.Model.Coche;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    static SessionFactory factory = null;
    static {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        cfg.addAnnotatedClass(Coche.class);
        factory = cfg.buildSessionFactory();
    } // METODO PARA ACCEDER AL OBJETO SessionFactory QUE SE HA CREADO

    public static SessionFactory getSessionFactory() {
        return factory;
    }

    public static Session getSession() {
        return factory.openSession();
    }
} // INICIALIZA HIBERNATE Y CREA SESSIONFACTORY
