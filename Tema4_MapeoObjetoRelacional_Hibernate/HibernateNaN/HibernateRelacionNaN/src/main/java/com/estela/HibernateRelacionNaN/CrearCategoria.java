package com.estela.HibernateRelacionNaN;

import model.Categorias;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import util.HibernateUtil;


public class CrearCategoria {
    public static void main(String[] args) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = HibernateUtil.getSession(); // CREAR LA SESION

        try {
            session.beginTransaction(); // INICIAR LA TRANSACCION

            // CREAMOS UNA CATEGORIA
            for (int i = 7; i < 12; i++) {
                Categorias cat = new Categorias("Cat" + i + " desde hibernate");
                session.save(cat); // GUARDAMOS LA CATEGORIA
            }

            session.getTransaction().commit(); // COMMIT DE LA TRANSACCION
        } finally {
            factory.close();
        }
    }
}