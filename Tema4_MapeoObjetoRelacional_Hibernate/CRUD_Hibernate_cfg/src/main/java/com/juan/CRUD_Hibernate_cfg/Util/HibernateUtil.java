package com.juan.CRUD_Hibernate_cfg.Util;

import com.juan.CRUD_Hibernate_cfg.Model.Profesor;
import com.juan.CRUD_Hibernate_cfg.Model.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

//import com.juan.CRUD_Hibernate_cfg.Model.Profesor;
/*
 * En cualquier libro o tutorial que hable sobre Hibernate acabará hablándose
 * de la clase HibernateUtil.
 * Esta clase que debemos crearnos nosotros y que no está incluida en Hibernate
 * contiene código estático que inicializa Hibernate y crea el objeto SessionFactory.
 * Se incluye además un método estático que da acceso al objeto SessionFactory que se ha creado.
 */

//HibernateUtil, que nos de las conexiones con la base de datos. 

//De esta forma, la SessionFactory será única y compartida por todos

public class HibernateUtil {
    static SessionFactory factory = null;

    static {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        cfg.addAnnotatedClass(Profesor.class);
        cfg.addAnnotatedClass(Usuario.class);
        factory = cfg.buildSessionFactory();
    }


    public static SessionFactory getSessionFactory() {
        return factory;
    }

    public static Session getSession() {
        return factory.openSession();
    }

}
