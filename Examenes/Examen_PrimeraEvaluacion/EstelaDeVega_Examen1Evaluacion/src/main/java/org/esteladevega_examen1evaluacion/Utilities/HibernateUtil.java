package org.esteladevega_examen1evaluacion.Utilities;

import org.esteladevega_examen1evaluacion.Model.Equipo;
import org.esteladevega_examen1evaluacion.Model.Jugador;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    static Session session;
    static SessionFactory factory;
    static {
        Configuration configuration = new Configuration();
        configuration.configure(R.getHibernateConfig("hibernate.cfg.xml"));
        configuration.addAnnotatedClass(Equipo.class);
        configuration.addAnnotatedClass(Jugador.class);

        factory = configuration.buildSessionFactory();

        session = factory.openSession();
    }

    public static Session getSession(){ //Devuelve la sesión
        return session;
    }
} // CLASE QUE GESTIONA LO RELACIONADO A HIBERNATE