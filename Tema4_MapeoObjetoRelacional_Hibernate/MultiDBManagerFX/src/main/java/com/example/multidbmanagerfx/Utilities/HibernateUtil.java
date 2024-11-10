package com.example.multidbmanagerfx.Utilities;

import com.example.multidbmanagerfx.Model.Coche;
import com.example.multidbmanagerfx.Model.Multa;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    static Session session;
    static SessionFactory factory;
    static {
        Configuration configuration = new Configuration();
        configuration.configure(R.getHibernateConfig("hibernate.cfg.xml"));
        configuration.addAnnotatedClass(Multa.class);
        configuration.addAnnotatedClass(Coche.class);

        factory = configuration.buildSessionFactory();

        session = factory.openSession();
    }

    public static Session getSession(){ //Devuelve la sesión
        return session;
    }
} // CLASE QUE GESTIONA LO RELACIONADO A HIBERNATE